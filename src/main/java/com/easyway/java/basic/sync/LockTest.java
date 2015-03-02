/**
 * Project Name:java-basic
 * File Name:LockTest.java
 * Package Name:com.easyway.java.basic.sync
 * Date:2015-2-12上午10:19:11
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.easyway.java.basic.utils.DateUtils;


/**
 * ClassName:LockTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-2-12 上午10:19:11 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class LockTest {
    public void testLock() {
        final ReentrantLock lock = new ReentrantLock();
        System.out.println(Thread.currentThread().getName()+"====before=" +DateUtils.getCurrentFormatDate());
        lock.lock();
        try {
            Thread.sleep(10000L);
            System.out.println(Thread.currentThread().getName()+"============="+Thread.activeCount());
        }
        catch (InterruptedException e) {
            e.printStackTrace();

        }
        finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName()+"====after=" +DateUtils.getCurrentFormatDate());
    }


    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    LockTest lock = new LockTest();

                    lock.testLock();

                }

            });
            thread.setName("Thread" + i);
            threadList.add(thread);
        }
        for (Thread thread : threadList) {
            thread.start();
        }
    }
}
