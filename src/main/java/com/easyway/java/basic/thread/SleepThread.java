package com.easyway.java.basic.thread;

/**
 * 
 * @author Administrator
 * 
 */
public class SleepThread implements Runnable {
	/**
	 * 线程运行时执行的方法
	 */
	public void run() {
		try {
			// 该线程进入阻塞状态5秒
			Thread.sleep(5000);
			for (int i = 0; i < 500; i++) {
				System.out.println(Thread.currentThread().getName() + i);
			}
		} catch (InterruptedException e) {
			// 若调用该线程的interrupt方法，会报该异常，真实程序中可以关闭一些资源
			e.printStackTrace();
		}
	}

	/**
	 * 主线程
	 */
	public static void main(String[] args) {
		SleepThread tr = new SleepThread();
		Thread t = new Thread(tr);
		t.start();
		for (int i = 0; i < 500; i++) {
			System.out.println(Thread.currentThread().getName() + i);
		}
	}
}
