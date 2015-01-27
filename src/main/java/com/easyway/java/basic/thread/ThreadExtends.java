package com.easyway.java.basic.thread;

/**
 * 继承Thread类
 * 
 * @author Administrator
 * 
 */
public class ThreadExtends extends Thread {
	/**
	 * 线程运行时执行的方法
	 */
	public void run() {
		for (int i = 0; i < 500; i++) {
			System.out.println(Thread.currentThread().getName() + i);
		}
	}

	/**
	 * 主线程，启动线程必须是start方法
	 */
	public static void main(String[] args) {
		ThreadExtends tr = new ThreadExtends();
		tr.start();
		for (int i = 0; i < 500; i++) {
			System.out.println(Thread.currentThread().getName() + i);
		}
	}
}
