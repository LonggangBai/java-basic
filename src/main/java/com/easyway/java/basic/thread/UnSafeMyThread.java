package com.easyway.java.basic.thread;

/**
 * <pre>
 * 要想解决“脏数据”的问题，最简单的方法就是使用synchronized关键字来使run方法同步，代码如下：
 * 
 * public synchronized void run()  
 * {  
 *        
 * } 
 * 从上面的代码可以看出，只要在void和public之间加上synchronized关键字，就可以使run方法同步，也就是说，对于同一个Java类的对象实例，run方法同时只能被一个线程调用，并当前的run执行完后，才能被其他的线程调用。即使当前线程执行到了run方法中的yield方法，也只是暂停了一下。由于其他线程无法执行run方法，因此，最终还是会由当前的线程来继续执行。先看看下面的代码：
 * 
 * sychronized关键字只和一个对象实例绑定
 * 
 * class Test  
 * {  
 *       public synchronized void method()  
 *      {  
 *             
 *      }  
 * }  
 *    
 * public class Sync implements Runnable  
 * {  
 *      private Test test;  
 *      public void run()  
 *      {  
 *           test.method();  
 *      }  
 *      public Sync(Test test)  
 *      {  
 *          this.test = test;  
 *      }  
 *      public static void main(String[] args) throws Exception  
 *      {  
 *          Test test1 =  new Test();  
 *          Test test2 =  new Test();  
 *          Sync sync1 = new Sync(test1);  
 *          Sync sync2 = new Sync(test2);  
 *          new Thread(sync1).start();  
 *          new Thread(sync2).start();   
 *      }  
 *  } 
 * 在Test类中的method方法是同步的。但上面的代码建立了两个Test类的实例，因此，test1和test2的method方法是分别执行的。要想让method同步，
 * 必须在建立Sync类的实例时向它的构造方法中传入同一个Test类的实例，如下面的代码所示：
 * 
 * Sync sync1 = new Sync(test1);     不仅可以使用synchronized来同步非静态方法，也可以使用synchronized来同步静态方法。如可以按如
 * 下方式来定义method方法：
 * 
 * class Test   
 * {  
 *     public static synchronized void method() {   }  
 * } 
 * 建立Test类的对象实例如下：
 * 
 * Test test = new Test(); 
 * 对于静态方法来说，只要加上了synchronized关键字，这个方法就是同步的，无论是使用test.method()，还是使用Test.method()来调用method方法，
 * method都是同步的，并不存在非静态方法的多个实例的问题。
 * 
 * 在23种设计模式中的单件（Singleton）模式如果按传统的方法设计，也是线程不安全的，下面的代码是一个线程不安全的单件模式。
 * 
 * 
 * 
 * 
 * </pre>
 * 
 * @author Administrator
 * 
 */
// 线程安全的Singleton模式
class UnSafeSingleton {
	private static UnSafeSingleton sample;

	private UnSafeSingleton() {
	}

	public static UnSafeSingleton getInstance() {
		if (sample == null) {
			Thread.yield(); // 为了放大Singleton模式的线程不安全性
			sample = new UnSafeSingleton();
		}
		return sample;
	}
}

public class UnSafeMyThread extends Thread {
	public void run() {
		UnSafeSingleton singleton = UnSafeSingleton.getInstance();
		System.out.println(singleton.hashCode());
	}

	public static void main(String[] args) {
		Thread threads[] = new Thread[5];
		for (int i = 0; i < threads.length; i++)
			threads[i] = new UnSafeMyThread();
		for (int i = 0; i < threads.length; i++)
			threads[i].start();
	}
}