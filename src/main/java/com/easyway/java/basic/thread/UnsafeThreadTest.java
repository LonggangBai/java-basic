/**
 * Project Name:java-basic
 * File Name:UnsafeThreadTest.java
 * Package Name:com.easyway.java.basic.thread
 * Date:2015-3-3上午10:08:33
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.thread;
/**
 * 
 * 回归正题，当我们查看JDK API的时候，总会发现一些类说明写着，线程安全或者线程不安全，比如说StringBuilder中，有这么一句，“将StringBuilder
 *  的实例用于多个线程是不安全的。如果需要这样的同步，则建议使用StringBuffer。 ”，那么下面手动创建一个线程不安全的类，然后在多线程中使用这个类，看看有什么效果。
 * 
 * 
 *     在这个类中的count方法是计算1一直加到10的和，并输出当前线程名和总和，我们期望的是每个线程都会输出55。 
 *     
 *     
 * ClassName:UnsafeThreadTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-3 上午10:08:33 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
class UnsafeCount {
        private int num;
        public void count() {
            for(int i = 1; i <= 10; i++) {
                num += i;
            }
            System.out.println(Thread.currentThread().getName() + "-" + num);
        }
    }
public class UnsafeThreadTest {

        public static void main(String[] args) {
            Runnable runnable = new Runnable() {
                public void run() {
                    UnsafeCount count = new UnsafeCount();
                    count.count();
                }
            };
            for(int i = 0; i < 10; i++) {
                new Thread(runnable).start();
            }
        }
    }