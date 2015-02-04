/**
 * Project Name:java-basic
 * File Name:BufferInterruptibly.java
 * Package Name:com.easyway.java.concurrent
 * Date:2015-2-4下午4:07:44
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.concurrent;

import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * <pre>
 * 
 * ReentrantLock与synchronized
 * 博客分类： 需学习
 * 多线程
 *  
 *  .
 * 
 * 
 *  
 * 关于互斥锁：
 *  
 * 所谓互斥锁, 指的是一次最多只能有一个线程持有的锁. 在jdk1.5之前, 我们通常使用synchronized机制控制多个线程对共享资源的访问. 而现在, Lock提供了比synchronized机制更广泛的锁定操作, Lock和synchronized机制的主要区别:
 *  
 * synchronized机制提供了对与每个对象相关的隐式监视器锁的访问, 并强制所有锁获取和释放均要出现在一个块结构中, 当获取了多个锁时, 它们必须以相反的顺序释放. synchronized机制对锁的释放是隐式的, 只要线程运行的代码超出了synchronized语句块范围, 锁就会被释放. 而Lock机制必须显式的调用Lock对象的unlock()方法才能释放锁, 这为获取锁和释放锁不出现在同一个块结构中, 以及以更自由的顺序释放锁提供了可能. 
 *  
 *  
 *  
 *  
 *  
 * 关于可重入： 
 *  
 * 一、2.4.1 内部锁
 *  
 * Java 提供了原子性的内置锁机制： sychronized 块。它包含两个部分：锁对象的引用和这个锁保护的代码块：
 *  
 * synchronized(lock) {
 *  
 * // 访问或修改被锁保护的共享状态
 *  
 * }
 *  
 * 内部锁扮演了互斥锁（ mutual exclusion lock, 也称作 mutex ）的角色，一个线程拥有锁的时候，别的线程阻塞等待。
 *  
 *  
 *  
 * 2.4.2 重进入（Reentrancy ）
 *  
 * 重入性：指的是同一个线程多次试图获取它所占有的锁，请求会成功。当释放锁的时候，直到重入次数清零，锁才释放完毕。
 *  
 * Public class Widget {
 *  
 *       Public synchronized void doSomething(){
 *  
 *            …
 *  
 *       }
 *  
 * }
 *  
 * Public class LoggingWidget extends Widget {
 *  
 *    Public synchronized void doSomething(){
 *  
 *       System.out.println(toString()+”:calling doSomething”);
 *  
 *       Super.doSomething();
 *  
 *    }
 *  
 * }
 *  
 *  
 *  
 * 二、一般来说，在多线程程序中，某个任务在持有某对象的锁后才能运行任务，其他任务只有在该任务释放同一对象锁后才能拥有对象锁，然后执行任务。于是，想到，同一个任务在持有同一个对象的锁后，在不释放锁的情况下，继续调用同一个对象的其他同步(synchronized)方法，该任务是否会再次持有该对象锁呢？ 
 *  
 *     答案是肯定的。同一个任务在调用同一个对象上的其他synchronized方法，可以再次获得该对象锁。 
 *  
 *  
 *  
 * 
 * 
 * Java代码  
 * 1.synchronized  m1(){  
 * 2.//加入此时对锁a的计数是N  
 * 3. m2();  //进入m2的方法体之后锁计数是N+1,离开m2后是N  
 * 4.}  
 * 5.synchronized m2(){}  
 *  
 *  同一任务和对象锁的问题：http://www.iteye.com/topic/728485
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 * 
 * 
 * Java代码  
 * 1.*public class ReentrantLock  
 * 2.extends Object implements Lock, Serializable 
 * 3.* 
 *  
 *   
 *  
 * 一个可重入的互斥锁 Lock，它具有与使用 synchronized 方法和语句所访问的隐式监视器锁相同的一些基本行为和语义，但功能更强大。
 *  
 *  
 *  
 * ReentrantLock 将由最近成功获得锁，并且还没有释放该锁的线程所拥有。当锁没有被另一个线程所拥有时，调用 lock 的线程将成功获取该锁并返回。如果当前线程已经拥有该锁，此方法将立即返回。可以使用 isHeldByCurrentThread() 和 getHoldCount() 方法来检查此情况是否发生。
 *  
 *  
 *  
 * 此类的构造方法接受一个可选的公平 参数。当设置为 true 时，在多个线程的争用下，这些锁倾向于将访问权授予等待时间最长的线程。否则此锁将无法保证任何特定访问顺序。与采用默认设置（使用不公平锁）相比，使用公平锁的程序在许多线程访问时表现为很低的总体吞吐量（即速度很慢，常常极其慢），但是在获得锁和保证锁分配的均衡性时差异较小。不过要注意的是，公平锁不能保证线程调度的公平性。因此，使用公平锁的众多线程中的一员可能获得多倍的成功机会，这种情况发生在其他活动线程没有被处理并且目前并未持有锁时。还要注意的是，未定时的 tryLock 方法并没有使用公平设置。因为即使其他线程正在等待，只要该锁是可用的，此方法就可以获得成功。
 *  
 * JDK：http://www.xasxt.com/java/api/java/util/concurrent/locks/ReentrantLock.html
 *  
 *  
 *  
 *  
 *  
 * 
 * 
 * Java代码  
 * 1.*构造方法摘要 
 * 2.ReentrantLock()  
 * 3.          创建一个 ReentrantLock 的实例。 
 * 4.ReentrantLock(boolean fair)  
 * 5.          创建一个具有给定公平策略的 ReentrantLock。 
 * 6.* 
 *  
 *   
 *  
 * 
 * 
 * Java代码  
 * 1.**public void lock() 
 * 2.获取锁。 
 * 3.如果该锁没有被另一个线程保持，则获取该锁并立即返回，将锁的保持计数设置为 1。 
 * 4.如果当前线程已经保持该锁，则将保持计数加 1，并且该方法立即返回。 
 * 5.如果该锁被另一个线程保持，则出于线程调度的目的，禁用当前线程，并且在获得锁之前，该线程将一直处于休眠状态，此时锁保持计数被设置为 1。 
 * 6.*
 *  
 *   
 *  
 * ReentrantLock 的lock机制有2种，忽略中断锁和响应中断锁，这给我们带来了很大的灵活性。比如：如果A、B 2个线程去竞争锁，A线程得到了锁，B线程等待，
 * 但是A线程这个时候实在有太多事情要处理，就是 一直不返回，B线程可能就会等不及了，想中断自己，不再等待这个锁了，转而处理其他事情。这个时候ReentrantLock就
 * 提供了2种机制，第一，B线程中断自己（或者别的线程中断它），但是ReentrantLock 不去响应，继续让B线程等待，你再怎么中断，我全当耳边风（synchronized
 * 原语就是如此）；第二，B线程中断自己（或者别的线程中断它），ReentrantLock 处理了这个中断，并且不再等待这个锁的到来，完全放弃。请看例子：
 *  
 * Example1:
 * 
 * 
 * </pre>
 * 
 * ClassName:BufferInterruptibly <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-2-4 下午4:07:44 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
interface IBuffer {
    public void write();


    public void read() throws InterruptedException;
}


class Buffer implements IBuffer {

    private Object lock;


    public Buffer() {
        lock = this;
    }


    public void write() {
        synchronized (lock) {
            long startTime = System.currentTimeMillis();
            System.out.println("开始往这个buff写入数据…");
            for (;;)// 模拟要处理很长时间
            {
                if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE)
                    break;
            }
            System.out.println("终于写完了");
        }
    }


    public void read() {
        synchronized (lock) {
            System.out.println("从这个buff读数据");
        }
    }

}


class BufferInterruptibly implements IBuffer {

    private ReentrantLock lock = new ReentrantLock();


    public void write() {
        lock.lock();
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始往这个buff写入数据…");
            for (;;)// 模拟要处理很长时间
            {
                if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE)
                    break;
            }
            System.out.println("终于写完了");
        }
        finally {
            lock.unlock();
        }
    }


    public void read() throws InterruptedException {
        lock.lockInterruptibly();// 注意这里，可以响应中断
        try {
            System.out.println("从这个buff读数据");
        }
        finally {
            lock.unlock();
        }
    }

}


public class BufferInterruptiblyTest {
    // 是用ReentrantLock，还是用synchronized
    public static boolean useSynchronized = false;


    public static void main(String[] args) {
        IBuffer buff = null;
        if (useSynchronized) {
            buff = new Buffer();
        }
        else {
            buff = new BufferInterruptibly();
        }
        final Writer writer = new Writer(buff);
        final Reader reader = new Reader(buff);
        writer.start();
        reader.start();
        new Thread(new Runnable() {
            public void run() {
                long start = System.currentTimeMillis();
                for (;;) {
                    // 等5秒钟去中断读
                    if (System.currentTimeMillis() - start > 5000) {
                        System.out.println("不等了，尝试中断");
                        reader.interrupt();
                        break;
                    }

                }

            }
        }).start();
    }
}


class Writer extends Thread {
    private IBuffer buff;


    public Writer(IBuffer buff) {
        this.buff = buff;
    }


    @Override
    public void run() {
        buff.write();
    }
}


class Reader extends Thread {
    private IBuffer buff;


    public Reader(IBuffer buff) {
        this.buff = buff;
    }


    @Override
    public void run() {
        try {
            buff.read();
        }
        catch (InterruptedException e) {
            System.out.println("我不读了");
        }
        System.out.println("读结束");
    }
}