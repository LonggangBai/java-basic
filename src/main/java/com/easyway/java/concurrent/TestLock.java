/**
 * Project Name:java-basic
 * File Name:TestLock.java
 * Package Name:com.easyway.java.concurrent
 * Date:2015-2-4下午4:11:38
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.concurrent;
/**
 * ClassName:TestLock <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-2-4 下午4:11:38 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.Calendar;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    private ReentrantLock lock = null;
    
    public int data = 100;     // 用于线程同步访问的共享数据

    public TestLock() {
        lock = new ReentrantLock(); // 创建一个自由竞争的可重入锁
    }
    public ReentrantLock getLock() {
        return lock;
    }
    
    public void testReentry() {
        lock.lock();
        Calendar now = Calendar.getInstance();
        System.out.println(now.getTime() + " " + Thread.currentThread() + " get lock.");
    }

    public static void main(String[] args) {
        TestLock tester = new TestLock();

        //1、测试可重入
        tester.testReentry();
        tester.testReentry(); // 能执行到这里而不阻塞，表示锁可重入
        tester.testReentry(); // 再次重入

        // 释放重入测试的锁，要按重入的数量解锁，否则其他线程无法获取该锁。
        tester.getLock().unlock();
        tester.getLock().unlock();
        tester.getLock().unlock();

        //2、测试互斥
        // 启动3个线程测试在锁保护下的共享数据data的访问
        new Thread(new workerThread(tester)).start();
        new Thread(new workerThread(tester)).start();
        new Thread(new workerThread(tester)).start();
    }


    // 线程调用的方法
    public void testRun() throws Exception {
        lock.lock();

        Calendar now = Calendar.getInstance();
        try {
            // 获取锁后显示 当前时间 当前调用线程 共享数据的值（并使共享数据 + 1）
            System.out.println(now.getTime() + " " + Thread.currentThread()+ " accesses the data " + data++);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

// 工作线程，调用TestServer.testRun
class workerThread implements Runnable {

    private TestLock tester = null;

    public workerThread(TestLock testLock) {
        this.tester = testLock;
    }

    public void run() {
        try {
            tester.testRun();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
