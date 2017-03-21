package com.easyway.java.basic.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriterLockTest1 {  
    
    public static void main(String[] args) {  
    	final ReadWriteMethod1 rwm = new ReadWriteMethod1();  
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
      
    static class ReadWriteMethod1{  
        private Object data = null;  
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();  
        public void getData(){  
            readWriteLock.readLock().lock();  
            try {     
                System.out.println("准备读取数据");  
                Thread.sleep(1000);  
                System.out.println("读取到数据为---------"+data);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }finally {  
                readWriteLock.readLock().unlock();  
            }  
              
        }  
          
        public void setData(Object data){  
            readWriteLock.writeLock().lock();  
            try {  
                System.out.println("准备写入数据");  
                Thread.sleep(500);  
                this.data = data;  
                System.out.println("写入的数据为---------"+data);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }finally {  
                readWriteLock.writeLock().unlock();  
            }  
              
        }  
    }  
}  