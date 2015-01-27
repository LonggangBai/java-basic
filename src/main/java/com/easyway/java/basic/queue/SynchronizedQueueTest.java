package com.easyway.java.basic.queue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 使用同步队列  SynchronousQueue 实现生产者消费者
 * 该列队里虽然是列队 ，但是列队里面的元素永远只会为1个。即：存一个，取一个，存一个，取一个 ，...
 * 存对象时，如果列队里面有元素，则wait等待，直到列队为空
 * 取元素时，如果列队为空，则wait等待，直到列队非空
 */
public class SynchronizedQueueTest {

	/* 生产者 */
	class Producer implements Runnable{

		private BlockingQueue<String> queue ;

		private final String[] objects = new String[]{
				"one" ,"two" ,"three"
		}	;

		public Producer(BlockingQueue<String> queue ){
			this.queue = queue;
		}

		@Override
		public void run() {
			try{
				for (String s : objects) {
					System.out.printf("%s即将放入到队列中\n" , s);
					queue.put(s);
					System.out.printf("%s已经放入到队列中\n" , s);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	class Consumer implements Runnable{

		private BlockingQueue<String> queue ;

		public Consumer(BlockingQueue<String> queue ){
			this.queue = queue;
		}

		@Override
		public void run() {
			String obj = null;
			try {
				while(true){
					obj = queue.take();
					System.out.println("从队列中取出对象  " + obj);
					Thread.sleep(2000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		BlockingQueue<String> queue = new SynchronousQueue<String>(); //同步队列
		SynchronizedQueueTest st = new SynchronizedQueueTest();
		new Thread(st.new Consumer(queue)).start();
		new Thread(st.new Producer(queue)).start();
	}

}
