/**
 * Project Name:java-basic
 * File Name:LinkedBlockingQueueTest.java
 * Package Name:com.easyway.java.basic.collection.blockingqueue
 * Date:2015-3-20上午10:20:45
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.collection.blockingqueue;
/**
 * ClassName:LinkedBlockingQueueTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-20 上午10:20:45 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 利用LinkedBlockingQueue实现的生产者/消费者模式  
 * ClassName: LinkedBlockingQueueTest <br/>
 * date: 2015-3-20 上午10:21:21 <br/>
 *
 * @author Administrator
 * @version 
 * @since JDK 1.6
 */
public class LinkedBlockingQueueTest {
    /**
    * 生产者
    * @author wucheng
    *
    */
    class Producer implements Runnable{
        LinkedBlockingQueue<String> queue;
        public Producer(LinkedBlockingQueue<String> queue){
            this.queue = queue;
        }
        
        @Override
        public void run() {
            while(true){
                String s = UUID.randomUUID().toString();
                System.out.println(System.currentTimeMillis()+"：生产出-->"+s);
                queue.offer(s);
                try {
                    Thread.sleep(1000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    /**
    * 消费者
    * @author wucheng
    *
    */
    class Consumer implements Runnable{
        LinkedBlockingQueue<String> queue;
        public Consumer(LinkedBlockingQueue<String> queue){
            this.queue = queue;
        }
        @Override
        public void run() {
            while(true){
                String s = null;
                try {
                    s = queue.poll(10, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+"：消费出-->"+s+" 剩余未消费个数"+queue.size());
            }
        }
        
    }
    
    public static void main(String[] args) throws Throwable {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        Producer producer = new LinkedBlockingQueueTest().new Producer(queue);
        Consumer consumer = new LinkedBlockingQueueTest().new Consumer(queue);
        System.out.println("开始生产消费");
        for(int i=0;i<10;i++){
            //定义10个生产者
            Thread t = new Thread(producer);
            t.start();
        }
        
        //定义1个消费者
        Thread t = new Thread(consumer);
        t.start();
    }
}

