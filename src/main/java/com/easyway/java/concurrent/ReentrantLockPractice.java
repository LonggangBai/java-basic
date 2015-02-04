/**
 * Project Name:java-basic
 * File Name:ReentrantLockPractice.java
 * Package Name:com.easyway.java.concurrent
 * Date:2015-2-4下午4:13:04
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.concurrent;

import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * <pre>
 * Example4：
 * 三个线程，线程名分别为A、B、C，设计程序使得三个线程循环打印“ABC”10次后终止。如：ABCABCABCABCABCABCABCABCABCABC
 *  
 *  注意与Example2的区别，一个线材类定义在内部，一个在外部，注意区别。
 *  
 *  其他方法：
 *  
 * http://hxraid.iteye.com/blog/607228
 *  
 * 相同：ReentrantLock提供了synchronized类似的功能和内存语义。
 *  
 *  
 *  
 * 不同：
 *  
 * 1.ReentrantLock功能性方面更全面，比如时间锁等候，可中断锁等候，锁投票等，因此更有扩展性。在多个条件变量和高度竞争锁的地方，用ReentrantLock更合适，ReentrantLock还提供了Condition，对线程的等待和唤醒等操作更加灵活，一个ReentrantLock可以有多个Condition实例，所以更有扩展性。
 *  
 * 2.ReentrantLock必须在finally中释放锁，否则后果很严重，编码角度来说使用synchronized更加简单，不容易遗漏或者出错。
 *  
 * 3.ReentrantLock 的性能比synchronized会好点。
 *  
 * 4.ReentrantLock提供了可轮询的锁请求，他可以尝试的去取得锁，如果取得成功则继续处理，取得不成功，可以等下次运行的时候处理，所以不容易产生死锁，而synchronized则一旦进入锁请求要么成功，要么一直阻塞，所以更容易产生死锁。
 *  
 *  
 *  
 * 1、Lock的某些方法可以决定多长时间内尝试获取锁，如果获取不到就抛异常，这样就可以一定程度上减轻死锁的可能性。
 *  
 * 如果锁被另一个线程占据了，synchronized只会一直等待，很容易错序死锁 
 *  
 * 2、synchronized的话，锁的范围是整个方法或synchronized块部分；而Lock因为是方法调用，可以跨方法，灵活性更大 
 *  
 * 3、便于测试，单元测试时，可以模拟Lock，确定是否获得了锁，而synchronized就没办法了
 *  
 *  
 *  
 *  
 *  
 * ReentrantLock比synchronized 强大在哪儿？
 *  
 * 简单说： 
 *  
 * 1、ReentrantLock可以实现fair lock 
 *  
 *  
 *  
 * public ReentrantLock(boolean fair) {   
 *  
 *     sync = (fair)? new FairSync() : new NonfairSync();  
 *  
 * }  
 *  
 * 所谓fair lock就是看获得锁的顺序是不是和申请锁的时间的顺序是一致的 
 *  
 *  
 *  
 * 2、ReentrantLock支持中断处理 
 *  
 *  
 *  
 * public final void acquireInterruptibly(int arg) throws InterruptedException {  
 *  
 *     if (Thread.interrupted())  
 *  
 *         throw new InterruptedException();  
 *  
 *     if (!tryAcquire(arg))  
 *  
 *         doAcquireInterruptibly(arg);  
 *  
 * }  
 *  
 * 就是说那些持有锁的线程一直不释放，正在等待的线程可以放弃等待。 
 *  
 *  
 *  
 * 3、ReentrantLock可以和condition结合使用 
 *  
 *  
 *  
 * public boolean hasWaiters(Condition condition) {  
 *  
 *     if (condition == null)  
 *  
 *         throw new NullPointerException();  
 *  
 *     if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))  
 *  
 *         throw new IllegalArgumentException("not owner");  
 *  
 *     return sync.hasWaiters((AbstractQueuedSynchronizer.ConditionObject)condition);  
 *  
 * }  
 *  
 *  
 *  
 * public int getWaitQueueLength(Condition condition) {  
 *  
 *     if (condition == null)  
 *  
 *         throw new NullPointerException();  
 *  
 *     if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))  
 *  
 *         throw new IllegalArgumentException("not owner");  
 *  
 *     return sync.getWaitQueueLength((AbstractQueuedSynchronizer.ConditionObject)condition);  
 *  
 * }  
 *  
 *  
 *  
 * 内置锁synchronized
 *  
 * 显式锁Lock
 *  
 * ReentrantLock代码剖析之ReentrantLock.lock
 *  
 * ReentrantLock中tryLock的使用问题（注意循环）
 * 
 * </pre>
 * 
 * ClassName:ReentrantLockPractice <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-2-4 下午4:13:04 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ReentrantLockPractice {

    static ReentrantLock lock = new ReentrantLock();
    private static String[] threadArr = { "A", "B", "C" };


    public static void main(String[] args) {
        ReentrantLockPractice pc = new ReentrantLockPractice();
        pc.startDemo();
    }


    void startDemo() {
        for (int i = 0; i < 10; i++) {
            for (String name : threadArr) {
                TestThread t = new TestThread(name);
                t.start();
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class TestThread extends Thread {

        // 自定义线程名字
        TestThread(String str) {
            super(str);
        }


        public void run() {
            try {
                lock.lockInterruptibly();
                System.out.print(Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
    }

}
