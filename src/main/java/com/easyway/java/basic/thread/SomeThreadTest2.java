package com.easyway.java.basic.thread;
/**
 * <pre>
 * 1.如果每个线程执行的代码相同，可以使用同一个Runable对象，这个Runable对象中有那个共享数据，例如卖票系统就可以这样做。

2.如果每个线程执行的代码不相同，这个时候需要用到不同的Runable对象，有如下两种方式实现多个Runable对象中的数据共享。

（1）将共享数据封装在另一个对象中，然后将这对象逐一传递给各个Runable对象，每个线程对共享数据的操作方法也交给那个对象去进行，这样就能实现多个线程之间的互斥和通信。
 * 上面这个例子中A类中的integer是两个线程想要操作的共享数据，而两个线程要对共享数据执行的操作方法还是不同的，此时就需要两个Runable对象，这样就实现了Integer这个共享数据在多个线程中互斥和通信！！ 
 * </pre>
 * @author longgangbai
 *
 */
public class SomeThreadTest2 {  
    
    public static void main(String[] args) {  
        A a = new A();  
        new Thread(new C(a)).start();//创建第一个线程  
        new Thread(new D(a)).start();//创建第二个线程  
    }  
      
}  
  
class C implements Runnable{ //创建第一个Runable对象  
    private A a;  
    public C(A a) {  
        this.a=a;  
    }  
    @Override  
    public void run() {  
        System.out.println(a.Add());;  
    }  
      
}  
  
class D implements Runnable{ //创建第二个Runable对象  
    private A a;  
    public D(A a) {  
        this.a=a;  
    }  
    @Override  
    public void run() {  
        System.out.println(a.Reduce());;  
          
    }  
      
}  
/** 
 * C和D执行的代码不相同，这个时候需要用到不同的Runable对象 
 *  
 * A 
 * 创建人:LiBoMing 
 * 时间：2016年11月16日-上午10:20:08  
 * @version 1.0.0 
 * 
 */  
class A {//存储共享数据的对象  
    private Integer integer = 50;  
    public synchronized Integer Reduce() { //操作共享数据的方法  
        return --integer;  
    }  
  
    public synchronized Integer Add() { //操作共享数据的方法  
        return ++integer;  
    }  
}  