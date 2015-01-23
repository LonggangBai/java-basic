package com.easyway.java.basic.threadlocal;

/**
 * <pre>
 * android中的Looper类，使用了ThreadLocal保存每个线程的Looper static final ThreadLocal<Looper>
 * sThreadLocal = new ThreadLocal<Looper>(); 简单了解下ThreadLocal的概念和使用。
 * 
 * （一）概念 ThreadLocal为每个线程提供变量的副本，该变量线程间独立不影响。
 * 
 * 
 * 
 * 运行结果：
 * Thread name is Thread-0,num is 1
 * Thread name is Thread-1,num is 1
 * Thread name is Thread-1,num is 2
 * Thread name is Thread-1,num is 3
 * Thread name is Thread-0,num is 2
 * Thread name is Thread-0,num is 3
 * 
 * （三）使用分析
 * ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题。ThreadLocal提供变量拷贝，线程间独立，访问效率高，用“空间换时间”；线程同步机制对变量进行线程间访问互斥锁，变量多线程共享，访问效率低，处理负责，用“时间换空间”。
 * ThreadLocal不是用来解决变量线程共享安全的，而是提供了保存对象的方法。
 * 
 * （四）源码分析
 * 查看源码，每个Thread中都带有ThreadLocal.ThreadLocalMap变量
 * 在ThreadLocal中将变量和Thread通过map绑定
 * 在CODE上查看代码片派生到我的代码片
 * public void set(T value) {  
 *         Thread t = Thread.currentThread();  
 *         //获取ThreadLocalMap绑定该线程和对象变量  
 *         ThreadLocalMap map = getMap(t);  
 *         if (map != null)  
 *             map.set(this, value);  
 *         else  
 *             createMap(t, value);  
 *     }  
 * ThreadLocalMap getMap(Thread t) {  
 *         return t.threadLocals;  
 *     }
 * 
 * 
 * </pre>
 * 
 * ThreadLocal用法测试
 * 
 * @author peter_wang
 * @create-time 2014-12-24 下午8:15:47
 */
public class ThreadLocalDemo {
	private static ThreadLocal<Integer> num = new ThreadLocal<Integer>() {
		// 提供初始化，不实现初始化方法首次使用get()的时候也需要初始化对象
		protected Integer initialValue() {
			return 0;
		};
	};

	private static class ThreadLocalThread extends Thread {

		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				num.set(num.get() + 1);
				System.out.println("Thread name is " + getName() + ",num is "
						+ num.get());
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadLocalThread thread1 = new ThreadLocalThread();
		thread1.start();
		ThreadLocalThread thread2 = new ThreadLocalThread();
		thread2.start();
	}

}