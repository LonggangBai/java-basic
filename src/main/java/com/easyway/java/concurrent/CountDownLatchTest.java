/**
 * Project Name:java-basic
 * File Name:CountDownLatchTest.java
 * Package Name:com.easyway.java.basic.concurrent
 * Date:2015-1-21上午10:00:52
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.concurrent;

import java.util.concurrent.CountDownLatch;


/**
 * 并发工具类（一）等待多线程完成的CountDownLatch 简介
 * 
 * CountDownLatch 允许一个或多个线程等待其他线程完成操作。
 * 
 * 而在JDK1.5之后的并发包中提供的CountDownLatch也可以实现join的这个功能，并且比join的功能更多。
 * 
 * CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完成，这里就传入N。
 * 
 * 当我们调用一次CountDownLatch的countDown方法时，N就会减1，CountDownLatch的await会阻塞当前线程，直到N变成零。
 * 由于countDown方法可以用在任何地方
 * ，所以这里说的N个点，可以是N个线程，也可以是1个线程里的N个执行步骤。用在多个线程时，你只需要把这个CountDownLatch的引用传递到线程里。
 * 
 * 其他方法
 * 
 * 如果有某个解析sheet的线程处理的比较慢，我们不可能让主线程一直等待，所以我们可以使用另外一个带指定时间的await方法，await(long
 * time, TimeUnit unit): 这个方法等待特定时间后，就会不再阻塞当前线程。join也有类似的方法。
 * 
 * 注意：计数器必须大于等于0，只是等于0时候，计数器就是零，调用await方法时不会阻塞当前线程。
 * CountDownLatch不可能重新初始化或者修改CountDownLatch对象的内部计数器的值。一个线程调用countDown方法
 * happen-before 另外一个线程调用await方法。
 * 
 * ClassName:CountDownLatchTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 上午10:00:52 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class CountDownLatchTest {

    static CountDownLatch c = new CountDownLatch(2);


    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
                c.countDown();
            }
        }).start();
        c.await();
        System.out.println("3");
    }

}
