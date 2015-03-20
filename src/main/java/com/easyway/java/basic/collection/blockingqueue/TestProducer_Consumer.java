/**
 * Project Name:java-basic
 * File Name:TestProducer_Consumer.java
 * Package Name:com.easyway.java.basic.collection.blockingqueue
 * Date:2015-3-20下午1:27:47
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.collection.blockingqueue;

/**
 * ClassName:TestProducer_Consumer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	LinkedBlockingQueue实现是线程安全的，实现了FIFO（先进先出）等特性. 是作为生产者消费者的首选，LinkedBlockingQueue 可以指定容量，也可以不指定，不指定的话，
 * 默认最大是Integer.MAX_VALUE，其中主要用到put和take方法，put方法在队列满的时候会阻塞直到有队列成员被消费，take方法在队列空的时候会阻塞，直到有队列成员被放进来。

 书本上的话不再重复, 还是看看实例代码.

 工厂生产制造  生产高大上洒, 还有美女.

 消费者有X二代,也有导演.

 让消费者抢资源吧.
 * Date:     2015-3-20 下午1:27:47 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 生产者类
 */
class MProducer implements Runnable {
    private BlockingQueue<String> queue;
    private String produce;


    public MProducer(BlockingQueue<String> queue, String produce) {
        this.queue = queue;
        if (null != produce)
            this.produce = produce;
        else
            this.produce = "null ";
    }


    @Override
    public void run() {
        String uuid = UUID.randomUUID().toString();
        try {
            Thread.sleep(200);// 生产需要时间
            queue.put(produce + " : " + uuid);
            System.out.println("Produce \"" + produce + "\" : " + uuid + " " + Thread.currentThread());

        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}


/* 消费者类 */
class MConsumer implements Runnable {
    private BlockingQueue<String> queue;
    private String consumer;


    public MConsumer(BlockingQueue<String> queue, String consumer) {
        this.queue = queue;
        if (null != consumer)
            this.consumer = consumer;
        else
            this.consumer = "null ";
    }


    @Override
    public void run() {
        try {
            String uuid = queue.take();
            System.out.println(consumer + " decayed " + uuid + " " + Thread.currentThread());
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}


public class TestProducer_Consumer {

    public static void main(String[] args) {
        // 队列
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            service.submit(new MConsumer(queue, "X二代" + i));
            service.submit(new MConsumer(queue, "导演" + i));
        }
        for (int i = 0; i < 6; i++) {
            service.submit(new MProducer(queue, "黄金酒," + i));
            service.submit(new MProducer(queue, "美女演员" + i));
        }
        service.shutdown();

    }

}
