/**
 * Project Name:java-basic
 * File Name:BlockingQueueDemo.java
 * Package Name:com.easyway.java.basic.collection.blockingqueue
 * Date:2015-3-20下午2:40:03
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.collection.blockingqueue;
/**
 * ClassName:BlockingQueueDemo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-20 下午2:40:03 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 一个简单的生产者-消费者demo
 * 有一点这里要说明 一下，其它 的在代码里我都 有简单的说明 了，这个demo比较简单，所以就不废话了。~~ 
NOTE：
 
 因为BlockingQueue接口扩展了Queue接口，所以它从Queue接口那里得到了一些简单的队列操作方法
  有个重载方法在使用的过程中应该注意。比如:offer(E)这个方法是从Queue那里得到的，它并不具有
  阻塞性，而BlokingQueue提供的重载方法offer(E,long,TimeUnit)这个方法是一个阻塞方法，它具有
 和put相似的功能，不过它提供了一个阻塞时间，时间过后如果还不能成功操作则放弃，返回false。
 同样的问题在poll()和poll(long,TimeUnit)之间也存在，在使用的过程中请务必留意。

 * @author KevinJom
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        new BlockingQueueDemo().go();
    }

    private void go() {
        // 这里简单的说一下BlockingQueue的实现,它基于生产者-消费者模式，其中有两个重要的阻塞方法
        // put()和take()，而这两个方法的实现用到了Lock和Condition，具体实现请参考API
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        Thread t1 = new Thread(new Producer(queue, 500, "peak")); // 生产者线程,来，生产一些i
                                                                    // can
                                                                    // play吧，并且要比nike生产的快
        Thread t2 = new Thread(new Producer(queue, 1000, "nike")); // 第二个生产者线程
        Thread t3 = new Thread(new Customer(queue)); // 消费者线程
        t1.start();
        t2.start();
        t3.start();
    }

    private class Producer implements Runnable {
        private BlockingQueue<String> queue;
        private int timeout; // 生产一个产品后暂停的时间
        private String category; // 仅仅起标记产品作用

        public Producer(BlockingQueue<String> queue, int timeout,
                String category) {
            super();
            this.queue = queue;
            this.timeout = timeout;
            this.category = category;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // put()方法也是一个会阻塞的方法，如果队列已满的时候这个方法会一起阻塞直到
                    // 队列中重新出现空间为止
                    queue.put("product " + category);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(timeout); // 每生产一个产品就暂停timeout毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Customer implements Runnable {
        private BlockingQueue<String> queue;

        public Customer(BlockingQueue<String> queue) {
            super();
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("product got:" + queue.take());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    // 暂停10毫秒，这里主要是为了证明take()是一个阻塞方法，如果 BlockingQueue中
                    // 没有元素，它会一起阻塞直到队列中有元素为止
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
