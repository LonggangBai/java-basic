package com.easyway.java.basic.thread;

public class PriorityThread extends Thread {
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + i);
		}
	}

	/**
	 * 线程优先级默认是5
	 */
	public static void main(String[] args) {
		int norm = Thread.NORM_PRIORITY; // 5
		int max = Thread.MAX_PRIORITY; // 10
		int min = Thread.MIN_PRIORITY; // 1
		PriorityThread yt = new PriorityThread();
		Thread t1 = new Thread(yt, "t1_");
		t1.setPriority(norm + 3);
		t1.start();
		Thread t2 = new Thread(yt, "t2_");
		t2.start();
	}
}
