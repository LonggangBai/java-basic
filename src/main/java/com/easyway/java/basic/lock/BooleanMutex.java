/**
 * Project Name:java-basic
 * File Name:BooleanMutex.java
 * Package Name:com.easyway.java.basic.lock
 * Date:2015-4-1下午4:10:11
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * ClassName:BooleanMutex <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-4-1 下午4:10:11 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BooleanMutex {

    private Sync sync;

    public BooleanMutex() {
        sync = new Sync();
        set(false);
    }

    public BooleanMutex(Boolean mutex) {
        sync = new Sync();
        set(mutex);
    }

    /**
     * 阻塞等待Boolean为true
     * 
     * @throws InterruptedException
     */
    public void get() throws InterruptedException {
        sync.innerGet();
    }

    /**
     * 阻塞等待Boolean为true,允许设置超时时间
     * 
     * @param timeout
     * @param unit
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public void get(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
        sync.innerGet(unit.toNanos(timeout));
    }

    /**
     * 重新设置对应的Boolean mutex
     * 
     * @param mutex
     */
    public void set(Boolean mutex) {
        if (mutex) {
            sync.innerSetTrue();
        } else {
            sync.innerSetFalse();
        }
    }

    public boolean state() {
        return sync.innerState();
    }

    /**
     * Synchronization control for BooleanMutex. Uses AQS sync state to
     * represent run status
     */
    private final class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -7828117401763700385L;

        /** State value representing that TRUE */
        private static final int  TRUE             = 1;
        /** State value representing that FALSE */
        private static final int  FALSE            = 2;

        private boolean isTrue(int state) {
            return (state & TRUE) != 0;
        }

        /**
         * 实现AQS的接口，获取共享锁的判断
         */
        protected int tryAcquireShared(int state) {
            // 如果为true，直接允许获取锁对象
            // 如果为false，进入阻塞队列，等待被唤醒
            return isTrue(getState()) ? 1 : -1;
        }

        /**
         * 实现AQS的接口，释放共享锁的判断
         */
        protected boolean tryReleaseShared(int ignore) {
            //始终返回true，代表可以release
            return true;
        }

        boolean innerState() {
            return isTrue(getState());
        }

        void innerGet() throws InterruptedException {
            acquireSharedInterruptibly(0);
        }

        void innerGet(long nanosTimeout) throws InterruptedException, TimeoutException {
            if (!tryAcquireSharedNanos(0, nanosTimeout))
                throw new TimeoutException();
        }

        void innerSetTrue() {
            for (;;) {
                int s = getState();
                if (s == TRUE) {
                    return; //直接退出
                }
                if (compareAndSetState(s, TRUE)) {// cas更新状态，避免并发更新true操作
                    releaseShared(0);//释放一下锁对象，唤醒一下阻塞的Thread
                }
            }
        }

        void innerSetFalse() {
            for (;;) {
                int s = getState();
                if (s == FALSE) {
                    return; //直接退出
                }
                if (compareAndSetState(s, FALSE)) {//cas更新状态，避免并发更新false操作
                    setState(FALSE);
                }
            }
        }

    }
    public  static  void test_init_true() {
        BooleanMutex mutex = new BooleanMutex(true);
        try {
            mutex.get(); //不会被阻塞
        } catch (InterruptedException e) {
        }
    }
    
    public static  void test_init_false() {
        final BooleanMutex mutex = new BooleanMutex(false);
        try {
            final CountDownLatch count = new CountDownLatch(1);
            ExecutorService executor = Executors.newCachedThreadPool();

            executor.submit(new Callable() {

                public Object call() throws Exception {
                    Thread.sleep(1000);
                    mutex.set(true);
                    count.countDown();
                    return null;
                }
            });

            mutex.get(); //会被阻塞，等异步线程释放锁对象
            count.await();
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public  static void test_concurrent_true() {
        try {
            final BooleanMutex mutex = new BooleanMutex(true);
            final CountDownLatch count = new CountDownLatch(10);
            ExecutorService executor = Executors.newCachedThreadPool();

            for (int i = 0; i < 10; i++) {
                executor.submit(new Callable() {

                    public Object call() throws Exception {
                        mutex.get();
                        count.countDown();
                        return null;
                    }
                });
            }
            count.await();
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void test_concurrent_false() {
        try {
            final BooleanMutex mutex = new BooleanMutex(false);//初始为false
            final CountDownLatch count = new CountDownLatch(10);
            ExecutorService executor = Executors.newCachedThreadPool();

            for (int i = 0; i < 10; i++) {
                executor.submit(new Callable() {

                    public Object call() throws Exception {
                        mutex.get();//被阻塞
                        count.countDown();
                        return null;
                    }
                });
            }
            Thread.sleep(1000);
            mutex.set(true);
            count.await();
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        BooleanMutex.test_concurrent_true();
    }
}

