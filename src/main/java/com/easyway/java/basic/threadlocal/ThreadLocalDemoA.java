package com.easyway.java.basic.threadlocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 线程共享类的实现
 * 
 * @author ysjian
 * @version 1.0
 * @email ysjian_pingcx@126.com
 * @QQ 646633781
 * @telephone 18192235667
 * @csdnBlog http://blog.csdn.net/ysjian_pingcx
 * @createTime 2014-1-6
 * @copyRight Merit
 */
public class ThreadLocalDemoA {
	/**
	 * 一个ThreadLocal只能存放一个变量，需要放多个信息的时候，利用javaBena封装
	 */
	private static ThreadLocal<Integer> local = new ThreadLocal<Integer>();
	private static class A {
		public void getData() {
			System.out.println("A-->" + Thread.currentThread().getName()
					+ "-->get data-->" + local.get());
		}
	}
	private static class B {
		public void getData() {
			System.out.println("B-->" + Thread.currentThread().getName()
					+ "-->get data-->" + local.get());
		}
	}
	private static class C {
		public void getData() {
			System.out.println("C-->" + Thread.currentThread().getName()
					+ "-->get data-->" + local.get());
		}
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt(10);
					local.set(data);
					new A().getData();
					new B().getData();
					new C().getData();
				}
			});
		}
		service.shutdown();
	}
}