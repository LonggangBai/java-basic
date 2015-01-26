package com.easyway.java.basic.threadlocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 线程共享，经典的单例实现
 * 
 */
class ThreadLocalData {
	private static class A {
		public void getData() {
			// 改为
			System.out.println("A-->" + Thread.currentThread().getName()
					+ "get data-->" + ThreadLocalData.getThreadInstance());
		}
	}

	private static class B {
		public void getData() {
			// 改为
			System.out.println("A-->" + Thread.currentThread().getName()
					+ "get data-->" + ThreadLocalData.getThreadInstance());
		}
	}

	private static class C {
		public void getData() {
			// 改为
			System.out.println("A-->" + Thread.currentThread().getName()
					+ "get data-->" + ThreadLocalData.getThreadInstance());
		}
	}

	private String name;
	private int id;

	private ThreadLocalData() {
	}

	private static ThreadLocal<ThreadLocalData> threadLocal = new ThreadLocal<ThreadLocalData>();

	// 实现线程范围内共享的核心代码
	public static ThreadLocalData getThreadInstance() {
		ThreadLocalData instance = threadLocal.get();
		if (instance == null) {
			instance = new ThreadLocalData();
			threadLocal.set(instance);
		}
		return instance;
	}

	public ThreadLocalData(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ThreadLocalData [name=" + name + ", id=" + id + "]";
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt(10);
					// 改为
					ThreadLocalData.getThreadInstance().setId(data);
					ThreadLocalData.getThreadInstance()
							.setName("ysjian" + data);
					new A().getData();
					new B().getData();
					new C().getData();
				}
			});
		}
		service.shutdown();
	}

}