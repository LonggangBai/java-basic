/**
 * Project Name:java-basic
 * File Name:CyclicBarrierTest3.java
 * Package Name:com.easyway.java.basic.concurrent
 * Date:2015-1-21上午10:20:33
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.concurrent;

/**
 * CyclicBarrier的应用场景

 * CyclicBarrier可以用于多线程计算数据，最后合并计算结果的应用场景。比如我们用一个Excel保存了用户所有银行流水，每个Sheet保存一个
 * 帐户近一年的每笔银行流水，现在需要统计用户的日均银行流水，先用多线程处理每个sheet里的银行流水，都执行完之后，得到每个sheet的日均
 * 银行流水，最后，再用barrierAction用这些线程的计算结果，计算出整个Excel的日均银行流水。
 * CyclicBarrier和CountDownLatch的区别
 * CountDownLatch的计数器只能使用一次。而CyclicBarrier的计数器可以使用reset() 方法重置。所以CyclicBarrier能处理更为复杂
 * 的业务场景，比如如果计算发生错误，可以重置计数器，并让线程们重新执行一次。
 * CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得CyclicBarrier阻塞的线程数量。isBroken方法用来知道
 * 阻塞的线程是否被中断。比如以下代码执行完之后会返回true。
 * 
 * 
 * CyclicBarrier和CountDownLatch的区别

 * CountDownLatch的计数器只能使用一次。而CyclicBarrier的计数器可以使用reset() 方法重置。所以CyclicBarrier能处理更为复杂
 * 的业务场景，比如如果计算发生错误，可以重置计数器，并让线程们重新执行一次。
 * CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得CyclicBarrier阻塞的线程数量。isBroken方法用来知道
 * 阻塞的线程是否被中断。比如以下代码执行完之后会返回true。
 * isBroken的使用代码如下：
 * 
 * ClassName:CyclicBarrierTest3 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-1-21 上午10:20:33 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * 
 */
public class CyclicBarrierTest3 {

    static CyclicBarrier c = new CyclicBarrier(2);


    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    c.await();
                }
                catch (Exception e) {
                }
            }
        });
        thread.start();
        thread.interrupt();
        try {
            c.await();
        }
        catch (Exception e) {
            System.out.println(c.isBroken());
        }
    }
}