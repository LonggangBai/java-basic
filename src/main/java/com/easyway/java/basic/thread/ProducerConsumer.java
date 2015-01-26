package com.easyway.java.basic.thread;

/**
 * 五、生产者消费者问题
 * 
 * @author Administrator
 * 
 */
public class ProducerConsumer {
	public static void main(String[] args) {
		SyncStack ss = new SyncStack();
		PProducer p = new PProducer(ss);
		PConsumer c = new PConsumer(ss);
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(c).start();
	}
}

class WoTou {
	int id;

	WoTou(int id) {
		this.id = id;
	}

	public String toString() {
		return "WoTou : " + id;
	}
}

class SyncStack { // 栈实现
	int index = 0;
	WoTou[] arrWT = new WoTou[6]; // 相当于装物品的篮子

	public synchronized void push(WoTou wt) { // 生产物品，线程安全
		while (index == arrWT.length) { // 当篮子满了线程等待
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		this.notifyAll(); // 开始生产时，叫醒等待的其他线程开始消费
		arrWT[index] = wt;
		index++;
	}

	public synchronized WoTou pop() { // 消费物品，线程安全
		while (index == 0) { // 如果篮子空了
			try {
				this.wait(); // 线程等待,等待生产者开始
				// 生产，叫醒此线程
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		this.notifyAll(); // 消费时喊醒生产者生产
		index--;
		return arrWT[index];
	}
}

class PProducer implements Runnable { // 生产者类
	SyncStack ss = null;

	PProducer(SyncStack ss) {
		this.ss = ss;
	}

	public void run() {
		for (int i = 0; i < 20; i++) { // 生产20个
			WoTou wt = new WoTou(i);
			ss.push(wt);
			System.out.println("生产了：" + wt);
			try {
				Thread.sleep((int) (Math.random() * 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class PConsumer implements Runnable {
	SyncStack ss = null;

	PConsumer(SyncStack ss) {
		this.ss = ss;
	}

	public void run() {
		for (int i = 0; i < 20; i++) { // 消费20个
			WoTou wt = ss.pop();
			System.out.println("消费了: " + wt);
			try {
				Thread.sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}