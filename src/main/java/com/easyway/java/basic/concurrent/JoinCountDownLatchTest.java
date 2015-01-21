/**
 * Project Name:java-basic
 * File Name:JoinCountDownLatchTest.java
 * Package Name:com.easyway.java.basic.concurrent
 * Date:2015-1-21上午9:51:35
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.concurrent;

/**
 * 
 * 并发工具类（一）等待多线程完成的CountDownLatch 简介
 * 
 * CountDownLatch 允许一个或多个线程等待其他线程完成操作。
 * 
 * 应用场景
 * 
 * 假如有这样一个需求，当我们需要解析一个Excel里多个sheet的数据时，可以考虑使用多线程，每个线程解析一个sheet里的数据，
 * 等到所有的sheet都解析完之后 ，程序需要提示解析完成。在这个需求中，要实现主线程等待所有线程完成sheet的解析操作，最简单的做法是使用join。
 * 
 * 多线程线程 java
 * 
 * <pre>
 * 1.java线程面试题
 *  
 * java线程面试题
 *  
 * 1.实现线程的方法，有什么区别
 *  继承Thread与实现Runnable接口。
 *   启动方法不一样。Thread1继承，Thread2实现Runnable接口，则启动一
 *  
 * 个Thread1线程可以使用new Thread1().start(),而启动Thread2线程则new 
 * 
 * Thread(new Thread2()).start()。
 *   
 * 2.可以使用run方法启动一个线程吗？
 *  启动一个线程应该使用start方法，线程的run方法可以直接调用，但是
 *  
 * 不会启动一个新的线程，只是在原来的线程中调用了run方法而已。
 *  
 * 3.sleep方法与wait方法的区别，带时间参数时有什么区别?
 *   sleep()是线程的静态方法，使当前线程(即调用该方法的线程)暂停执
 *  
 * 行一段时间，让其他线程有机会继续执行，但它并不释放对象锁。也就是如果有
 *  
 * Synchronized同步块，其他线程仍然不可以访问共享数据。
 *  wait()方法使当前线程暂停执行并释放对象锁标志，让其他线程可以进
 *  
 * 入Synchronized数据块，当前线程被放入对象等待池中。对象。
 *  
 * 4.join() ，join(long mills)
 *  join()方法使调用该方法的线程在此之前执行完毕，也就是等待调用该方法的线
 *  
 * 程执行完毕后再往下继续执行。注意该方法也要捕获异常。join(mills)最长等
 *  
 * 待mills时间。
 *  
 * 5.wait()，wait(long timeout)和notify()、notifyAll() 
 * 这三个方法用于协调多个线程对共享数据的存取，所以必须在
 *  
 * Synchronized语句块内使用这三个方法。Synchronized这个关键字用于保护共享
 *  
 * 数据，阻止其他线程对共享数据的存取。 
 * wait()方法使当前线程暂停执行并释放对象锁标志，让其他线程可以进
 *  
 * 入Synchronized数据块，当前线程被放入对象等待池中。
 *  wait(timeout)等待timeout长的时间，如果这段时间过了还没有被唤醒
 *  
 * ，则自动唤醒。
 *   当调用 notify()方法后，将从对象的等待池中移走一个任意的线程并
 *  
 * 放到锁标志等待池中，只有 锁标志等待池中的线程能够获取锁标志；如果锁标
 *  
 * 志等待池中没有线程，则notify()不起作用。 
 * notifyAll()则从对象等待池中移走所有等待那个对象的线程并放到锁
 *  
 * 标志等待池中。 
 *  注意 这三个方法都是java.lang.Ojbect的方法!
 *  
 * 6.yield() 
 *  yield()方法暂停当前线程的执行，其它的线程有执行的机会 
 * 
 * 7.当一个线程进入一个对象的一个synchronized方法后，其它线程是否可进入此对象的其它方法?
 * 分情况而定。假设两个方法test()与test2()是一个类的两个方法，test是这个类的对象，现在thread1访问test()而thread2访问test2()方法。
 * 若test与test2中没有访问相同的对象，则可以，如果test与test2访问了相同的对象，则不可以。
 *  这里是要明白synchronized锁定的是什么。synchronized块锁定的是它后面的括号里面的内容，而synchronized方法锁定的是该方法中访问的所有的对象。
 *  ----------------------------------------------------------------------
 *  网上面试题摘录
 *  
 * 60、java中有几种方法可以实现一个线程？用什么关键字修饰同步方法? stop()和suspend()方法为何不推荐使用？
 * 答：有两种实现方法，分别是继承Thread类与实现Runnable接口。用synchronized关键字修饰同步方法
 *  反对使用stop()，是因为它不安全。它会解除由线程获取的所有锁定，而且如果对象处于一种不连贯状态，那么其他线程能在那种状态下检查和修改它们。结果很难检查出真正的问题所在。suspend()方法容易发生死锁。调用suspend()的时候，目标线程会停下来，但却仍然持有在这之前获得的锁定。此时，其他任何线程都不能访问锁定的资源，除非被"挂起"的线程恢复运行。对任何线程来说，如果它们想恢复目标线程，同时又试图使用任何一个锁定的资源，就会造成死锁。所以不应该使用suspend()，而应在自己的Thread类中置入一个标志，指出线程应该活动还是挂起。若标志指出线程应该挂起，便用wait()命其进入等待状态。若标志指出线程应当恢复，则用一个notify()重新启动线程。
 *  
 * 61、sleep() 和 wait() 有什么区别? 
 *  答：sleep是线程类（Thread）的方法，导致此线程暂停执行指定时间，给执行机会给其他线程，但是监控状态依然保持，到时后会自动恢复。调用sleep不会释放对象锁。
 *   wait是Object类的方法，对此对象调用wait方法导致本线程放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象发出notify方法（或notifyAll）后本线程才进入对象锁定池准备获得对象锁进入运行状态。
 *  
 * 62、同步和异步有何异同，在什么情况下分别使用他们？举例说明。
 *   答：如果数据将在线程间共享。例如正在写的数据以后可能被另一个线程读到，或者正在读的数据可能已经被另一个线程写过了，那么这些数据就是共享数据，必须进行同步存取。
 *  当应用程序在对象上调用了一个需要花费很长时间来执行的方法，并且不希望让程序等待方法的返回时，就应该使用异步编程，在很多情况下采用异步途径往往更有效率。
 *  
 * 63、启动一个线程是用run()还是start()?
 *  答：启动一个线程是调用start()方法，使线程所代表的虚拟处理机处于可运行状态，这意味着它可以由JVM调度并执行。这并不意味着线程就会立即运行。run()方法可以产生必须退出的标志来停止一个线程。
 *   
 *  64、当一个线程进入一个对象的一个synchronized方法后，其它线程是否可进入此对象的其它方法?
 *   答：不能，一个对象的一个synchronized方法只能由一个线程访问。
 *   64网上的答案有点答非所问。
 *  
 * 65、请说出你所知道的线程同步的方法。
 *  答：wait():使一个线程处于等待状态，并且释放所持有的对象的lock。sleep():使一个正在运行的线程处于睡眠状态，是一个静态方法，调用此方法要捕捉InterruptedException异常。
 *  notify():唤醒一个处于等待状态的线程，注意的是在调用此方法的时候，并不能确切的唤醒某一个等待状态的线程，而是由JVM确定唤醒哪个线程，而且不是按优先级。
 *   notifyAll():唤醒所有处入等待状态的线程，注意并不是给所有唤醒线程一个对象的锁，而是让它们竞争。
 *  
 * 66、多线程有几种实现方法,都是什么?同步有几种实现方法,都是什么? 
 *  答：多线程有两种实现方法，分别是继承Thread类与实现Runnable接口 
 *  同步的实现方面有两种，分别是synchronized,wait与notify
 *  
 * 67、线程的基本概念、线程的基本状态以及状态之间的关系
 *  答：线程指在程序执行过程中，能够执行程序代码的一个执行单位，每个程序至少都有一个线程，也就是程序本身。Java中的线程有四种状态分别是：运行、就绪、挂起、结束
 *  
 * 68、简述synchronized和java.util.concurrent.locks.Lock的异同 ？
 *   答：主要相同点：Lock能完成synchronized所实现的所有功能
 * 主要不同点：Lock有比synchronized更精确的线程语义和更好的性能。synchronized会自动释放锁，而Lock一定要求程序员手工释放，并且必须在finally从句中释放。
 * 
 * </pre>
 * 
 * ClassName:JoinCountDownLatchTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 上午9:51:35 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class JoinCountDownLatchTest {

    public static void main(String[] args) {
        try {
            Thread parser1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("parser1 finish...");
                }
            });
            Thread parser2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("parser2 finish...");
                }
            });
            parser1.start();
            parser2.start();
            // join用于让当前执行线程等待join线程执行结束。其实现原理是不停检查join线程是否存活，如果join线程存活则让当前线程永远wait，代码片段如下，wait(0)表示永远等待下去。
            // 直到join线程中止后，线程的this.notifyAll会被调用，调用notifyAll是在JVM里实现的，所以JDK里看不到，有兴趣的同学可以看看JVM源码。JDK不推荐在线程实例上使用wait，notify和notifyAll方法。
            parser1.join();
            parser2.join();
            System.out.println("all  parser finish ...");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
