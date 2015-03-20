/**
 * Project Name:java-basic
 * File Name:DelayQueueTest.java
 * Package Name:com.easyway.java.basic.collection.blockingqueue
 * Date:2015-3-20下午4:19:41
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.collection.blockingqueue;

/**
 * ClassName:DelayQueueTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	
 * 
 * DelayQueue是实现Delayed接口的元素的一个无界阻塞队列，只有在延迟期满时才能从中提取元素。
 Delayed接口定义了getDelay(TimeUnit unit)方法。
 该队列的头部是延迟期满后保存时间最长的 Delayed 元素。如果延迟都还没有期满，则队列没有头部，
 并且 poll 将返回 null。当一个元素的getDelay(TimeUnit unit) 方法返回一个小于或等于零的值时，则出现期满。
 注意1：它是无界阻塞队列，容量是无限的。
 注意2:它是线程安全的，是阻塞的
 注意3：不允许使用 null 元素。 
 注意4：加入的元素必须实现了Delayed接口。
 public interface Delayed
 extends Comparable<Delayed>
 Delayed是一种混合风格的接口，用来标记那些应该在给定延迟时间之后执行的对象。
 此接口的实现必须定义一个 compareTo 方法，该方法提供与此接口的 getDelay 方法一致的排序。 
 注意5：对于put(E o)和offer(E o, long timeout, TimeUnit unit)，由于该队列是无界的，所以此方法永远不会阻塞。
 因此参数timeout和unit没意义，会被忽略掉。
 注意6：此类及其迭代器实现了 Collection 和 Iterator 接口的所有可选 方法。 
 * Date:     2015-3-20 下午4:19:41 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


public class DelayQueueTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        BlockingQueue<Task> queue = new DelayQueue();
        // TODO Auto-generated method stub
        for (int i = 0; i < 10; i++)
            new Thread(new QThreadProducer(queue)).start();
        for (int i = 0; i < 10; i++)
            new Thread(new QThreadConsumer(queue)).start();
    }
}


class Task implements Delayed {
    String name;
    long submitTime;


    Task(String taskName, long delayTime) {
        name = taskName;
        /*
         * conver the time from MILLISECONDS to NANOSECONDS *
         */
        submitTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }


    public long getDelay(TimeUnit unit) {
        System.out.println("get delay");
        return unit.convert(submitTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }


    public int compareTo(Delayed o) {
        System.out.println("compareTo");
        Task that = (Task) o;
        return submitTime > that.submitTime ? 1 : (submitTime < that.submitTime ? -1 : 0);
    }


    void doTask() {
        System.out.println("do task:" + name);
    }
}


class QThreadProducer implements Runnable {
    QThreadProducer(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    BlockingQueue<Task> queue;
    static int cnt = 0;


    public void run() {
        Task task;
        Random random = new Random(System.currentTimeMillis());
        while (true) {
            task = new Task("" + (cnt), random.nextInt() & 0xFFFF);
            cnt = (cnt + 1) & 0xFFFFFFFF;
            try {
                queue.put(task);
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class QThreadConsumer implements Runnable {
    QThreadConsumer(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    BlockingQueue<Task> queue;


    public void run() {
        Task task;
        while (true) {
            try {
                task = queue.take();
                task.doTask();
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
