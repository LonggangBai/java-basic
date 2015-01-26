/**
 * Project Name:java-basic
 * File Name:CyclicBarrierTest2.java
 * Package Name:com.easyway.java.basic.concurrent
 * Date:2015-1-21上午10:10:53
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.concurrent;

import java.util.concurrent.CyclicBarrier;


/**
 * CyclicBarrier还提供一个更高级的构造函数CyclicBarrier(int parties, Runnable
 * barrierAction)，用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景。代码如下：
 * 
 * 
 * ClassName:CyclicBarrierTest2 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 上午10:10:53 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class CyclicBarrierTest2 {

    static CyclicBarrier c = new CyclicBarrier(2, new A());


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

    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println(3);
        }

    }

}
