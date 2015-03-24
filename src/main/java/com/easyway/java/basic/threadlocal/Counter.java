/**
 * Project Name:java-basic
 * File Name:Counter.java
 * Package Name:com.easyway.java.basic.threadlocal
 * Date:2015-3-24上午11:03:32
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.threadlocal;
/**
 * ClassName:Counter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-24 上午11:03:32 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Counter {
    private static int count;
    private static ThreadLocal<Integer> serialCount=new ThreadLocal<Integer>(){
        protected synchronized Integer initialValue() {
            return new Integer(count++);
        }
    };
    
    public static int get(){
        return serialCount.get().intValue();
    }
    public static void set(int i ){
        serialCount.set(Integer.valueOf(i));
    }
    static  class LocalTester extends Thread{
        public void run(){
            for (int i = 0; i < 3; i++) {
                int c=Counter.get();
                System.out.println(this.getName()+" :" +c);
                Counter.set(c+2);
            }
        }
    }
    public static void main(String[] args) {
        Thread  t1=new LocalTester();
        Thread  t2=new LocalTester();
        t1.start();
        t2.start();
    }
}

