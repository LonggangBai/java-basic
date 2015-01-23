package com.easyway.java.basic.threadlocal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <pre>
 * 如果一个对象要被多个线程访问，而该对象存在类变量被不同类方法读写，为获得线程安全，可以用ThreadLocal来替代类变量。 
 * 
 *        同步机制的比较　　ThreadLocal和线程同步机制相比有什么优势呢？ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题。 
 * 
 * 　　在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。这时该变量是多个线程共享的，使用同步机制要求程序慎密地分析什么时候对变量进行读写，
 * 什么时候需要锁定某个对象，什么时候释放对象锁等繁杂的问题，程序设计和编写难度相对较大。 
 * 
 * 　　而ThreadLocal则从另一个角度来解决多线程的并发访问。ThreadLocal会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突。
 * 因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。ThreadLocal提供了线程安全的共享对象，在编写多线程代码时，可以把不安全的变量封装进ThreadLocal。 
 * 
 * 　　由于ThreadLocal中可以持有任何类型的对象，低版本JDK所提供的get()返回的是Object对象，需要强制类型转换。但JDK 5.0通过泛型很好的解决了这个问题，
 * 在一定程度地简化ThreadLocal的使用，代码清单 9 2就使用了JDK 5.0新的ThreadLocal版本。 
 * 
 * 　　概括起来说，对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式。前者仅提供一份变量，让不同的线
 * 程排队访问，而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响。 
 * 
 * 　　Spring使用ThreadLocal解决线程安全问题 
 * 
 * 　　我们知道在一般情况下，只有无状态的Bean才可以在多线程环境下共享，在Spring中，绝大部分Bean都可以声明为singleton作用域。就是因为Spring对一些
 * Bean（如RequestContextHolder、TransactionSynchronizationManager、LocaleContextHolder等）中非线程安全状态采用ThreadLocal
 * 进行处理，让它们也成为线程安全的状态，因为有状态的Bean就可以在多线程中共享了。 
 * 
 * 　　一般的Web应用划分为展现层、服务层和持久层三个层次，在不同的层中编写对应的逻辑，下层通过接口向上层开放功能调用。在一般情况下，从接收请求到返回响应
 * 所经过的所有程序调用都同属于一个线程，如图9‑2所示： 
 * 
 * 　　这样你就可以根据需要，将一些非线程安全的变量以ThreadLocal存放，在同一次请求响应的调用线程中，所有关联的对象引用到的都是同一个变量。 
 * 
 * 　　下面的实例能够体现Spring对有状态Bean的改造思路： 
 * 
 * 　　代码清单3 TopicDao：非线程安全
 * 
 * 　代码清单3 TopicDao：非线程安全 
 * 
 * 　　public class TopicDao { 
 * 
 * 　　private Connection conn;①一个非线程安全的变量 
 * 
 * 　　public void addTopic(){ 
 * 
 * 　　Statement stat = conn.createStatement();②引用非线程安全变量 
 * 
 * 　　… 
 * 
 * 　　} 
 * 
 * 　　} 
 * 
 * 　　由于①处的conn是成员变量，因为addTopic()方法是非线程安全的，必须在使用时创建一个新TopicDao实例（非singleton）。下面使用ThreadLocal对
 * conn这个非线程安全的“状态”进行改造：
 * 
 * 不同的线程在使用TopicDao时，先判断connThreadLocal.是否是null，如果是null，则说明当前线程还没有对应的Connection对象，这时创建一个
 * Connection对象并添加到本地线程变量中；如果不为null，则说明当前的线程已经拥有了Connection对象，直接使用就可以了。这样，就保证了不同的线程
 * 使用线程相关的Connection，而不会使用其它线程的Connection。因此，这个TopicDao就可以做到singleton共享了。 
 * 
 * 　　当然，这个例子本身很粗糙，将Connection的ThreadLocal直接放在DAO只能做到本DAO的多个方法共享Connection时不发生线程安全问题，但无法和其
 * 它DAO共用同一个Connection，要做到同一事务多DAO共享同一Connection，必须在一个共同的外部类使用ThreadLocal保存Connection。 
 * 
 *      小结　　ThreadLocal是解决线程安全问题一个很好的思路，它通过为每个线程提供一个独立的变量副本解决了变量并发访问的冲突问题。在很多情况下，
 *      ThreadLocal比直接使用synchronized同步机制解决线程安全问题更简单，更方便，且结果程序拥有更高的并发性。 
 * 结论就是：在spring管理【ThreadLocal管理的类变量，他也仅仅是在管理变量而已】的，要访问线程不安全的对象中的变量的时候都会将原来的对象copy一份，
 * 进而访问这个copy 
 * 
 * 版本，所以从来就没有机会访问原来的对象，反而这个原来应该被访问的对象倒是成了闲置、冗余的对象了
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class TopicDao {

	// ①使用ThreadLocal保存Connection变量

	private static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<Connection>();

	public static Connection getConnection() {

		// ②如果connThreadLocal没有本线程对应的Connection创建一个新的Connection， 并将其保存到线程本地变量中。

		if (connThreadLocal.get() == null) {

			Connection conn = null;// ConnectionManager.getConnection();

			connThreadLocal.set(conn);

			return conn;

		} else {

			return connThreadLocal.get();// ③直接返回线程本地变量

		}

	}

	public void addTopic() {

		// ④从ThreadLocal中获取线程对应的Connection

		try {
			Statement stat = getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
