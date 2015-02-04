/**
 * Project Name:java-basic
 * File Name:PausableThreadPoolExecutor.java
 * Package Name:com.easyway.java.concurrent
 * Date:2015-2-4下午3:22:29
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * ClassName:PausableThreadPoolExecutor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-2-4 下午3:22:29 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class PausableThreadPoolExecutor extends ThreadPoolExecutor {
    private boolean isPaused;
    private ReentrantLock pauseLock = new ReentrantLock();
    private Condition unpaused = pauseLock.newCondition();


    public PausableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory());
    }


    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        pauseLock.lock();
        try {
            while (isPaused)
                unpaused.await();
        }
        catch (InterruptedException ie) {
            t.interrupt();
        }
        finally {
            pauseLock.unlock();
        }
    }


    public void pause() {
        pauseLock.lock();
        try {
            isPaused = true;
        }
        finally {
            pauseLock.unlock();
        }
    }


    public void resume() {
        pauseLock.lock();
        try {
            isPaused = false;
            unpaused.signalAll();
        }
        finally {
            pauseLock.unlock();
        }
    }
}