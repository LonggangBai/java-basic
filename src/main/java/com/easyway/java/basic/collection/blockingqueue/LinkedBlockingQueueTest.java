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
 * LinkedBlockingQueue是一个基于已链接节点的、范围任意的blocking queue的实现。
  此队列按 FIFO（先进先出）排序元素。队列的头部 是在队列中时间最长的元素。队列的尾部 是在队列中时间最短的元素。
  新元素插入到队列的尾部，并且队列检索操作会获得位于队列头部的元素。链接队列的吞吐量通常要高于基于数组的队列，
  但是在大多数并发应用程序中，其可预知的性能要低。
 可选的容量范围构造方法参数作为防止队列过度扩展的一种方法。
 如果未指定容量，则它等于 Integer.MAX_VALUE。除非插入节点会使队列超出容量，否则每次插入后会动态地创建链接节点。
构造函数
Public Constructors
LinkedBlockingDeque()
Creates a LinkedBlockingDeque with a capacity of MAX_VALUE.
LinkedBlockingDeque(int capacity)
Creates a LinkedBlockingDeque with the given (fixed) capacity.
LinkedBlockingDeque(Collection<? extends E> c)
Creates a LinkedBlockingDeque with a capacity of MAX_VALUE, initially containing the elements of the given collection, added in traversal order of the collection's iterator.
 注意1:容量范围可以在构造方法参数中指定作为防止队列过度扩展。如果未指定容量，则它等于 Integer.MAX_VALUE
 注意2:它是线程安全的，是线程阻塞的。
 注意3：不接受 null 元素
 注意4:它实现了BlockingQueue接口。关于BlockingQueue，请参考《BlockingQueue》
 注意5：它没有线程ArrayBlockingQueue那样的公平性设置。为什么这样设计呢？puzzle.
 注意6：此类及其迭代器实现了 Collection 和 Iterator 接口的所有可选 方法。
 注意7：在JDK5/6中，LinkedBlockingQueue和ArrayBlocingQueue等对象的poll(long timeout, TimeUnit unit)存在内存泄露
   Leak的对象是AbstractQueuedSynchronizer.Node，
   据称JDK5会在Update12里Fix，JDK6会在Update2里Fix。
   更加详细参考http://sesame.javaeye.com/blog/428026和 http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=2143840 

关于LinkedBlockingQueue的使用请参考《ArrayBlockingQueue》和《BlockingQueue》

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

