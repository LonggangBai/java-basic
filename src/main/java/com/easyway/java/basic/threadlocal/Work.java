package com.easyway.java.basic.threadlocal;
/**
 * <pre>
 * spring 的singleton 和prototype的区别和应用场合？
 * singleton作用域：
 * 		当把一个Bean定义设置为singleton作用域是，Spring IoC容器中只会存在一个共享的Bean实例，并且所有对Bean的请求，
 * 只要id与该Bean定义相匹配，则只会返回该Bean的同一实例。值得强调的是singleton作用域是Spring中的缺省作用域。
 * 
 * prototype作用域：
 * 		prototype作用域的Bean会导致在每次对该Bean请求（将其注入到另一个Bean中，或者以程序的方式调用容器的getBean()方法）
 * 时都会创建一个新的Bean实例。根据经验，对有状态的Bean应使用prototype作用域，而对无状态的Bean则应该使用singleton作用域。
 * 对于具有prototype作用域的Bean，有一点很重要，即Spring不能对该Bean的整个生命周期负责。具有prototype作用域的Bean创建
 * 后交由调用者负责销毁对象回收资源。简单的说：singleton 只有一个实例，也即是单例模式。prototype访问一次创建一个实例，相当
 * 于new。 
 * 
 * 应用场合：
 * 1.需要回收重要资源(数据库连接等)的事宜配置为singleton，如果配置为prototype需要应用确保资源正常回收。
 * 2.有状态的Bean配置成singleton会引发未知问题，可以考虑配置为prototype。
 * </pre>
 * @author Administrator
 *
 */
public class Work extends Thread {

	private QuerySvc querySvc;

	private String sql;

	public Work(QuerySvc querySvc, String sql) {

		this.querySvc = querySvc;

		this.sql = sql;

	}

	public void run() {

		querySvc.setSql(sql);

		querySvc.execute();

	}

	public static void main(String[] args) {
		QuerySvc qs = new QuerySvc();

		for (int k = 0; k < 10; k++) {
			String sql = "Select * from table where id =" + k;

			new Work(qs, sql).start();

		}

	}
}
