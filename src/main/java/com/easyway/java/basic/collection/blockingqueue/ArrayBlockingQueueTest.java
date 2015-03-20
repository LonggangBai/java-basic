/**
 * Project Name:java-basic
 * File Name:ArrayBlockingQueueTest.java
 * Package Name:com.easyway.java.basic.collection.blockingqueue
 * Date:2015-3-20下午4:18:41
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.collection.blockingqueue;

/**
 * ClassName:ArrayBlockingQueueTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 
 * 
 * ArrayBlockingQueue
 一个由数组支持的有界阻塞队列。它的本质是一个基于数组的blocking queue的实现。
 它的容纳大小是固定的。此队列按 FIFO（先进先出）原则对元素进行排序。
 队列的头部 是在队列中存在时间最长的元素。队列的尾部 是在队列中存在时间最短的元素。
 新元素插入到队列的尾部，队列检索操作则是从队列头部开始获得元素。 
 这是一个典型的“有界缓存区”，固定大小的数组在其中保持生产者插入的元素和使用者提取的元素。
 一旦创建了这样的缓存区，就不能再增加其容量。
 试图向已满队列中放入元素会导致放入操作受阻塞，直到BlockingQueue里有新的空间才会被唤醒继续操作；
 试图从空队列中检索元素将导致类似阻塞，直到BlocingkQueue进了新货才会被唤醒。 
 此类支持对等待的生产者线程和使用者线程进行排序的可选公平策略。
 默认情况下，不保证是这种排序。然而，通过在构造函数将公平性 (fairness) 设置为 true 而构造的队列允许按照 FIFO 顺序访问线程。
 公平性通常会降低吞吐量，但也减少了可变性和避免了“不平衡性”。 
 此类及其迭代器实现了 Collection 和 Iterator 接口的所有可选 方法。
 注意1：它是有界阻塞队列。它是数组实现的，是一个典型的“有界缓存区”。数组大小在构造函数指定，而且从此以后不可改变。
 注意2:是它线程安全的，是阻塞的，具体参考BlockingQueue的“注意4”。
 注意3:不接受 null 元素
 注意4：公平性 (fairness)可以在构造函数中指定。
Public Constructors
ArrayBlockingQueue(int capacity)
Creates an ArrayBlockingQueue with the given (fixed) capacity and default access policy.
ArrayBlockingQueue(int capacity, boolean fair)
Creates an ArrayBlockingQueue with the given (fixed) capacity and the specified access policy.
ArrayBlockingQueue(int capacity, boolean fair, Collection<? extends E> c)
Creates an ArrayBlockingQueue with the given (fixed) capacity, the specified access policy and initially containing the elements of the given collection, added in traversal order of the collection's iterator.
 如果为true，则按照 FIFO 顺序访问插入或移除时受阻塞线程的队列；如果为 false，则访问顺序是不确定的。
  注意5:它实现了BlockingQueue接口。关于BlockingQueue，请参照《BlockingQueue》
  注意6：此类及其迭代器实现了 Collection 和 Iterator 接口的所有可选 方法。
  注意7：其容量在构造函数中指定。容量不可以自动扩展，也没提供手动扩展的接口。
  注意8：在JDK5/6中，LinkedBlockingQueue和ArrayBlocingQueue等对象的poll(long timeout, TimeUnit unit)存在内存泄露
   Leak的对象是AbstractQueuedSynchronizer.Node，
   据称JDK5会在Update12里Fix，JDK6会在Update2里Fix。
   更加详细参考http://sesame.javaeye.com/blog/428026和 http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=2143840 
 实例1：
 * Date:     2015-3-20 下午4:18:41 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ArrayBlockingQueueTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(100);
        // TODO Auto-generated method stub
        for (int i = 0; i < 10; i++)
            new Thread(new ThreadProducer(queue)).start();
        for (int i = 0; i < 10; i++)
            new Thread(new ThreadConsumer(queue)).start();
    }
}


class ThreadProducer implements Runnable {
    ThreadProducer(BlockingQueue queue) {
        this.queue = queue;
    }

    BlockingQueue queue;
    static int cnt = 0;


    public void run() {
        String cmd;
        while (true) {
            cmd = "" + (cnt);
            cnt = (cnt + 1) & 0xFFFFFFFF;
            try {
                queue.put(cmd);
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class ThreadConsumer implements Runnable {
    ThreadConsumer(BlockingQueue queue) {
        this.queue = queue;
    }

    BlockingQueue queue;


    public void run() {
        String cmd;
        while (true) {
            try {
                System.out.println(queue.take());
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}