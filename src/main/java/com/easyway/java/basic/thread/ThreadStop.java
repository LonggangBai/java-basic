package com.easyway.java.basic.thread;

/**
 * <pre>
 * Start和Stop
 * 
 * 即将告一段落了，这里说说线程的流程和多线程的生命周期的控制问题吧。
 * 
 * 前面写了那么多的线程的程序，启动线程我们都是调用的start()方法，而线程中运行的是run()方法中的代码，这是为什么呢，为什么不直接调用run()方法呢，
 * 我也试过，没有用，这相当于方法的普通调用，达不到启动一个新的线程的效果，内部机制觉得的吧，就不深究了，说说
 * 
 * start()方法调用后，进入Runnable状态，既是可以运行状态，并不是立即开始运行，调度器可以将cpu的资源分配给它，真正运行后的走向：
 * 
 * 1.      无任何阻隔，运行完毕直接结束，run()方法执行完；
 * 
 * 2.      调度器将cpu分配给其他线程，这个线程变为Runnable状态；
 * 
 * 3.      请求锁旗标，就是判断锁对象的标志位的状态，如果锁定，就等待。
 * 
 * 4.      遇到wait()方法，放弃锁，进入等待池中继续等待，直到有notify(),notifyAll或interrupt()方法的执行，它才会被唤醒或打断进入3步骤；
 * 
 * 5.      遇到sleep方法，会暂停当前线程，不放弃监视器。
 * 
 * 凡事要将善始善终，有了start，就应该有end，前面搞了什么同步，线程间的通信等，都是为了程序在运行的时候不要出问题，那么到了快要结束了，我们就跟
 * 不能让它出问题了，不然前功尽弃，没有人希望这样，JDK中Thread类有几个方法，可以用来终止线程的，但不幸的是在我用的JDK1.6文档中已经标示为过期了。
 * 为什么呢？
 * 
 * 1.      suspend和resume方法会导致死锁，并且它们一个线程通过直接控制另外一个线程的代码。
 * 
 * 2.      stop方法虽然能避免死锁，但是会造成共享数据在线程操作过程中停止，会导致数据不完整。
 * 
 * 解决方案：
 * 
 * 就是我们可以在，线程对类中，加一个标识，通过控制这个标示的值来终止线程，看下面一个小程序：
 * </pre>
 * 
 * @author Administrator
 * 
 */
class ThreadEnd implements Runnable {
	private boolean flag;

	public ThreadEnd() {
		this.flag = true;
	}

	// 写一个方法，停止线程，其实就是讲循环条件改变
	public void stop() {
		this.flag = false;
	}

	public void run() {
		while (flag) {
			System.out.println("I am Running...");
		}
	}
}

public class ThreadStop {
	public static void main(String[] args) {
		int temp = 1;
		ThreadEnd t = new ThreadEnd();
		new Thread(t).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (temp++ <= 100) {
			if (temp == 50) {
				System.out.println("temp-->" + temp);
				t.stop();// 当temp为50时，线程停止
			}
		}
	}
}