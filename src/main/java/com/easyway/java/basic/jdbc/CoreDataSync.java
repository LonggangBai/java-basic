package com.easyway.java.basic.jdbc;

/**
 * 如何提高JDBC的性能?
 * 
 * <pre>
 * 
 * 1. 使用数据连接池(Connection Pool), 避免使用DriverManager.getConnection，参考另外一篇博客 不推荐使用DriverManager.getConnection
 * 
 * 
 * 2. 合理的配置数据连接池参数，参考另外一篇博客  如何设置数据连接池的初始大小
 * 
 * 3. 选择合适的事务等级，按照不同的数据库操作类型选择不同的事务等级。
 * 
 * 4. 及时关闭Connection，不关闭的话会严重影响系统的性能，甚至造成系统罢工。
 * 
 * 5.  优化Statement
 * 
 * 1) 选择合适的Statement, 根据不同的数据库操作选择Statement, PreparedStatement 或者 CallableStatement， 具体选择哪个可以通过搜索引擎了解。
 * 
 * 2) 尽可能的使用batch, 这样可以减少调用JDBC的次数。 具体的方法是使用statement.addBatch(“your sql”) 添加batch, 然后执行statement.executeBatch()来一起执行。
 * 
 * 3) Statement执行完毕后关闭Statement
 * 
 * 6.  优化你的SQL, 尽量减少你的结果集，不要每次都”select * from XXX”
 * 
 * 7. 使用一些缓存工具进行缓存，特别是大数据量大访问量的系统，合理的缓存往往会显著的提高系统的性能
 * </pre>
 * 
 * @author Administrator
 * 
 */
public interface CoreDataSync {
	public void syncData(int businessType) throws Exception;
}
