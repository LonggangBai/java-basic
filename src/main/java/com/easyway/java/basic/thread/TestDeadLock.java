package com.easyway.java.basic.thread;

/**
 * <pre>
 * 三、线程优先级别 
 * 线程的优先级用数字表示，范围从1到10，一个线程的缺省优先级为5.
 * Thread.MAX_PRIORITY=1
 * Thread.MIN_PRIORITY=10
 * Thread.NORM_PRIORITY=5
 * 例：t.setPriority(Thread.NORM_PRIORITY+3);
 * 
 * 四、线程同步
 * 1.同步代码块
 * synchronized(this){  //在执行代码块过程中，不会被其他线程打断
 * ...  
 * }
 * public sunchronized void method //执行此方法时，当前对象被锁定
 * 在Java语言中，引入了对象互斥锁的概念，保证共享数据操作的完整性，每个对象 都对应一个可称为"互斥锁"的标记，这个标记保证在任一时刻，只能有一个线程访 问该对象。
 * 2.线程死锁
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class TestDeadLock implements Runnable {
	public int flag = 1;
	static Object o1 = new Object(), o2 = new Object();

	public void run() {
		System.out.println("flag=" + flag);
		if (flag == 1) {
			synchronized (o1) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized (o2) {
					System.out.println("1");
				}
			}
		}
		if (flag == 0) {
			synchronized (o2) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized (o1) {
					System.out.println("0");
				}
			}
		}
	}

	public static void main(String[] args) {
		TestDeadLock td1 = new TestDeadLock();
		TestDeadLock td2 = new TestDeadLock();
		td1.flag = 1;
		td2.flag = 0;
		Thread t1 = new Thread(td1);
		Thread t2 = new Thread(td2);
		t1.start();
		t2.start();

	}
}