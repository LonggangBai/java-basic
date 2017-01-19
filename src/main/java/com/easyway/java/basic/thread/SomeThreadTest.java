package com.easyway.java.basic.thread;
/**
 * <pre>
 * 2）讲这些个Runable对象作为某一个类中的内部类，共享数据作为这个外部类中的成员变量，每个线程对共享数据的操作也作为这个外部类的方法，这些歌Runable对象分别取调用这个外部类的不同操作共享数据的方法，样就实现了这个共享数据在多个线程中互斥和通信！！

总之要同步几段互斥的代码最好是放在不同的方法中，这些方法再放在同一个类中，这样比较容易实现他们之间的互斥和通信
 * </pre>
 * @author longgangbai
 *
 */
public class SomeThreadTest {  
    
    public static void main(String[] args) {  
    	final AS count = new AS();  
        /** 
         * 创建两个线程内部类，并且两个Runable对共享数据所做的操作不同 
         */  
        new Thread(new Runnable() {//创建一个线程内部类  
            @Override  
            public void run() {  
                System.out.println(count.Reduce());  
            }  
        }).start();  
        new Thread(new Runnable() {//创建第二个线程内部类  
            @Override  
            public void run() {  
                System.out.println(count.Add());  
            }  
        }).start();  
    }  
}  
  
class AS {  
    private Integer integer = 50;//共享数据  
    public synchronized Integer Reduce() {//对共享数据的操作方法（此处的synchronize是为了保证线程安全）  
        return --integer;  
    }  
  
    public synchronized Integer Add() {//对共享数据的操作方法  
        return ++integer;  
    }  
}  


