package com.easyway.java.basic.thread;

public class JoinThreads implements Runnable {
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + i);
		}
	}

	public static void main(String[] args) {
		JoinThreads jt = new JoinThreads();
		Thread t = new Thread(jt);
		t.start();
		try {
			// 调用该方法将当前线程（此处是主线程）合并到本线程中，执行完本线程，再执行当前线程
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + i);
		}
	}
}
