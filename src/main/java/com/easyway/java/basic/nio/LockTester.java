/**
 * Project Name:java-basic
 * File Name:LockTester.java
 * Package Name:com.easyway.java.basic.nio
 * Date:2015-3-25上午10:49:44
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;

/**
 * ClassName:LockTester <br/>
 * Function: FileLock <br/>
 * Reason: FileLock的tryLock或者lock方法用于锁定文件，如果操作成功，会返回一个FileLock对象。
 * tryLock方法是非阻塞式的它设法获取的锁，如果不能获得锁，就立即返回null。lock方法是阻塞式的它设法获取锁，如果不能获得锁，那么会
 * 使执行该lock方法的线程进入阻塞状态，只有等到获得了锁，线程才能恢复运行。
 * 文件锁可以分为以下两种类型：
 * 1.共享锁：
 *   当一个线程已经获得了文件的共享锁，其他线程还可以获得该文件的共享锁，
 *   但是不能获得该文件的排它锁。
 * 2.排它锁：
 *    当一个线程已经获得了文件的排它锁时候，其他线程不允许获得该文件的共享锁或者排它锁。
 *    
 *  <br/>
 * Date: 2015-3-25 上午10:49:44 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class LockTester {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileOutputStream fos=new FileOutputStream("D:\\test.txt");
        FileLock fl=fos.getChannel().tryLock();
        if(fl!=null){
            System.out.println("Locked File");
            System.out.println(" "+fl.isShared());//是否是共享锁
            Thread.sleep(600000);
            fl.release();//用于释放文件锁。
            System.out.println("Released Lock");
        }
    }
}
