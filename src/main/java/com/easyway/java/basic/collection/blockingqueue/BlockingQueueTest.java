/**
 * Project Name:java-basic
 * File Name:BlockingQueueTest.java
 * Package Name:com.easyway.java.basic.collection.blockingqueue
 * Date:2015-3-20下午4:17:08
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.collection.blockingqueue;

/**
 * ClassName:BlockingQueueTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 本例介绍一个特殊的队列：BlockingQueue，如果BlockingQueue是空的，从BlockingQueue取东西的操作将会被阻断进入等待状态，直到BlockingQueue进了东西才会被唤醒，同样，
 如果BlockingQueue是满的，任何试图往里存东西的操作也会被阻断进入等待状态，直到BlockingQueue里有空间时才会被唤醒继续操作。
 本例再次实现前面介绍的篮子程序，不过这个篮子中最多能放得苹果数不是1，可以随意指定。当篮子满时，生产者进入等待状态，当篮子空时，消费者等待。

 BlockingQueue定义的常用方法如下：
 add(anObject)：把anObject加到BlockingQueue里，如果BlockingQueue可以容纳，则返回true，否则抛出异常。
 offer(anObject)：表示如果可能的话，将anObject加到BlockingQueue里，即如果BlockingQueue可以容纳，则返回true，否则返回false。
 put(anObject)：把anObject加到BlockingQueue里，如果BlockingQueue没有空间，则调用此方法的线程被阻断直到BlockingQueue里有空间再继续。
 poll(time)：取走BlockingQueue里排在首位的对象，若不能立即取出，则可以等time参数规定的时间，取不到时返回null。
 take()：取走BlockingQueue里排在首位的对象，若BlockingQueue为空，阻断进入等待状态直到BlockingQueue有新的对象被加入为止。

 BlockingQueue有四个具体的实现类，根据不同需求，选择不同的实现类：
 ArrayBlockingQueue：规定大小的BlockingQueue，其构造函数必须带一个int参数来指明其大小。其所含的对象是以FIFO（先入先出）顺序排序的。
 LinkedBlockingQueue：大小不定的BlockingQueue，若其构造函数带一个规定大小的参数，生成的BlockingQueue有大小限制，若不带大小参数，
 所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定。其所含的对象是以FIFO顺序排序的。
 PriorityBlockingQueue：类似于LinkedBlockingQueue,但其所含对象的排序不是FIFO，而是依据对象的自然排序顺序或者是构造函数所带的Comparator决定的顺序。
 SynchronousQueue：特殊的BlockingQueue，对其的操作必须是放和取交替完成的。

 LinkedBlockingQueue和ArrayBlockingQueue比较起来，它们背后所用的数据结构不一样，导致LinkedBlockingQueue的数据吞吐量要大于ArrayBlockingQueue，
 但在线程数量很大时其性能的可预见性低于ArrayBlockingQueue。
 * Date:     2015-3-20 下午4:17:08 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/** */
/**
 * BlockingQueue是一种特殊的Queue，若BlockingQueue是空的，
 * 从BlockingQueue取东西的操作将会被阻断进入等待状态直到BlocingkQueue进了新货才会被唤醒。
 * 同样，如果BlockingQueue是满的任何试图往里存东西的操作也会被阻断进入等待状态， 直到BlockingQueue里有新的空间才会被唤醒继续操作。
 * BlockingQueue提供的方法主要有： add(anObject):
 * 把anObject加到BlockingQueue里，如果BlockingQueue可以容纳返回true
 * ，否则抛出IllegalStateException异常。
 * offer(anObject)：把anObject加到BlockingQueue里，如果BlockingQueue可以容纳返回true
 * ，否则返回false。 put(anObject)：把anObject加到BlockingQueue里，如果BlockingQueue没有空间，
 * 调用此方法的线程被阻断直到BlockingQueue里有新的空间再继续。
 * poll(time)：取出BlockingQueue里排在首位的对象，若不能立即取出可等time参数规定的时间。取不到时返回null。
 * take()：取出BlockingQueue里排在首位的对象
 * ，若BlockingQueue为空，阻断进入等待状态直到BlockingQueue有新的对象被加入为止。
 * 
 * 根据不同的需要BlockingQueue有4种具体实现：
 * （1）ArrayBlockingQueue：规定大小的BlockingQueue，其构造函数必须带一个int参数来指明其大小
 * 。其所含的对象是以FIFO（先入先出）顺序排序的。
 * （2）LinkedBlockingQueue：大小不定的BlockingQueue，若其构造函数带一个规定大小的参数
 * ，生成的BlockingQueue有大小限制，
 * 若不带大小参数，所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定。其所含的对象是以FIFO（先入先出）顺序排序的。
 * LinkedBlockingQueue和ArrayBlockingQueue比较起来，它们背后所用的数据结构不一样，
 * 导致LinkedBlockingQueue的数据吞吐量要大于ArrayBlockingQueue
 * ，但在线程数量很大时其性能的可预见性低于ArrayBlockingQueue。
 * （3）PriorityBlockingQueue：类似于LinkedBlockingQueue
 * ，但其所含对象的排序不是FIFO，而是依据对象的自然排序顺序或者是构造函数所带的Comparator决定的顺序。
 * （4）SynchronousQueue：特殊的BlockingQueue，对其的操作必须是放和取交替完成的。
 * 
 * 下面是用BlockingQueue来实现Producer和Consumer的例子
 */
public class BlockingQueueTest {

    /** */
    /**
     * 定义装苹果的篮子
     */
    public static class Basket {
        // 篮子，能够容纳3个苹果
        BlockingQueue<String> basket = new ArrayBlockingQueue<String>(3);


        // 生产苹果，放入篮子
        public void produce() throws InterruptedException {
            // put方法放入一个苹果，若basket满了，等到basket有位置
            basket.put("An apple");
        }


        // 消费苹果，从篮子中取走
        public String consume() throws InterruptedException {
            // get方法取出一个苹果，若basket为空，等到basket有苹果为止
            return basket.take();
        }
    }


    // 　测试方法
    public static void testBasket() {
        // 建立一个装苹果的篮子
        final Basket basket = new Basket();
        // 定义苹果生产者
        class Producer implements Runnable {
            public void run() {
                try {
                    while (true) {
                        // 生产苹果
                        System.out.println("生产者准备生产苹果：" + System.currentTimeMillis());
                        basket.produce();
                        System.out.println("生产者生产苹果完毕：" + System.currentTimeMillis());
                        // 休眠300ms
                        Thread.sleep(300);
                    }
                }
                catch (InterruptedException ex) {
                }
            }
        }
        // 定义苹果消费者
        class Consumer implements Runnable {
            public void run() {
                try {
                    while (true) {
                        // 消费苹果
                        System.out.println("消费者准备消费苹果：" + System.currentTimeMillis());
                        basket.consume();
                        System.out.println("消费者消费苹果完毕：" + System.currentTimeMillis());
                        // 休眠1000ms
                        Thread.sleep(1000);
                    }
                }
                catch (InterruptedException ex) {
                }
            }
        }

        ExecutorService service = Executors.newCachedThreadPool();
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        service.submit(producer);
        service.submit(consumer);
        // 程序运行5s后，所有任务停止
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
        }
        service.shutdownNow();
    }


    public static void main(String[] args) {
        BlockingQueueTest.testBasket();
    }
}