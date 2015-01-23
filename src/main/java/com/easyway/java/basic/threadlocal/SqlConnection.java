package com.easyway.java.basic.threadlocal;

import java.sql.Connection;

import java.sql.SQLException;

import java.sql.Statement;

/**
 * <pre>
 * Spring使用ThreadLocal解决线程安全问题
 *  我们知道在一般情况下，只有无状态的Bean才可以在多线程环境下共享，在Spring中，绝大部分Bean都可以声明为singleton作用域。
 *  就是因为Spring对一些Bean（如RequestContextHolder、TransactionSynchronizationManager、LocaleContextHolder等）
 *  中非线程安全状态采用ThreadLocal进行处理，让它们也成为线程安全的状态，因为有状态的Bean就可以在多线程中共享了。
 *  一般的Web应用划分为展现层、服务层和持久层三个层次，在不同的层中编写对应的逻辑，下层通过接口向上层开放功能调用。在一般情况下，
 *  从接收请求到返回响应所经过的所有程序调用都同属于一个线程，如图9?2所示：
 *  
 * 图1同一线程贯通三层
 *  这样你就可以根据需要，将一些非线程安全的变量以ThreadLocal存放，在同一次请求响应的调用线程中，所有关联的对象引用到的都是同一个变量。
 *  下面的实例能够体现Spring对有状态Bean的改造思路：
 *  
 *  
 *  由于①处的conn是成员变量，因为addTopic()方法是非线程安全的，必须在使用时创建一个新TopicDao实例（非singleton）。
 *  下面使用ThreadLocal对conn这个非线程安全的“状态”进行改造：
 *  代码清单4 TopicDao：线程安全
 *  
 *  Spring中Singleton模式的线程安全
 *  
 *  
 *  
 *  
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class SqlConnection {
	// ①使用ThreadLocal保存Connection变量
	private static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<Connection>();

	public static Connection getConnection() {
		// ②如果connThreadLocal没有本线程对应的Connection创建一个新的Connection，
		// 并将其保存到线程本地变量中。
		if (connThreadLocal.get() == null) {
			Connection conn = getConnection();
			connThreadLocal.set(conn);
			return conn;
		} else {
			return connThreadLocal.get();
			// ③直接返回线程本地变量
		}
	}

	public void addTopic() {
		// ④从ThreadLocal中获取线程对应的Connection
		try {
			Statement stat = getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
