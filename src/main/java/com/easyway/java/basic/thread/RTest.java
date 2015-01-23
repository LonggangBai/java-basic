package com.easyway.java.basic.thread;

/* ================================================================================== 
 * 文件：ThreadDemo07.java 
 * 描述：生产者－－消费者 
 * 注：其中的一些注释是我根据自己的理解加注的 
 * ================================================================================== 
 */
/**
 * <pre>
 * 四、Java的等待通知机制
 * 　　在有些时候，我们需要在几个或多个线程中按照一定的秩序来共享一定的资源。例如生产者－－消费者的关系，
 * 在这一对关系中实际情况总是先有生产者生产了产品后，消费者才有可能消费；又如在父－－子关系中，总是先有父亲，
 * 然后才能有儿子。然而在没有引入等待通知机制前，我们得到的情况却常常是错误的。这里我引入《用线程获得强大的功能》
 * 一文中的生产者－－消费者的例子：
 * 
 * 
 * 　在以上的程序中，模拟了生产者和消费者的关系，生产者在一个循环中不断生产了从Ａ－Ｚ的共享数据，而消费者则不断地消费生产者生产的Ａ－Ｚ的共享数据。我们开始已经说过，在这一对关系中，必须先有生产者生产，才能有消费者消费。但如果运行我们上面这个程序，结果却出现了在生产者没有生产之前，消费都就已经开始消费了或者是生产者生产了却未能被消费者消费这种反常现象。为了解决这一问题，引入了等待通知（wait/notify)机制如下：
 * 　　１、在生产者没有生产之前，通知消费者等待；在生产者生产之后，马上通知消费者消费。
 * 　　２、在消费者消费了之后，通知生产者已经消费完，需要生产。
 * 下面修改以上的例子（源自《用线程获得强大的功能》一文）：
 * </pre>
 * 
 * @author Administrator
 * 
 */
// 共享的数据对象
class RShareData {
	private char c;

	public void setShareChar(char c) {
		this.c = c;
	}

	public char getShareChar() {
		return this.c;
	}
}

// 生产者线程
class RProducer extends Thread {

	private RShareData s;

	RProducer(RShareData s) {
		this.s = s;
	}

	public void run() {
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			try {
				Thread.sleep((int) Math.random() * 4000);
			} catch (InterruptedException e) {
			}

			// 生产
			s.setShareChar(ch);
			System.out.println(ch + " producer by producer.");
		}
	}
}

// 消费者线程
class RConsumer extends Thread {

	private RShareData s;

	RConsumer(RShareData s) {
		this.s = s;
	}

	public void run() {
		char ch;

		do {
			try {
				Thread.sleep((int) Math.random() * 4000);
			} catch (InterruptedException e) {
			}
			// 消费
			ch = s.getShareChar();
			System.out.println(ch + " consumer by consumer.");
		} while (ch != 'Z');
	}
}

public class RTest {
	public static void main(String argv[]) {
		RShareData s = new RShareData();
		new RConsumer(s).start();
		new RProducer(s).start();
	}
}