package com.easyway.java.basic.queue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者 消费者  LinkedBlockingQueue 实现 
 * 该列队取出对象时，如果列队为空，就一直wait等待。直到列队非空。
 * 存入对象时，如果列队已满，就一直wait等待。直到列队非满  
 */
public class LinkedBlockingQueueTest {
	
	public int MAX_SIZE ;

	private BlockingQueue<String> queue ;
	
	public LinkedBlockingQueueTest(int max_size){
		this.MAX_SIZE = max_size;
		queue = new LinkedBlockingQueue<String>(MAX_SIZE);
	}
	
	public void buildProducer(){
		new Thread(new Producer(queue)).start();
	}
	
	
	public void buildConsumer(){ 
		new Thread(new Consumer(queue)).start();
	}
	
	/* 生产者 */
	class Producer implements Runnable{
		BlockingQueue<String> queue ; 
		public Producer(BlockingQueue<String> queue){
			this.queue = queue;
		}
		
		@Override
		public void run() {
			int i = 0 ;
			while(true){
				try {   
					System.out.println("即将存");
					queue.put( "包子"  + ++i);	//存
					System.out.println("生产了一个包子 ，包子数量：" + queue.size());
					if(queue.size() == MAX_SIZE){
						System.out.println("已满");
					}
				} catch (InterruptedException e) {  
					e.printStackTrace();
				}
			}
		}
	}
	
	/*消费者*/
	class Consumer implements Runnable{
		
		BlockingQueue<String> queue ; 
		public Consumer(BlockingQueue<String> queue){
			this.queue = queue;
		}
		
		@Override
		public void run() {
			try {
				System.out.println("即将取");
				queue.take();	//取
				if(queue.size() == 0){  
					System.out.println("取出了一个包子 ，包子数量：" + queue.size());
					System.out.println("已空");
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {  
				e.printStackTrace();
			}	
		}
	}
	
	public static void main(String[] args) {
		LinkedBlockingQueueTest pac = new LinkedBlockingQueueTest(5);
		pac.buildProducer();
		pac.buildConsumer();
	}

}
