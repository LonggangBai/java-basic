/**
 * 
 */
package com.easyway.java.basic.thread;

/*
 *   java多线程中 有前台线程和后台线程，前台线程 是指 main所在的主线程 和其他 创建的线程 ,如果在线程调用start 之前调用  setDeamon(true)
 *   那么 这个线程就是一个后台线程,在进程中如果没有一个前台线程 那么后台线程也随之退出,从而进程也退出 。如果没有调用setDeamon(true)或者
 *   调用setDeamom(false)那么这个线程就是前台线程 ,只要一个进程中还存在前台线程 那么即使mian方法所在的线程退出了 ,那么这个前台子线程也会继续执行
 *   直至退出  。
 *   Tread类额join方法 是将一个线程合并到另一个线程中, 可以设置合并的时间间隔 
 *   我们实现自己的线程类有2中方法  :
 *   1、直接从Thread继承而来  实现 run方法  。
 *   2、实现Runnable接口,并且实现run方法 。 Thread  th=new Thread(....) ;//吧我们自己实现的类的对象作为参数传进去   .
 *   join 方法可以将一个线程合并到另一个线程 中 而且还可以指定线程合并的时间间隔 
 *
 */
public class ThreadDemo1 {
	public static void main(String[] args) {
		// MyThread tt=new MyThread() ; tt.start() ;可以从 Thread类派生一个线程类
		Thread tt = new Thread(new MyThread1()); // 可以通过Thread类的带参数的构造方法
		// 传递一个实现了Runnable接口的对象
		// tt.setDaemon(true) ;//将线程设置为 后台线程 主线层退出这个线程也会随着退出
		tt.start();
		int index = 0;
		while (true) {
			if (index++ == 100)
				try {
					tt.join(5000);
				} catch (Exception ex) {
					System.out.println(ex.toString());
				}

			System.out.println("run:" + Thread.currentThread().getName());
		}
	}

}

class MyThread1 implements Runnable// extends Thread
{
	public void run() {
		while (true) {
			System.out.println("run:" + Thread.currentThread().getName());
		}
	}
}
