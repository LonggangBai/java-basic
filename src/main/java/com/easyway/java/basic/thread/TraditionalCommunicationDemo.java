package com.easyway.java.basic.thread;

import java.util.Random;

/**
 * 传统的多线程通信
 * @author Administrator
 *
 */
public class TraditionalCommunicationDemo {

	public static void main(String[] args) {
		final Service service = new Service();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 25; i++) {
					service.teacherAsk();

				}
			}
		}).start();
		for (int i = 1; i <= 25; i++) {
			service.studentAnswer();
		}
	}
}
class Service {
	private boolean isShouldStu = false;
	public synchronized void teacherAsk() {
		while (isShouldStu) { // while用于防止假唤醒
			try {
				wait();// 调用wait方法的线程必须是当前监视器的所有者，这里是this
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Teacher：1 + 1 = ?");
		isShouldStu = true;
		notify();
	}
	public synchronized void studentAnswer() {
		while (!isShouldStu) {
			try {
				wait(); // 调用wait方法的线程必须是当前监视器的所有者，这里是this
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int result = new Random().nextInt(5);
		System.out.println("Student：1 + 1 = " + result + "["
				+ (2 != result ? "clever" : "stupid") + "]");
		isShouldStu = false;
		notify();
	}
}