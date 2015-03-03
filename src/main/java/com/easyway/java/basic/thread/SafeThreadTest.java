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
class SafeCount {
    /**
     * 只有Thread-0线程输出的结果是我们期望的，而输出的是每次都累加的，这里累加的原因以后的博文会说明，那么要想得到我们期望的结果，有几种解决方案： 

        1. 将Count中num变成count方法的局部变量；
     *
     * @author Administrator
     * @since JDK 1.6
     */
    public void count() {
        int num = 0;
        for(int i = 1; i <= 10; i++) {
            num += i;
        }
        System.out.println(Thread.currentThread().getName() + "-" + num);
    }
}
/**
 * 3. 每次启动一个线程使用不同的线程类，不推荐。
         上述测试，我们发现，存在成员变量的类用于多线程时是不安全的，不安全体现在这个成员变量可能发生非原子性的操作，
         而变量定义在方法内也就是局部变量是线程安全的。想想在使用struts1时，不推荐创建成员变量，因为action是单例的，
         如果创建了成员变量，就会存在线程不安全的隐患，而struts2是每一次请求都会创建一个action，就不用考虑线程安全的问题。
         所以，日常开发中，通常需要考虑成员变量或者说全局变量在多线程环境下，是否会引发一些问题。
 * ClassName: SafeThreadTest <br/>
 *
 * @author Administrator
 * @version 
 * @since JDK 1.6
 */
public class SafeThreadTest {

    /**
     * 2. 将线程类成员变量拿到run方法中，这时count引用是线程内的局部变量；
     *
     * @author Administrator
     * @param args
     * @since JDK 1.6
     */
        public static void main(String[] args) {
            Runnable runnable = new Runnable() {
                public void run() {
                    SafeCount count = new SafeCount();
                    count.count();
                }
            };
            for(int i = 0; i < 10; i++) {
                new Thread(runnable).start();
            }
        }
    }