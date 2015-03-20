/**
 * Project Name:java-basic
 * File Name:Printer.java
 * Package Name:com.easyway.java.basic.lock
 * Date:2015-3-20下午2:37:04
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.lock;
/**
 * ClassName:Printer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-20 下午2:37:04 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
public class Printer  { 
    private Lock lock = new ReentrantLock();// 锁对象   
    public void printLetters(char c) {
         lock.lock();// 得到锁  
         try {
             for(int i = 0; i<5; i++) {  
                 System.out.print(c);  
             }  
             System.out.println();
        }finally {  
             lock.unlock();// 释放锁   
        } 
    }
}

