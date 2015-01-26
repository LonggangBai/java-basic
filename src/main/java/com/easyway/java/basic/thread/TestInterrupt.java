package com.easyway.java.basic.thread;

import java.util.*;  
/**
 * 一、interrupt方法一种让线程退出的方式。
 * @author Administrator
 *
 */
public class TestInterrupt{  
    public static void main(String[] args){  
        MyThread t = new MyThread();  
        t.start();  
        try{Thread.sleep(10000);}  
        catch(InterruptedException i){}  
        t.interrupt();  
    }  
}  
 
class MyThread extends Thread{  
    public void run(){  
        while(true){  
            try{  
                System.out.println("------"+new Date()+"-----");  
                Thread.sleep(1000);  
            }catch(InterruptedException i){  
                return;  
            }  
        }  
    }  
}  
 