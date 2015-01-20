/**
 * 
 */
package com.easyway.java.basic.thread;

/*   Runable接口比直接从Thread继承方便的多  。  
 *    new  Thread(...) ;这样即使我们传递了同一个实现了Runnable接口的多个对象那么 也是多个线程 ,而且多个线程共享数据域.
 *    否则new Thread 创建的多个线程之间 互不相干  ，数据之间互不干涉 
 *    同步就是为了实现 在多个线程同时对一个资源进行操作的时候 要一个一个的执行 ,
 *    只有等占有CPU的 线程完事之后 其他等待线程才能进入就绪状态等待运行
 *    java中可以用 synchrozined(Object obj){}      实现代码块的同步      参数是任意对象  
 *    不但可以利用synchronized语句块  也可以在方法的前面 声明 synchronized       
 *    同步原理是对象的标志位 初始为1  当进入代码块后 obj的标志位变为 0  这时候其他线程则不能 进入代码块执行 而进入等待状态
 *    直到先进入的那个线程完事然后就会为这个线程解锁 。 其他线程才可能开始运行   火车票的售票系统就是一个多线程很好的运用的例子 
 *    同步是以程序的性能为代价的   ,同步方法是以类的this对象为同步对象的  而 synchronized块是以我们指定的对象为 同步对象
 *    如果想让代码块和同步方法同步那么 使用的同步对象 必须都为this
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
	MyThread2 mt = new MyThread2();
	// mt.start() ;
	new Thread(mt).start();
	try {
	    Thread.sleep(10); // 每当产生一个线程CPU不会立马去执行 ，这之间既有一个微小的时间间隔 。
	} catch (Exception e) {
	    System.out.println(e.toString());
	}
	mt.str = "method";
	new Thread(mt).start();
    }
}

class MyThread2 implements Runnable// extends Thread
{
    int tickets = 100;
    String str = new String("");

    public void run() {
	if (str.equals("method")) {
	    while (true) {
		running();
	    }
	} else {
	    while (true) {
		synchronized (str) {
		    if (tickets > 0) {
			System.out.println("block:" + Thread.currentThread().getName() + "sells" + tickets--);
		    }
		}

	    }
	}
    }

    public synchronized void running() {
	if (tickets > 0) {
	    try {
		Thread.sleep(10);
	    } catch (Exception ex) {
	    }
	    System.out.print("method：");
	    System.out.println(Thread.currentThread() + "sell " + tickets--);
	}
    }
}
