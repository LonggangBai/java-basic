package com.easyway.java.basic.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * Lock的使用
 * 用synchronized关键字可以对资源加锁。用Lock关键字也可以。它是JDK1.5中新增内容。用法如下：
 * (注：这是JavaDoc里的例子，是一个阻塞队列的实现例子。所谓阻塞队列，就是一个队列如果满了或者空了，都会导致线程阻塞等待。
 * Java里的 ArrayBlockingQueue提供了现成的阻塞队列，不需要自己专门再写一个了。)
 * 
 * 一个对象的lock.lock()和lock.unlock()之间的代码将会被锁住。这种方式比起synchronize好在什么地方？简而言之，
 * 就是对wait的线程进行了分类。用厕位理论来描述，则是那些蹲了一半而从厕位里出来等待的人原因可能不一样，有的是因为马桶堵了
 * ，有的是因为马桶没水了。通知(notify)的时候，就可以喊：因为马桶堵了而等待的过来重新排队（比如马桶堵塞问题被解决了），
 * 或者喊，因为马桶没水而等待的过来重新排队(比如马桶没水问题被解决了）。这样可以控制得更精细一些。不像synchronize里的
 * wait和notify，不管是马桶堵塞还是马桶没水都只能喊：刚才等待的过来排队！假如排队的人进来一看，发现原来只是马桶堵塞问
 * 题解决了，而自己渴望解决的问题（马桶没水）还没解决，只好再回去等待(wait)，白进来转一圈，浪费时间与资源。
 * 
 * Lock方式与synchronized对应关系：
 * 
 * Lock			await	signal	signalAll
 * synchronized	wait	notify	notifyAll
 * 注意：不要在Lock方式锁住的块里调用wait、notify、notifyAll
 * 
 * </pre>
 * 
 * @author Administrator
 * 
 */
class BoundedBuffer {
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	final Object[] items = new Object[100];
	int putptr, takeptr, count;

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length)
				notFull.await();
			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				notEmpty.await();
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
}