/**
 * Project Name:java-basic
 * File Name:CyclicBarrierTest.java
 * Package Name:com.easyway.java.basic.concurrent
 * Date:2015-1-21上午10:06:21
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.concurrent;

import java.util.concurrent.CyclicBarrier;


/**
 * 
 * 并发工具类（二）同步屏障CyclicBarrier 简介
 * 
 * CyclicBarrier
 * 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞
 * ，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。CyclicBarrier默认的构造方法是CyclicBarrier(int
 * parties)，其参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。
 * 
 * 
 * 输出 2，1 或者输出 1 ，2
 * 
 * 
 * 如果把new CyclicBarrier(2)修改成new
 * CyclicBarrier(3)则主线程和子线程会永远等待，因为没有第三个线程执行await方法
 * ，即没有第三个线程到达屏障，所以之前到达屏障的两个线程都不会继续执行。
 * 
 * 
 * ClassName:CyclicBarrierTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 上午10:06:21 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class CyclicBarrierTest {

    static CyclicBarrier c = new CyclicBarrier(2);


    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    c.await();
                }
                catch (Exception e) {

                }
                System.out.println(1);
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    c.await();
                }
                catch (Exception e) {

                }
                System.out.println(2);
            }
        }).start();

    }
}
