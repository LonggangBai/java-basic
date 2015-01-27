package com.easyway.java.basic.executors;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 门插销计数器 启动线程，然后等待线程结束。即常用的主线程等所有子线程结束后再执行的问题。
 * JDK1.4时，常用办法是给子线程设置状态，主线程循环检测。易用性和效率都不好。
 * @author Administrator
 * 
 */
public class CountDownLatchMain {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		final int count = 10;
		final CountDownLatch completeLatch = new CountDownLatch(count);// 定义了门插销的数目是10

		for (int i = 0; i < count; i++) {
			Thread thread = new Thread("worker thread" + i) {
				public void run() {
					// do xxxx
					completeLatch.countDown();// 减少一根门插销
				}
			};
			thread.start();
		}
		completeLatch.await();// 如果门插销还没减完则等待。
	}
}
