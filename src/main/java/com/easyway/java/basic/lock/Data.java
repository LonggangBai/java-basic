/**
 * Project Name:java-basic
 * File Name:Data.java
 * Package Name:com.easyway.java.basic.lock
 * Date:2015-3-20下午2:35:52
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName:Data <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 我们要实现写入和写入互斥，读取和写入互斥，读取和读取互斥，在set和get方法加入sychronized修饰符：
Java代码  收藏代码
public synchronized void set(int data) {...}        
public synchronized void get() {...}    
 部分输出结果：
Text代码  收藏代码
Thread-0准备写入数据  
Thread-0写入9  
Thread-5准备读取数据  
Thread-5读取9  
Thread-5准备读取数据  
Thread-5读取9  
Thread-5准备读取数据  
Thread-5读取9  
Thread-5准备读取数据  
Thread-5读取9  
 我们发现，虽然写入和写入互斥了，读取和写入也互斥了，但是读取和读取之间也互斥了，不能并发执行，效率较低，用读写锁实现代码如下： <br/>
 * Date:     2015-3-20 下午2:35:52 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Data {
    private int data;// 共享数据   
    private ReadWriteLock rwl = new ReentrantReadWriteLock();     
    public void set(int data) {  
        rwl.writeLock().lock();// 取到写锁   
        try {  
            System.out.println(Thread.currentThread().getName() + "准备写入数据");  
            try {  
                Thread.sleep(20);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            this.data = data;  
            System.out.println(Thread.currentThread().getName() + "写入" + this.data);  
        } finally {  
            rwl.writeLock().unlock();// 释放写锁   
        }  
    }     
    public void get() {  
        rwl.readLock().lock();// 取到读锁   
        try {  
            System.out.println(Thread.currentThread().getName() + "准备读取数据");  
            try {  
                Thread.sleep(20);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println(Thread.currentThread().getName() + "读取" + this.data);  
        } finally {  
            rwl.readLock().unlock();// 释放读锁   
        }  
    }  
}  
