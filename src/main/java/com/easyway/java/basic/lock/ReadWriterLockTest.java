package com.easyway.java.basic.lock;

import java.util.Random;

/**
 * <pre>
 * Thread中读写锁
读写锁，分为读锁和写锁，多个读锁不互斥，读锁和写锁互斥，写锁和写锁互斥，其实很好理解，就是读的时候不能多线程去写，读的时候可以多线程去读，写的时候不能多线程去写。这是由jvm自己控制的，你只要上相应的锁就行了，如果你的代码是只读数据，可以很多人去读，但不能同时写，那就上读锁，如果你的代码修改数据，那就上写锁，只能有一个人在写，不能有人在写得时候读数据。
 * </pre>
 * @author longgangbai
 *
 */
public class ReadWriterLockTest {  
    
    public static void main(String[] args) {  
        final ReadWriteMethod rwm = new ReadWriteMethod();  
        for(int i=1;i<=3;i++){  
            new Thread(new Runnable() {//写线程  
                @Override  
                public void run() {  
                    rwm.setData(new Random().nextInt(10000));  
                }  
            },"t2").start();  
              
            new Thread(new Runnable() { //读线程  
                @Override  
                public void run() {  
                    rwm.getData();  
                }  
            },"t1").start();  
              
        }  
    }  
      
    static class ReadWriteMethod{  
        private Object data = null;  
        public void getData(){  
            try {     
                System.out.println("准备读取数据");  
                Thread.sleep(1000);  
                System.out.println("读取到数据为---------"+data);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }finally {  
            }  
              
        }  
          
        public void setData(Object data){  
            try {  
                System.out.println("准备写入数据");  
                Thread.sleep(500);  
                this.data = data;  
                System.out.println("写入的数据为---------"+data);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }finally {  
            }  
              
        }  
    }  
}  