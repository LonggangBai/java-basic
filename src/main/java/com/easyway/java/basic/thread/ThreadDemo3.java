/**
 * 
 */
package com.easyway.java.basic.thread;

/**
 * @author longgangbai 2015-1-20 下午4:36:12
 */
public class ThreadDemo3 {

	public static void main(String[] args) {

		Q q = new Q();
		new Thread(new Producer(q)).start();
		new Thread(new Consumer(q)).start();

	}
}

/*
 * 线程之间的通信是协调线程同步的重要方法 、 Object类的 wait方法通告同步对象进入等待状态,直到其他线程得到同步对象 并且调用 notity方法
 * ,等待线程才会继续执行 notify方法 是通告同步对象的等待线程进入恢复运行 notifyAll通告所有堵塞线程恢复运行 下面是一个生产消费者的问题
 * ,在对于类的操作的时候 一定要有面向对象的思想 。 否则 就会非常的杂乱
 */
class Producer implements Runnable {
	Q q;

	public Producer(Q q) {
		this.q = q;
	}

	public void run() {
		int i = 0;
		while (true) {
			/*
			 * synchronized(q) { if(q.bFull==true) try{q.wait()
			 * ;}catch(Exception ex){} if(i==0) { q.name="zhangsan" ; try{
			 * Thread.sleep(1) ; }catch(Exception ex){} q.sex="male" ; } else {
			 * q.name="lisi" ; q.sex="female" ; } q.bFull=true ; q.notify() ; }
			 * i=(i+1)%2 ;
			 */

			if (i == 0)
				q.push("zhangsan", "male");
			else
				q.push("lisi", "female");
			i = (i + 1) % 2;
		}

	}
}

class Consumer implements Runnable {
	Q q;

	public Consumer(Q q) {
		this.q = q;
	}

	public void run() {
		while (true) {
			/*
			 * synchronized(q) { if(!q.bFull) { try{q.wait() ;}catch(Exception
			 * ex){} } System.out.println(q.name+":"+q.sex) ; q.bFull=false ;
			 * q.notify() ; }
			 */
			q.get();
		}
	}
}

class Q // 以面向对象的思想对线程之间的通信进行封装从而实现消费者 生产社的问题
{
	String name = "unknown";
	String sex = "unknown";
	boolean bFull = false;

	public synchronized void push(String name, String sex) {
		if (this.bFull == true)
			try {
				wait();
			} catch (Exception ex) {
			}
		this.name = name;
		this.sex = sex;
		bFull = true;
		try {
			notify();
		} catch (Exception ex) {
		}
	}

	public synchronized void get() {
		if (this.bFull == false)
			try {
				wait();
			} catch (Exception ex) {
			}
		System.out.println("name:" + this.name + " sex:" + this.sex);
		bFull = false;
		try {
			notify();
		} catch (Exception ex) {
		}
	}
}

class ThreadTest implements Runnable {
	// 这个线程类 来模拟线程的声明结束 因为Thread类的 stop
	// suspend等方法都已经过时了
	// 所以有时候我们对于线程的开始和结束要自己设置标志位来
	boolean bRun = true;
	int index = 0;

	public void stopMe() {
		bRun = false;
	}

	public void run() {
		while (bRun) {
			if (++index > 100)
				stopMe();
			System.out.println(Thread.currentThread().getName()
					+ " is running!");
		}

	}

}
