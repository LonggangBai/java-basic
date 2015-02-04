/**
 * Project Name:java-basic
 * File Name:ExecuteServiceTest.java
 * Package Name:com.easyway.java.concurrent
 * Date:2015-2-4下午3:14:16
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


/**
 * ClassName:ExecuteServiceTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-2-4 下午3:14:16 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ExecuteServiceTest {

    static class LiftOff implements Runnable {
        public void run() {
            System.out.println("LiftOff" + Thread.currentThread().getName());
        }
    }

    static class LiftOffCallable implements Callable<Integer> {
        public Integer call() {
            System.out.println("LiftOff" + Thread.currentThread().getName());
            return Integer.valueOf(Thread.activeCount());
        }
    }

    static class Daemon extends Thread {
        public Daemon(){
            setDaemon(true);
        }
        public void run() {
            System.out.println("Daemon" + Thread.currentThread().getName());
        }
    }


    // 该线程池是个可以回收利用的线程池，按需产生线程，可以被复用，线程可以闲置60秒，或者被销毁
    // 一般多线程程序，首选该线程池
    public static void testCachedThreadPool() {
        ExecutorService se = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            se.execute(new LiftOff());
        }
        // shutdown的作用是避免提交更多的线程任务
        se.shutdown();
    }


    // 该线程池产生固定个数的线程，并复用，不被销毁。
    // 好处是线程个数有限，即便是糟糕的程序也不能滥用资源
    public static void testFixedCacheThreadPool() {
        ExecutorService se = Executors.newFixedThreadPool(2);
        se.execute(new LiftOff());
        for (int i = 0; i < 4; i++) {
            se.execute(new LiftOff());
        }
    }


    /**
     * 1.该线程池只产生一个不停止的线程，每次调用线程任务都会等待上一次的任务完成
     * 比如写日志的模块，为了避免主程序等待IO操作，可以传入一个ExecutorService，写日志的时候就向这个线程池 的队列添加一个日志任务
     * 
     * 2.监听socket的任务，比如发送短信。短信的网关只有1个Socket出口，也就是同时只能发1条信息。但是界面提交信息数可能
     * 在某一时间大量任务，那么这时候可以采用这个线程池，让所有的任务进行排队。而不是在socket出口上对资源进行锁定。 避免了在资源同步上繁琐的处理。
     * 
     * 3.事件分发线程，响应事件的时候，采用同一接口分发事件。各个时间按照抛出的先后顺序排队，然后按次序分发
     * 
     * 某种意义上讲，这是一个单队列任务，大量线程任务使用同一资源时很有效
     */
    public static void testSingleThreadExecutor() {
        ExecutorService se = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 4; i++) {
            se.execute(new LiftOff());
        }
    }


    /**
     * 1.Callable返回的值可以是任意类型 2.采用ExecutorService.submit方法来执行
     * 3.Callable的Cancel必须在还未调用get方法的时候调用，不然cancel不了。 4.isDone方法可以判断是否有返回结果了
     * 5.get方法是个同步的方法，会一直等待返回结果，然后退出
     */
    public static void testCallableThread() {
        ExecutorService se = Executors.newCachedThreadPool();
        Future<Integer> f1 = se.submit(new LiftOffCallable());
        // f1.cancel(true);
        System.out.println(f1.isCancelled());
        System.out.println(f1.isDone());
        try {
            // System.out.println(f1.get());
            System.out.println(f1.isDone());
            System.out.println(f1.isCancelled());
            f1.cancel(true);
            System.out.println(f1.isCancelled());
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        se.shutdown();
    }


    /**
     * sleep与yield的相同点在于， 都会让当前线程放弃cpu，让其他线程执行。 sleep在指定的时间后才能执行，
     * yield则不预见什么时候能执行
     * 
     * sleep的机制在不同的平台实现有所不一样，尽量不要用。很多情况下，可以采用wait，或者yield来实现类似功能：
     * 1.比如不断查询某个结果是否有值 2...
     * 
     * wait不能和这2个混淆，不能因为名字也一样短，就认为有什么共同点
     * 
     * TimeUnit提供了操作线程状态的几个简略方法： TimeUnit.MILLISECONDS.
     */
    public void testSleep() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.timedJoin(new Thread(), 110l);
                TimeUnit.MILLISECONDS.timedWait(new Object(), 110l);
                TimeUnit.MILLISECONDS.sleep(10l);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    /*
     * 通常情况，操纵优先级都是一种错误 应该在run方法的开始部分设置，构造器里面设置没有什么好处
     * JDK有10个优先级，真正所有系统都支持的只有3个，MAX,MIN,NORM
     * 
     * volatile定义的变量，不能进行任何编译器优化
     */
    public void testThreadPriority() {
        Thread.currentThread().setPriority(1);
    }


    /*
     * yield能够促使各种不同的任务之间，产生良好的处理机制，除此之外，不能作其他用处
     * 
     * 需要注意的，我们的程序逻辑，不能依赖这个机制
     */
    public void testYield() {
        Thread.yield();
    }


    /*
     * Daemon是后台线程，只要还有非后台线程存在，那么Daemon就不会停止 Daemon提供的是通用服务线程，但它不是必不可少的部分
     * 
     * 
     * Daemon线程是为其他线程服务的，并不是程序的本地，比如说垃圾回收机制，当所有的线程都 运行完毕，垃圾回收Daemon才会停止。
     * 
     * 比如在main里面创建一系列后台线程，当main结束时候，所有后台线程也结束了
     * 
     * 不要再构造方法里面启动线程，这样有可能构造还没有结束就开始线程了，所以总是用Executor.execute来执行线程
     */
    public void testDaemon() {
        new Daemon();
    }


    /*
     * 创建同种特性的线程，可以只用ThreadFactory定义一次 比如，创建一批Daemon线程，都拥有最大的priority
     */
    public void testThreadFactory() {
        ExecutorService se = Executors.newSingleThreadExecutor(new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setPriority(Thread.MAX_PRIORITY);
                return t;
            }
        });
    }


    /*
     * 1.调用join的需求是，希望在某个线程中执行到一部分的时候，转入另外一个指定的线程，而当前
     * 2.线程挂起，等到另外一个线程执行完了以后，再恢复到到join的点，然后执行下去
     * 3.注意，join的必须是正在运行的线程，还未启动的线程，join的时候不会启动
     * 4.join的参数，指定一定得时间，如果还未返回，那么就自动结束挂起 5.join可以被interrupt, 比如：
     * 如果一个线程，join后长时间等待后，还没有返回结果，那么在其他线程里面调用这个线程的interrupt，可以结束join等待，继续执行下去
     * 6.网上有人说interrupt害人，应该是没有搞清楚interrupt的用处，没有测试，从字面意思理解为（结束，或者关闭）
     * 真实的意思，应该是让线程结束某个等待，然后顺利执行下去 7.如果是Executor执行的Thread，那么join后，将不能够interrupt
     */
    public static void testThreadJoin() {

        class TestJ extends Thread {
            public TestJ() {
                // 必须是join正在运行的线程
                start();
            }


            public void run() {
                int i = 10;
                while (i-- > 0) {

                    try {
                        TimeUnit.MILLISECONDS.sleep(500l);
                    }
                    catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("XXX");
                }
            }
        }

        Thread tx = new TestJ();

        class TestJ1 extends Thread {
            Thread t;


            public TestJ1(Thread tx) {
                t = tx;
            }


            public void run() {
                int i = 10;
                while (i > 0) {
                    if (i == 5) {
                        try {
                            t.join();
                        }
                        catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Integer.toString(i--));
                }
            }
        }

        ExecutorService se = Executors.newCachedThreadPool();
        Thread xx = new TestJ1(tx);
        se.execute(xx);
        // xx.start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000l);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        xx.interrupt();

    }

}
