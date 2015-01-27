package com.easyway.java.basic.sync;

import java.io.*;

public class PipedStreamTestA {
	public static void main(String[] args) {
		PipedOutputStream ops = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream();
		try {
			ops.connect(pis);// 实现管道连接
			RProducer p = new RProducer(ops);
			new Thread(p).start();
			RConsumer c = new RConsumer(pis);
			new Thread(c).start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

// 生产者
class RProducer implements Runnable {
	private PipedOutputStream ops;

	public RProducer(PipedOutputStream ops) {
		this.ops = ops;
	}

	public void run() {
		try {
			for (;;) {
				ops.write("hell,spell".getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (ops != null) {
				try {
					ops.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

// 消费者
class RConsumer implements Runnable {
	private PipedInputStream pis;

	public RConsumer(PipedInputStream pis) {
		this.pis = pis;
	}

	public void run() {
		try {
			for (;;) {
				byte[] bu = new byte[100];
				int len = pis.read(bu);
				System.out.println(new String(bu, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pis != null) {
				try {
					pis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}