/**
 * Project Name:java-basic
 * File Name:BlockingQueueTest2.java
 * Package Name:com.easyway.java.basic.collection.blockingqueue
 * Date:2015-3-20上午10:02:06
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.collection.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 用阻塞队列ArrayBlockingQueue或LinkedBlockingQueue实现实现生产者与消费者 阻塞队列BlockingQueue
 * 
 * 一、概述： BlockingQueue作为线程容器，可以为线程同步提供有力的保障。
 * 
 * 
 * 二、BlockingQueue定义的常用方法 1.BlockingQueue定义的常用方法如下:
 * 
 * 抛出异常 特殊值 阻塞 超时 插入 add(e) offer(e) put(e) offer(e, time, unit) 移除 remove()
 * poll() take() poll(time, unit) 检查 element() peek() 不可用 不可用
 * 
 * 1)add(anObject):把anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则招聘异常
 * 
 * 2)offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,
 * 则返回true,否则返回false.
 * 
 * 3)put(anObject):把anObject加到BlockingQueue里,如果BlockQueue没有空间,
 * 则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续.
 * 
 * 4)poll(time):取走BlockingQueue里排在首位的对象,若不能立即取出,则可以等time参数规定的时间,取不到时返回null
 * 
 * 5)take():取走BlockingQueue里排在首位的对象,若BlockingQueue为空,
 * 阻断进入等待状态直到Blocking有新的对象被加入为止
 * 
 * 其中：BlockingQueue 不接受null 元素。试图add、put 或offer 一个null
 * 元素时，某些实现会抛出NullPointerException。null 被用作指示poll 操作失败的警戒值。
 * 
 * 
 * 
 * 三、BlockingQueue的几个注意点
 * 
 * 【1】BlockingQueue 可以是限定容量的。它在任意给定时间都可以有一个remainingCapacity，超出此容量，便无法无阻塞地put
 * 附加元素。没有任何内部容量约束的BlockingQueue 总是报告Integer.MAX_VALUE 的剩余容量。 【2】BlockingQueue
 * 实现主要用于生产者-使用者队列，但它另外还支持Collection 接口。因此，举例来说，使用remove(x)
 * 从队列中移除任意一个元素是有可能的。然而，这种操作通常不 会有效执行，只能有计划地偶尔使用，比如在取消排队信息时。 【3】BlockingQueue
 * 实现是线程安全的。所有排队方法都可以使用内部锁或其他形式的并发控制来自动达到它们的目的。然而，大量的 Collection
 * 操作（addAll、containsAll、retainAll 和removeAll）没有
 * 必要自动执行，除非在实现中特别说明。因此，举例来说，在只添加了c 中的一些元素后，addAll(c) 有可能失败（抛出一个异常）。
 * 【4】BlockingQueue 实质上不
 * 支持使用任何一种“close”或“shutdown”操作来指示不再添加任何项。这种功能的需求和使用有依赖于实现的倾向
 * 。例如，一种常用的策略是：对于生产者，插入特殊的end-of-stream 或poison 对象，并根据使用者获取这些对象的时间来对它们进行解释。
 * 
 * 四、简要概述BlockingQueue常用的四个实现类
 * 
 * 
 * 
 * 
 * 
 * 1)ArrayBlockingQueue:规定大小的BlockingQueue,其构造函数必须带一个int参数来指明其大小.其所含的对象是以FIFO(
 * 先入先出)顺序排序的.
 * 
 * 2)LinkedBlockingQueue:大小不定的BlockingQueue,若其构造函数带一个规定大小的参数,
 * 生成的BlockingQueue有大小限制
 * ,若不带大小参数,所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定.其所含的对象是以FIFO(先入先出)顺序排序的
 * 
 * 3)PriorityBlockingQueue:类似于LinkedBlockQueue,但其所含对象的排序不是FIFO,
 * 而是依据对象的自然排序顺序或者是构造函数的Comparator决定的顺序.
 * 
 * 4)SynchronousQueue:特殊的BlockingQueue,对其的操作必须是放和取交替完成的.
 * 
 * 其中LinkedBlockingQueue和ArrayBlockingQueue比较起来,它们背后所用的数据结构不一样,
 * 导致LinkedBlockingQueue的数据吞吐量要大于ArrayBlockingQueue
 * ,但在线程数量很大时其性能的可预见性低于ArrayBlockingQueue.
 * 
 * 
 * 
 * 五、具体BlockingQueue的实现类的内部细节
 * 
 * 有耐心的同学请看具体实现类细节：
 * 
 * 1、ArrayBlockingQueue
 * 
 * ArrayBlockingQueue是一个由数组支持的有界阻塞队列。此队列按 FIFO（先进先出）原则对元素进行排序。队列的头部
 * 是在队列中存在时间最长的元素。队列的尾部 是在队列中存在时间最短的元素。新元素插入到队列的尾部，队列检索操作则是从队列头部开始获得元素。
 * 
 * 这是一个典型的“有界缓存区”，固定大小的数组在其中保持生产者插入的元素和使用者提取的元素。一旦创建了这样的缓存区，就不能再增加其容量。
 * 试图向已满队列中放入元素会导致放入操作受阻塞；试图从空队列中检索元素将导致类似阻塞。
 * 
 * ArrayBlockingQueue创建的时候需要指定容量capacity(可以存储的最大的元素个数，因为它不会自动扩容)以及是否为公平锁(fair参数)
 * 。
 * 
 * 在创建ArrayBlockingQueue的时候默认创建的是非公平锁，不过我们可以在它的构造函数里指定。
 * 这里调用ReentrantLock的构造函数创建锁的时候，调用了：
 * 
 * public ReentrantLock(boolean fair) {
 * 
 * sync = (fair)? new FairSync() : new NonfairSync();
 * 
 * }
 * 
 * FairSync/ NonfairSync是ReentrantLock的内部类：
 * 
 * 线程按顺序请求获得公平锁，而一个非公平锁可以闯入，且当它尚未进入等待队列，就会和等待队列head结点的线程发生竞争，如果锁的状态可用，
 * 请求非公平锁的线程可在等待队列中向前跳跃，获得该锁。内部锁synchronized没有提供确定的公平性保证。
 * 
 * 分三点来讲这个类：
 * 
 * 2.1 添加新元素的方法：add/put/offer
 * 
 * 2.2 该类的几个实例变量：takeIndex/putIndex/count/
 * 
 * 2.3 Condition实现
 * 
 * 
 * 下面是用BlockingQueue来实现Producer和Consumer的例子
 */
