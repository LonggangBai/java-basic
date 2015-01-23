package com.easyway.java.basic.thread;

public class ThreadSample {

	private int count = 0;

	public synchronized void add() {
		count++;
		System.out.println(Thread.currentThread().getName() + "  " + count);
	}

	public synchronized void desc() {
		count--;
		System.out.println(Thread.currentThread().getName() + "  " + count);
	}

	class ThreadAdd extends Thread {
		@Override
		public void run() {
				add();
		}
	};

	class ThreadDesc implements Runnable {
		public void run() {
				desc();
		}
	};

	public static void main(String[] args) {
		
		for (int i = 0; i < 100; i++) {
			ThreadSample t = new ThreadSample();
			ThreadAdd add = t.new ThreadAdd();
			ThreadDesc desc = t.new ThreadDesc();
			Thread tdesc = new Thread(desc);
			add.start();
			tdesc.start();
		}
		
	}
}
