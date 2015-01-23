package com.easyway.java.basic.thread;

/* ================================================================================== 
 * 文件：ThreadDemo08.java 
 * 描述：生产者－－消费者 
 * 注：其中的一些注释是我根据自己的理解加注的 
 * ================================================================================== 
 */
/**
 * <pre>
 * 
 * 在以上程序中，设置了一个通知变量，每次在生产者生产和消费者消费之前，都测试通知变量，检查是否可以生产或消费。
 * 最开始设置通知变量为true，表示还未生产，在这时候，消费者需要消费，于时修改了通知变量，调用notify()发出通知。
 * 这时由于生产者得到通知，生产出第一个产品，修改通知变量，向消费者发出通知。这时如果生产者想要继续生产，但因为检
 * 测到通知变量为false，得知消费者还没有生产，所以调用wait()进入等待状态。因此，最后的结果，是生产者每生产一个，
 * 就通知消费者消费一个；消费者每消费一个，就通知生产者生产一个，所以不会出现未生产就消费或生产过剩的情况。
 * </pre>
 * 
 * @author Administrator
 * 
 */
class WShareData {

	private char c;
	// 通知变量
	private boolean writeable = true;

	// -------------------------------------------------------------------------
	// 需要注意的是：在调用wait()方法时，需要把它放到一个同步段里，否则将会出现
	// "java.lang.IllegalMonitorStateException: current thread not owner"的异常。
	// -------------------------------------------------------------------------
	public synchronized void setShareChar(char c) {
		if (!writeable) {
			try {
				// 未消费等待
				wait();
			} catch (InterruptedException e) {
			}
		}

		this.c = c;
		// 标记已经生产
		writeable = false;
		// 通知消费者已经生产，可以消费
		notify();
	}

	public synchronized char getShareChar() {
		if (writeable) {
			try {
				// 未生产等待
				wait();
			} catch (InterruptedException e) {
			}
		}
		// 标记已经消费
		writeable = true;
		// 通知需要生产
		notify();
		return this.c;
	}
}

// 生产者线程
class WProducer extends Thread {

	private WShareData s;

	WProducer(WShareData s) {
		this.s = s;
	}

	public void run() {
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			try {
				Thread.sleep((int) Math.random() * 400);
			} catch (InterruptedException e) {
			}

			s.setShareChar(ch);
			System.out.println(ch + " producer by producer.");
		}
	}
}

// 消费者线程
class WConsumer extends Thread {

	private WShareData s;

	WConsumer(WShareData s) {
		this.s = s;
	}

	public void run() {
		char ch;

		do {
			try {
				Thread.sleep((int) Math.random() * 400);
			} catch (InterruptedException e) {
			}

			ch = s.getShareChar();
			System.out.println(ch + " consumer by consumer.**");
		} while (ch != 'Z');
	}
}

class Test {
	public static void main(String argv[]) {
		WShareData s = new WShareData();
		new WConsumer(s).start();
		new WProducer(s).start();
	}
}