public class BlockingQueueTest2 {

    /**
     * 定义装苹果的篮子
     */
    public static class Basket {
        // 篮子，能够容纳3个苹果
        // BlockingQueue<String> basket = new ArrayBlockingQueue<String>(3);
        BlockingQueue<String> basket = new LinkedBlockingQueue<String>(3);


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
            public String instance = "";


            public Producer(String a) {
                instance = a;
            }


            public void run() {
                try {
                    while (true) {
                        // 生产苹果
                        System.out.println("生产者准备生产苹果：" + instance);
                        basket.produce();
                        System.out.println("! 生产者生产苹果完毕：" + instance);
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
            public String instance = "";


            public Consumer(String a) {
                instance = a;
            }


            public void run() {
                try {
                    while (true) {
                        // 消费苹果
                        System.out.println("消费者准备消费苹果：" + instance);
                        basket.consume();
                        System.out.println("! 消费者消费苹果完毕：" + instance);
                        // 休眠1000ms
                        Thread.sleep(1000);
                    }
                }
                catch (InterruptedException ex) {
                }
            }
        }

        ExecutorService service = Executors.newCachedThreadPool();
        Producer producer = new Producer("P1");
        Producer producer2 = new Producer("P2");
        Consumer consumer = new Consumer("C1");
        service.submit(producer);
        service.submit(producer2);
        service.submit(consumer);

        // 程序运行3s后，所有任务停止
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
        }

        service.shutdownNow();
    }


    public static void main(String[] args) {
        BlockingQueueTest2.testBasket();
    }
}
