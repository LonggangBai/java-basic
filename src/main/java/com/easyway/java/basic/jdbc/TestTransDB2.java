package com.easyway.java.basic.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * java实现数据库同步
 * 这里实现方式事先说明下，采用json+文本流方式，即读取数据库信息至文本流中，格式采用json，之后读取文本中json数据至数据库中
 * ，实现度还不完全，不过也提供了一种解决方案，目前可支持同类型数据库表同步，使用后可根据个人需要进行不同表数据库同步进行修改，仅贡献个人代码给各位大神。
 * 再次说明下由于各个数据库中关于数字的类型可能有所不同，所以这里提供了一个db2拓展实现作为demo，其他数据库可以效仿。
 * 
 * @author Administrator
 * 
 */
abstract class DBTools {
	protected Connection conn = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	protected String username;// 数据库用户名
	protected String password;// 数据库密码
	protected String dbname;// 数据库名称
	// 数据库中的数字域
	protected List numList = new ArrayList();

	protected boolean connectDB(String dbname, String user, String password)
			throws Exception {
		// 加载驱动程序以连接数据库
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection("jdbc:odbc:" + dbname, user,
					password);
			// url = "jdbc:db2:njtcdata";
		}
		// 捕获加载驱动程序异常
		catch (ClassNotFoundException cnfex) {
			System.err.println("装载JDBC驱动程序失败。");
			cnfex.printStackTrace();
			return false;
		}
		// 捕获连接数据库异常
		catch (SQLException sqlex) {
			System.err.println("无法连接数据库");
			sqlex.printStackTrace();
			// System.exit(1); // terminate program
			return false;
		}
		return true;
	}

	protected int executeSQL(String sql) throws SQLException {
		System.out.println(sql);
		return stmt.executeUpdate(sql);
	}

	protected ResultSet query(String sql) throws Exception {
		// 先打印出SQL
		System.out.println(sql);
		try {
			Statement stmt = conn
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行查询语句错误!", e);
		}
		return rs;
	}

	protected void colseConnect() throws Exception {
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
		if (rs != null)
			rs.close();
		// conn.close();
	}

	protected String createFilePath() {
		Date dt = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-sss");
		String filename = System.getProperty("user.dir") + "file://src//"
				+ sf.format(dt) + ".txt";
		return filename;
	}

	protected String generalInsertSQL(String table, JSONObject jobject,
			JSONArray columns) throws JSONException {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into " + table + "(");
		for (int i = 0; i < columns.length(); i++) {
			JSONObject jcolumn = columns.getJSONObject(i);
			String column = jcolumn.getString("COLNAME");
			sb.append(column);
			if (i != columns.length() - 1) {
				sb.append(",");
			}
		}
		sb.append(") values(");
		for (int i = 0; i < columns.length(); i++) {
			JSONObject jcolumn = columns.getJSONObject(i);
			String column = jcolumn.getString("COLNAME");
			String typecolumn = jcolumn.getString("TYPENAME");
			String datacolumn = jobject.getString(column).equals("null") ? null
					: jobject.getString(column);

			if (datacolumn == null) {
				sb.append("null");
			} else if (numList.contains(typecolumn)) {
				// 是数字域
				sb.append(datacolumn);
			} else {
				// 文本域或者日期域
				sb.append("'" + datacolumn + "'");
			}
			if (i != columns.length() - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	protected String generalTextSQL(JSONArray columns, String tablename,
			String condition) throws JSONException {
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		for (int i = 0; i < columns.length(); i++) {
			JSONObject jcolumn = (JSONObject) columns.get(i);
			String column = jcolumn.getString("COLNAME");
			// String strColumn = "case when " + column
			// + " is null then '' else char(" + column + ") end  as "
			// + column;
			String strColumn = column;
			sb.append(strColumn);
			if (i != columns.length() - 1) {
				sb.append(" ,");
			}
		}
		sb.append("  from  " + tablename);
		if (!condition.equals("")) {
			// 添加控制条线
			sb.append(" where " + condition);
		}
		return sb.toString();
	}

	public String readToFile(String table, String condition) throws Exception {
		String path = createFilePath();
		File file = new File(path);
		JSONArray columns = queryTableColumns(table);
		if (columns == null) {
			// 读取表失败
			System.out.println("读取表失败");
			return null;
		}
		String sql = generalTextSQL(columns, table, condition);

		ResultSet rs = query(sql);
		FileOutputStream fout = new FileOutputStream(file);
		System.out.println("开始读取表到文件中...");
		int index = 0;
		fout.write("[".getBytes());
		while (rs.next()) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			for (int i = 0; i < columns.length(); i++) {
				JSONObject jcolumn = (JSONObject) columns.get(i);
				String column = jcolumn.getString("COLNAME");
				String columndate = rs.getString(column);
				String addDateStr = "\"" + column + "\":\"" + columndate + "\"";
				sb.append(addDateStr);
				if (i != columns.length() - 1) {
					sb.append(",");
				}
			}
			if (rs.isLast()) {
				sb.append("}");
			} else {
				sb.append("},");
			}
			fout.write(sb.toString().getBytes());
			index++;
		}
		fout.write("]".getBytes());
		System.out.println("读取完毕！");
		System.out.println("读取总数据量为:" + index + "条！");
		System.out.println("文件路径为！");
		System.out.println(path);
		return path;
	}

	public boolean readToDatabase(String table, String filePath)
			throws Exception {
		JSONArray columns = queryTableColumns(table);
		if (columns == null) {
			// 读取表失败
			return false;
		}
		// 先打印出SQL
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			System.out.println("开始读取文件...");
			File file = new File(filePath);
			FileInputStream filein = new FileInputStream(file);
			byte[] in = new byte[999];
			byte temp;
			int index = 0;
			while ((temp = (byte) filein.read()) != -1) {
				if (temp == 123) {
					// {
					index = 0;
					in[index++] = temp;
				} else if (temp == 125) {
					// }
					in[index++] = temp;
					String str = new String(in, "gbk");
					JSONObject jobject = new JSONObject(str);
					String insertSQL = generalInsertSQL(table, jobject, columns);
					System.out.println(insertSQL);
					stmt.executeUpdate(insertSQL);
					// 重新更新in内存
					in = null;
					in = new byte[999];
				} else {
					in[index++] = temp;

				}

			}
			System.out.println("读取文件完毕");
			System.out.println("开始插入数据库...");
			conn.commit();
			System.out.println("插入数据库完毕！");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			e.printStackTrace();
			try {
				throw new Exception("执行查询语句错误!", e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return true;
	}

	public abstract JSONArray queryTableColumns(String table) throws Exception;
}

public class TestTransDB2 extends DBTools {

	public TestTransDB2(String dbname, String username, String password) {
		numList.add("INTEGER");
		numList.add("DECIMAL");
		this.dbname = dbname;
		this.username = username;
		this.password = password;
		try {
			connectDB(dbname, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONArray queryTableColumns(String table) throws Exception {
		// 检验表名是否存在
		String sql = "Select COLNAME,TYPENAME from SYSCAT.COLUMNS WHERE TABNAME='"
				+ table + "' order by COLNO";
		// System.out.println(sql);
		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		JSONArray result = new JSONArray();

		while (rs.next()) {
			JSONObject jobject = new JSONObject();
			String COLNAME = rs.getString("COLNAME").toString();
			String TYPENAME = rs.getString("TYPENAME").toString();
			jobject.put("COLNAME", COLNAME);
			jobject.put("TYPENAME", TYPENAME);

			result.put(jobject);
		}
		if (result.length() == 0) {
			return null;
		} else {
			return result;
		}
	}
}
