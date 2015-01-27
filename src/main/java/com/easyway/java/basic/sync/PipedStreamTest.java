package com.easyway.java.basic.sync;

import java.io.*;

/**
 * <pre>
 *  利用管道进行线程间通信
 * 原理简单。两个线程，一个操作PipedInputStream,一个操作 PipedOutputStream。PipedOutputStream写入的数
 * 据先缓存在Buffer中,如果 Buffer满，此线程wait。PipedInputStream读出Buffer中的数据，如果Buffer 没数据，
 * 此线程wait。
 * 
 * jdk1.5中的阻塞队列可实现同样功能。
 * 
 * 例1 这个例子实际上只是单线程，还谈不上线程间通信，但不妨一看。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class PipedStreamTest {
	public static void main(String[] args) {
		PipedOutputStream ops = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream();
		try {
			ops.connect(pis);// 实现管道连接
			new IProducer(ops).run();
			new IConsumer(pis).run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

// 生产者
class IProducer implements Runnable {
	private PipedOutputStream ops;

	public IProducer(PipedOutputStream ops) {
		this.ops = ops;
	}

	public void run() {
		try {
			ops.write("hell,spell".getBytes());
			ops.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// 消费者
class IConsumer implements Runnable {
	private PipedInputStream pis;

	public IConsumer(PipedInputStream pis) {
		this.pis = pis;
	}

	public void run() {
		try {
			byte[] bu = new byte[100];
			int len = pis.read(bu);
			System.out.println(new String(bu, 0, len));
			pis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}