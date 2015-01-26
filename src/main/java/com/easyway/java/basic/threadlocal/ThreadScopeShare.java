package com.easyway.java.basic.threadlocal;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程空间数据共享,在银行转账事务中有很大的作用
 */
public class ThreadScopeShare {
	/**
	 * 存放线程数据
	 */
	private static Map<Thread, Integer> threadData = new ConcurrentHashMap<Thread, Integer>();

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					// 将当前线程和数据绑定
					threadData.put(Thread.currentThread(), data);
					new Student1().getData();
					new Student2().getData();
					new Student3().getData();
				}
			}).start();
		}
	}

	private static class Student1 {
		public void getData() {
			System.out
					.println("Student1-->" + Thread.currentThread().getName()
							+ "-->get data-->"
							+ threadData.get(Thread.currentThread()));
		}
	}

	private static class Student2 {
		public void getData() {
			System.out
					.println("Student2-->" + Thread.currentThread().getName()
							+ "-->get data-->"
							+ threadData.get(Thread.currentThread()));
		}
	}

	private static class Student3 {
		public void getData() {
			System.out
					.println("Student3-->" + Thread.currentThread().getName()
							+ "-->get data-->"
							+ threadData.get(Thread.currentThread()));
		}
	}
}