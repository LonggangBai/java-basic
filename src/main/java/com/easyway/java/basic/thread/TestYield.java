package com.easyway.java.basic.thread;

/**
 * <pre>
 * 二、join和yield方法 
 * t.join(); //t的run()方法完才会继续执行当前线程方法体
 * //也就是两个线程变成了一个线程
 * t.yield(); //暂停当前正在执行的线程对象，并执行其他线程。方法为静态
 * //哪个线程体执行此方法，哪个线程让步
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class TestYield {
	public static void main(String[] args) {
		MyThread3 t1 = new MyThread3("t1");
		MyThread3 t2 = new MyThread3("t2");
		t1.start();
		t2.start();
	}
}

class MyThread3 extends Thread {
	MyThread3(String s) {
		super(s);
	}

	public void run() {
		for (int i = 1; i <= 100; i++) {
			System.out.println(getName() + ": " + i);
			if (i % 10 == 0) {
				yield();
			}
		}
	}
}