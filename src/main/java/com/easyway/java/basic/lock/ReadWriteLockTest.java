/**
 * Project Name:java-basic
 * File Name:ReadWriteLockTest.java
 * Package Name:com.easyway.java.basic.lock
 * Date:2015-3-20下午2:37:40
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.lock;

import java.util.Random;

/**
 * ClassName:ReadWriteLockTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 紧接着Callable和线程池，再次接触java.util.concurrent并发包下的东西。Lock提供比synchronized更灵活的并发控制。Lock是java.util.concurrent.locks包下的接口，Lock 实现提供了比使用synchronized 方法和语句可获得的更广泛的锁定操作，它能以更优雅的方式处理线程同步问题。使用最多的Lock类是ReentrantLock。下面用它来实现上一篇日志中的打印机的例子：
 * 这样就实现了和sychronized一样的同步效果，值得一提的是，用sychronized修饰的方法或者语句块在代码执行完之后锁自动释放，而是用Lock需要我们手动释放锁，所以为了保证锁最终被释放(发生异常情况)，要把互斥区放在try内，释放锁放在finally内。
读锁和写锁的使用：
以下内容转载来至：http://blog.csdn.net/ghsau/article/details/7461369
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
如果说这就是Lock，那么它不能成为同步问题更完美的处理方式，下面要介绍的是读写锁(ReadWriteLock)，我们会有一种需求，在对数据进行读写的时候，为了保证数据的一致性和完整性，需要读和写是互斥的，写和写是互斥的，但是读和读是不需要互斥的，这样读和读不互斥性能更高些，来看一下不考虑互斥情况的代码原型：
 *  <br/>
 * Date:     2015-3-20 下午2:37:40 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ReadWriteLockTest {  
   static  class Data {      
        private int data;// 共享数据       
        public void set(int data) {  
            System.out.println(Thread.currentThread().getName() + "准备写入数据");  
            try {  
                Thread.sleep(20);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            this.data = data;  
            System.out.println(Thread.currentThread().getName() + "写入" + this.data);  
        }     
        public void get() {  
            System.out.println(Thread.currentThread().getName() + "准备读取数据");  
            try {  
                Thread.sleep(20);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println(Thread.currentThread().getName() + "读取" + this.data);  
        }  
    } 
    public static void main(String[] args) {  
        final Data data = new Data();  
        for (int i = 0; i < 3; i++) {  
            new Thread(new Runnable() {  
                public void run() {  
                    for (int j = 0; j < 5; j++) {  
                        data.set(new Random().nextInt(30));  
                    }  
                }  
            }).start();  
        }         
        for (int i = 0; i < 3; i++) {  
            new Thread(new Runnable() {  
                public void run() {  
                    for (int j = 0; j < 5; j++) {  
                        data.get();  
                    }  
                }  
            }).start();  
        }  
    }  
}  
