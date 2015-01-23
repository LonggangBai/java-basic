package com.easyway.java.basic.threadlocal;

/**
 * <pre>
 * 然代码清单9‑3这个ThreadLocal实现版本显得比较幼稚，但它和JDK所提供的ThreadLocal类在实现思路上是相近的。 
 * 
 * 在Java的多线程编程中，为保证多个线程对共享变量的安全访问，通常会使用synchronized来保证同一时刻只有一个线程对共享变量进行操作。但在有些情况下，synchronized不能保证多线程对共享变量的正确读写。例如类有一个类变量，该类变量会被多个类方法读写，当多线程操作该类的实例对象时，如果线程对类变量有读取、写入操作就会发生类变量读写错误，即便是在类方法前加上synchronized也无效，因为同一个线程在两次调用方法之间时锁是被释放的，这时其它线程可以访问对象的类方法，读取或修改类变量。这种情况下可以将类变量放到ThreadLocal类型的对象中，使变量在每个线程中都有独立拷贝，不会出现一个线程读取变量时而被另一个线程修改的现象
 * 
 * 　　为了说明多线程访问对于类变量和ThreadLocal变量的影响，QuerySvc中分别设置了类变量sql和ThreadLocal变量，使用时先创建 QuerySvc的一个实例对象，然后产生多个线程，分别设置不同的sql实例对象，然后再调用execute方法，读取sql的值，看是否是set方法中写入的值。这种场景类似web应用中多个请求线程携带不同查询条件对一个servlet实例的访问，然后servlet调用业务对象，并传入不同查询条件，最后要保证每个请求得到的结果是对应的查询条件的结果。 
 * 
 * 先创建一个QuerySvc实例对象，然后创建若干线程来调用QuerySvc的set和execute方法，每个线程传入的sql都不一样，从运行结果可以看出sql变量中值不能保证在execute中值和set设置的值一样，在 web应用中就表现为一个用户查询的结果不是自己的查询条件返回的结果，而是另一个用户查询条件的结果；而ThreadLocal中的值总是和set中设置的值一样，这样通过使用ThreadLocal获得了线程安全性。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class QuerySvc {

	private String sql;

	private static ThreadLocal sqlHolder = new ThreadLocal();

	public QuerySvc() {

	}

	public void execute() {

		System.out.println("Thread " + Thread.currentThread().getId()
				+ " Sql is " + sql);

		System.out.println("Thread " + Thread.currentThread().getId()
				+ " Thread Local variable Sql is " + sqlHolder.get());

	}

	public String getSql() {

		return sql;

	}

	public void setSql(String sql) {

		this.sql = sql;

		sqlHolder.set(sql);

	}

}