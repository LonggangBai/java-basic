/**
 * Project Name:java-basic
 * File Name:ConnectionHelper.java
 * Package Name:com.easyway.java.basic.jdbc
 * Date:2015-1-21下午2:35:30
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * JDBC连接数据库    
 * •创建一个以JDBC连接数据库的程序，包含7个步骤：    
 *  1、加载JDBC驱动程序：    
 *     在连接数据库之前，首先要加载想要连接的数据库的驱动到JVM（Java虚拟机），    
 *     这通过java.lang.Class类的静态方法forName(String  className)实现。    
 *     例如：    
 *     try{    
 *     //加载MySql的驱动类    
 *     Class.forName("com.mysql.jdbc.Driver") ;    
 *     }catch(ClassNotFoundException e){    
 *     System.out.println("找不到驱动程序类 ，加载驱动失败！");    
 *     e.printStackTrace() ;    
 *     }    
 *    成功加载后，会将Driver类的实例注册到DriverManager类中。    
 *  2、提供JDBC连接的URL    
 *    •连接URL定义了连接数据库时的协议、子协议、数据源标识。    
 *     •书写形式：协议：子协议：数据源标识    
 *     协议：在JDBC中总是以jdbc开始    
 *     子协议：是桥连接的驱动程序或是数据库管理系统名称。    
 *     数据源标识：标记找到数据库来源的地址与连接端口。    
 *     例如：（MySql的连接URL）    
 *     jdbc:mysql:    
 *         //localhost:3306/test?useUnicode=true&characterEncoding=gbk ;    
 *    useUnicode=true：表示使用Unicode字符集。如果characterEncoding设置为    
 *    gb2312或GBK，本参数必须设置为true 。characterEncoding=gbk：字符编码方式。    
 *  3、创建数据库的连接    
 *     •要连接数据库，需要向java.sql.DriverManager请求并获得Connection对象，    
 *      该对象就代表一个数据库的连接。    
 *     •使用DriverManager的getConnectin(String url , String username ,     
 *     String password )方法传入指定的欲连接的数据库的路径、数据库的用户名和    
 *      密码来获得。    
 *      例如：    
 *      //连接MySql数据库，用户名和密码都是root    
 *      String url = "jdbc:mysql://localhost:3306/test" ;     
 *      String username = "root" ;    
 *      String password = "root" ;    
 *      try{    
 *     Connection con =     
 *              DriverManager.getConnection(url , username , password ) ;    
 *      }catch(SQLException se){    
 *     System.out.println("数据库连接失败！");    
 *     se.printStackTrace() ;    
 *      }    
 *  4、创建一个Statement    
 *     •要执行SQL语句，必须获得java.sql.Statement实例，Statement实例分为以下3   
 *      种类型：    
 *       1、执行静态SQL语句。通常通过Statement实例实现。    
 *       2、执行动态SQL语句。通常通过PreparedStatement实例实现。    
 *       3、执行数据库存储过程。通常通过CallableStatement实例实现。    
 *     具体的实现方式：    
 *         Statement stmt = con.createStatement() ;    
 *        PreparedStatement pstmt = con.prepareStatement(sql) ;    
 *        CallableStatement cstmt =     
 *                             con.prepareCall("{CALL demoSp(? , ?)}") ;    
 *  5、执行SQL语句    
 *     Statement接口提供了三种执行SQL语句的方法：executeQuery 、executeUpdate    
 *    和execute    
 *     1、ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句    
 *         ，返回一个结果集（ResultSet）对象。    
 *      2、int executeUpdate(String sqlString)：用于执行INSERT、UPDATE或    
 *         DELETE语句以及SQL DDL语句，如：CREATE TABLE和DROP TABLE等    
 *      3、execute(sqlString):用于执行返回多个结果集、多个更新计数或二者组合的    
 *         语句。    
 *    具体实现的代码：    
 *           ResultSet rs = stmt.executeQuery("SELECT * FROM ...") ;    
 *     int rows = stmt.executeUpdate("INSERT INTO ...") ;    
 *     boolean flag = stmt.execute(String sql) ;    
 *  6、处理结果    
 *     两种情况：    
 *      1、执行更新返回的是本次操作影响到的记录数。    
 *      2、执行查询返回的结果是一个ResultSet对象。    
 *     • ResultSet包含符合SQL语句中条件的所有行，并且它通过一套get方法提供了对这些    
 *       行中数据的访问。    
 *     • 使用结果集（ResultSet）对象的访问方法获取数据：    
 *      while(rs.next()){    
 *          String name = rs.getString("name") ;    
 *     String pass = rs.getString(1) ; // 此方法比较高效    
 *      }    
 *     （列是从左到右编号的，并且从列1开始）    
 *  7、关闭JDBC对象     
 *      操作完成以后要把所有使用的JDBC对象全都关闭，以释放JDBC资源，关闭顺序和声    
 *      明顺序相反：    
 *      1、关闭记录集    
 *      2、关闭声明    
 *      3、关闭连接对象    
 *           if(rs != null){   // 关闭记录集    
 *         try{    
 *             rs.close() ;    
 *         }catch(SQLException e){    
 *             e.printStackTrace() ;    
 *         }    
 *           }    
 *           if(stmt != null){   // 关闭声明    
 *         try{    
 *             stmt.close() ;    
 *         }catch(SQLException e){    
 *             e.printStackTrace() ;    
 *         }    
 *           }    
 *           if(conn != null){  // 关闭连接对象    
 *          try{    
 *             conn.close() ;    
 *          }catch(SQLException e){    
 *             e.printStackTrace() ;    
 *          }    
 *           }
 * 
 * 
 * 
 * </pre>
 * 
 * ClassName:ConnectionHelper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午2:35:30 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ConnectionHelper {
	private final static Logger Logger = LoggerFactory
			.getLogger(ConnectionHelper.class);
	private static ConnectionHelper _connectionHelper = null;
	private final ThreadLocal<Connection> currentConnection = new ThreadLocal<Connection>();

	private ConnectionHelper() {
	}

	/**
	 * 采用单利模式的双重检查实现 getConnectionHelper:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @return
	 * @since JDK 1.6
	 */
	public static ConnectionHelper getConnectionHelper() {
		if (_connectionHelper != null) {// Single check
			synchronized (ConnectionHelper.class) {
				if (_connectionHelper != null) { // Double Single check
					_connectionHelper = new ConnectionHelper();
				}
			}
		}
		return _connectionHelper;
	}

	/**
	 * initConnection:用于创建数据库连接. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public  Connection getConnection() throws Exception {
		Connection conn = null;
		if (currentConnection.get() == null) {
			conn = getConn();
			if (conn != null) {
				currentConnection.set(conn);
			}
		} else {
			conn = currentConnection.get();
		}
		return conn;
	}

	private synchronized Connection getConn() throws Exception {
		try {
			// 加载MySql的驱动类
			Class.forName(Config.DRIVERCLASS);
			// 连接MySql数据库，用户名和密码都是root
			Connection connection = DriverManager.getConnection(Config.URL,
					Config.USERNAME, Config.PASSWD);
			return connection;
		} catch (SQLException se) {
			Logger.error("获取数据库连接失败..." + se.getMessage());
			se.printStackTrace();
			throw new Exception(se);
		} catch (ClassNotFoundException e) {
			Logger.error("数据库连接失败！" + e.getMessage());
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	/**
	 * 
	 * executeUpdate:根据一条sql语句更新 <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param sql
	 * @return
	 * @since JDK 1.6
	 */
	public int executeUpdate(String sql) {
		Connection conn = null;
		Statement smst = null;
		try {
			conn = getConnection();
			smst = conn.createStatement();
			return smst.executeUpdate(sql);
		} catch (Exception e) {
			Logger.error("数据库查询失败..." + e.getMessage());
			e.printStackTrace();
		} finally {
			close(conn, smst, null);
		}
		return -1;
	}

	/**
	 * 
	 * findById: 根据sql查询一条记录 <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param sql
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String, String> findById(String sql) {
		Connection conn = null;
		Statement smst = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			smst = conn.createStatement();
			rs = smst.executeQuery(sql);
			// 获取表的表的列头部
			ResultSetMetaData rsmeta = rs.getMetaData();
			String[] headerNames = getTableHeaderName(rsmeta);
			// 获取数据
			Map<String, String> paramMap = new HashMap<String, String>();
			List<Map<String, String>> resultMapColl = getResultMapList(rs,
					headerNames, paramMap);
			if (resultMapColl != null) {
				return resultMapColl.get(0);
			}
			return null;
		} catch (Exception e) {
			Logger.error("数据库查询失败..." + e.getMessage());
			e.printStackTrace();

		} finally {
			close(conn, smst, rs);
		}
		return null;
	}

	/**
	 * 
	 * execute:查询数据列表信息 <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param sql
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public Collection<Map<String, String>> execute(String sql) throws Exception {
		Connection conn = null;
		Statement smst = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			smst = conn.createStatement();
			rs = smst.executeQuery(sql);
			// 获取表的表的列头部
			ResultSetMetaData rsmeta = rs.getMetaData();
			String[] headerNames = getTableHeaderName(rsmeta);
			// 获取数据
			Map<String, String> paramMap = new HashMap<String, String>();
			return getResultMapList(rs, headerNames, paramMap);
		} catch (Exception e) {
			Logger.error("数据库查询失败..." + e.getMessage());
			e.printStackTrace();

		} finally {
			close(conn, smst, rs);
		}
		return null;
	}

	public boolean executeQuery(String sql, Object... params) throws Exception {
		Connection conn = getConnection();
		PreparedStatement psmst = conn.prepareStatement(sql);
		if (params != null && params.length > 0) {
			for (int parameterIndex = 0; parameterIndex < params.length; parameterIndex++) {
				Object object = params[parameterIndex];
				if (object instanceof String) {
					psmst.setString(parameterIndex, String.valueOf(object));
				} else if (object instanceof Double) {
					psmst.setDouble(parameterIndex, (Double) object);
				} else if (object instanceof Long) {
					psmst.setLong(parameterIndex, (Long) object);
				} else if (object instanceof Date) {
					psmst.setDate(parameterIndex, (Date) object);
				} else if (object instanceof Integer) {
					psmst.setInt(parameterIndex, (Integer) object);
				} else if (object instanceof Short) {
					psmst.setInt(parameterIndex, (Short) object);
				}
			}
		}
		return psmst.execute();
	}

	/**
	 * close:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param conn
	 * @param smst
	 * @param rs
	 * @since JDK 1.6
	 */
	private void close(Connection conn, Statement smst, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (smst != null) {
			try {
				smst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * getResultMapList:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param rs
	 * @param headerNames
	 * @param paramMap
	 * @throws SQLException
	 * @since JDK 1.6
	 */
	private List<Map<String, String>> getResultMapList(ResultSet rs,
			String[] headerNames, Map<String, String> paramMap)
			throws SQLException {
		List<Map<String, String>> resultMapList = new ArrayList<Map<String, String>>();
		while (rs.next()) {
			for (String columnName : headerNames) {
				paramMap.put(columnName, rs.getString(columnName));
			}
		}
		return resultMapList;
	}

	/**
	 * getTableHeaderName:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param rsmeta
	 * @throws SQLException
	 * @since JDK 1.6
	 */
	private String[] getTableHeaderName(ResultSetMetaData rsmeta)
			throws SQLException {
		int columnCount = rsmeta.getColumnCount();
		String[] headerNames = new String[columnCount];
		for (int i = 0; i < columnCount; i++) {
			headerNames[i] = rsmeta.getColumnName(i);
		}
		return headerNames;
	}

}
