/**
 * Project Name:java-basic
 * File Name:BeeperControl.java
 * Package Name:com.easyway.java.concurrent
 * Date:2015-3-6下午1:29:15
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.concurrent;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;


/**
 * ue: 任务队列
Concurrent Collection: 并发集合
atomic: 原子
lock: 锁
Timer: 计时器
Synchronizer: 同步

Executor: 执行对象
* Executor: 执行已提交的Runnable任务的对象
    * ExecutorService
        ThreadPoolExecutor  动态的线程池
        AbstractExecutorService
        * ScheduleExecutorService  任务调度
            ScheduledThreadPoolExecutor
    Executors  工厂方法，构建4种线程池
* CompletionService 生产新的异步任务与使用已完成任务的结果分离开来的服务
        ExecutorCompletionService
* Future 表示异步计算的结果
    * FutureTask
    * RunnableFuture
        RunnableScheduledFuture
* Callable 返回结果并可能抛出异常的任务
* RejectedExecutionHandler 无法由ThreadPoolExecutor执行的任务处理程序
Queue: 任务队列
ConcurrentLinkQueue  基于链接节点的无界线程安全队列
* BlockingQueue 阻塞队列
    ArrayBlockingQueue
    DelayQueue
    LinkedBlockingQueue
    PriorityBlocingQueue
    SynchronousQueue
    BlockingDeque  阻塞双端队列
        LinkedBlockingDeque
Concurrent Collection: 并发集合
* ConcurrentMap
    ConcurrentHashMap
    * ConcurrentNavigableMap
        ConcurrentSkipListMap
ConcurrentSkipListSet 基于ConcurrentSkipListMap的可缩放并发NavigableSet
CopyOnWriteArrayList ArrayList的一个线程安全的变体
CopyOnWriteArraySet
atomic: 原子
AtomicInteger
基本变量的原子操作
lock: 锁
* Lock
    ReentrantLock
* ReadWriteLock
    ReenTrantReadWriteLock
* Condition
Timer: 计时器
TimeUnit
Synchronizer: 同步
Semaphore 计数信号量
CountDownLatch 同步辅助类，计数器
CyclicBarrier 同步辅助类，使一组线程相互等待
Exchanger 可以再对中队元素进行配对和交换的线程的同步点
除了文章中有特别说明，均为IT宅原创文章，转载请以链接形式注明出处。
本文链接：http://www.itzhai.com/the-java-util-concurrent-the-overall-structure-of-fig.html
关键字: concurrent
arthinking指弹吉他 && 技术more
 * ClassName:BeeperControl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-6 下午1:29:15 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */

public class BeeperControl {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        BigInteger   bi;
        BeeperControl bc=new BeeperControl();
        bc.beepForAnHour();
    }

    public  void beepForAnHour() {
        final Runnable beeper = new Runnable() {
            public void run() {
                System.out.println("beep");
            }
        };
        final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
            }
        }, 60 * 60, SECONDS);
    }
}
