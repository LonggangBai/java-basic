package com.easyway.java.basic.thread;

public class YieldThread extends Thread {
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + i);
			if (i % 10 == 0) {
				// 当能被10整除时本线程让出优先级，让其他线程先执行一会，可看到t1_10下面紧接着t2的结果，同样t2_20下面紧接着t1的结果
				yield();
			}
		}
	}

	public static void main(String[] args) {
		YieldThread yt = new YieldThread();
		Thread t1 = new Thread(yt, "t1_");
		t1.start();
		Thread t2 = new Thread(yt, "t2_");
		t2.start();
	}
}
