/**
 * Project Name:java-basic
 * File Name:AtomicTest.java
 * Package Name:com.easyway.java.concurrent
 * Date:2015-2-4下午4:21:30
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.concurrent;

/**
 * 
 * <pre>
 * 原子操作AtomicInteger

 Java代码  收藏代码
 public class AtomicLong extends Number    
 implements Serializable  


 J2SE 5.0提供了一组atomic class来帮助我们简化同步处理。
 基本工作原理是使用了同步synchronized的方法实现了对一个long, integer, 对象的增、减、赋值（更新）操作. 比如对于++运算符, AtomicInteger可以将它持有的integer 能够atomic 地递增。在需要访问两个或两个以上 atomic变量的程序代码（或者是对单一的atomic变量执行两个或两个以上的操作）通常都需要被synchronize以便两者的操作能够被当作是一个atomic的单元。 

 简单的说，这些类都是线程安全的，支持无阻塞无锁定的 

 Java代码 
 addAndGet(long delta)   
 //以原子方式将给定值添加到当前值。  

 getAndSet() :   
 //设置新值，返回旧值.   

 compareAndSet(expectedValue, newValue) :   
 //如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值  
 //如果更新成功，返回true, 否则返回false  
 //换句话可以这样说: 将原子变量设置为新的值, 但是如果从我上次看到的这个变量之后到现在被其他线程修改了(和我期望看到的值不符), 那么更新失败   
 /*  单线程下, compareAndSet返回永远为true,       
 *  多线程下, 在与result进行compare时, counter可能被其他线程set了新值, 这时需要重新再取一遍再比较,       
 *  如果还是没有拿到最新的值, 则一直循环下去, 直到拿到最新的那个值  

 为了提高性能,AtomicLong等类在实现同步时,没有用synchronized关键字,而是直接使用了最低层(本地c语言实现代码)来完成的,
 因而你是看不到用synchronized关键字的.
 比如:AtomicLong类中原子操作方法:
 public final boolean compareAndSet(long expect, long update) ;
 就是直接使用SUN公司低层本地代码的原子方法(native方法):
 public final native boolean compareAndSwapLong(...)来实现的.

 其中NonSafeSeq是作为对比的类，直接放一个private long count不是线程安全的，而SafeSeq里面放了一个AtomicLong,是线程安全的；可以直接调用incrementAndGet来增加 

 运行代码，可以得到类似这样的结果 
 finished : 1 
 finished : 0 
 finished : 3 
 finished : 2 
 finished : 5 
 finished : 4 
 finished : 6 
 finished : 8 
 finished : 9 
 finished : 7 
 both have finished.... 
 NonSafeSeq:91723 
 SafeSeq with atomic: 100000 

 可以看到，10个线程，每个线程运行了10,000次，理论上应该有100,000次增加，使用了普通的long是非线程安全的，而使用了AtomicLong是线程安全的； 

 注意，这个例子也说明，虽然long本身的单个设置是原子的，要么成功要么不成功，但是诸如count++这样的操作就不是线程安全的；因为这包括了读取和写入两步操作；

 Java代码  收藏代码
 set()    
 get()    
 getAndSet()    
 getAndIncrement()    
 getAndDecrement()    
 getAndAdd()  
 * </pre>
 * ClassName:AtomicTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-2-4 下午4:21:30 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;


public class AtomicTest {

    public void testAtomic() {
    
        final int loopcount = 10000;
        int threadcount = 10;

        final NonSafeSeq seq1 = new NonSafeSeq();
        final SafeSeq seq2 = new SafeSeq();

        final CountDownLatch l = new CountDownLatch(threadcount);

        for (int i = 0; i < threadcount; ++i) {
            final int index = i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < loopcount; ++j) {

                        seq1.inc();
                        seq2.inc();
                    }

                    System.out.println("finished : " + index);
                    l.countDown();

                }
            }).start();
        }

        try {
            l.await();
        }
        catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println("both have finished....");

        System.out.println("NonSafeSeq:" + seq1.get());
        System.out.println("SafeSeq with atomic: " + seq2.get());

    }
}


class NonSafeSeq {
    private long count = 0;


    public void inc() {
        count++;
    }


    public long get() {
        return count;
    }
}


class SafeSeq {
    private AtomicLong count = new AtomicLong(0);


    public void inc() {
        count.incrementAndGet();
    }


    public long get() {
        return count.longValue();
    }
}