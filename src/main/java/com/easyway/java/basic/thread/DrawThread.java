package com.easyway.java.basic.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * 分别解释一下程序，进程和线程的概念和区别?
 * 程序 是计算机指令的集合它，以文件的形式存储在磁盘上。
 * 进程是一个程序在其 自身的地址空间中的一次执行活动。
 * 进程是资源申请、调度和独立运行的单位，因此，它使用系统中的运行资源，而程序不能申请系统资源，不能被系统调度，也不能作为独
 * 立运行的单位，因此，它不占系统的运行资源。
 * 线程：进程中的一个单一的连续控制流程。一个进程可以拥有多个线程。
 * 线程又称轻量级进程，它和进程一样拥有独立的执行控制，由操作系统负责调度，区别在于线程没有独立的存储空间，而是和所属进程中的
 * 其它线程共享一个存储空间，这使得线程间的通信远较进程简单。
 * 系统会为每个线程分配一个时间片。
 * 在java中每个线程都 有一个优先级。
 * java运行时系统实现了一个用于调度线程执行的线程调度器，用于确定某一时刻由哪个线程在CPU上运行。
 * 在java中，线程通常是抢占式的而不需要时间片分配进程（分配给每个线程相等的CPU时间的进程），但实际上只有一个线程在运行。该线
 * 程一直运行到它终止进入等待状态，或者另一个具有更高优先级的线程变成可运行状态。在后一种情况下，低优先级的线程被高优先级的线程
 * 抢占，高优先级的线程获得运行的机会。
 * java线程调度器支持不同优先级线程的抢占方式，但其本身不支持相同优先级线程的时间片轮换。
 * java运行时系统所在的操作系统支持时间片的轮换，则线程调度器就支持相同优先级线程的时间片轮换。
 * </pre>
 * 
 * @author Administrator
 * 
 */
class Account {
	/**
	 * 同步锁
	 */
	private final ReentrantLock lock = new ReentrantLock();
	/**
	 * 账户号
	 */
	private String accountNo;
	/**
	 * 账户余额
	 */
	private double balance;

	public Account() {
		super();
	}

	public Account(String accountNo, double balance) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return accountNo.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (null != obj && obj.getClass() == Account.class) {
			Account target = (Account) obj;
			return target.accountNo.equals(accountNo);
		}
		return false;
	}

	/**
	 * 同步方法，同步方法的监视器是this，同步方法可以将该类变为线程安全的类
	 * 任何时候只有一个线程获得对Account对象的锁定，然后进入draw方法进行取钱
	 */
	public void draw(double drawAmount) {
		lock.lock();
		try {
			if (balance >= drawAmount) {
				System.out.println(Thread.currentThread().getName() + "取出钞票成功"
						+ drawAmount);
				balance -= drawAmount;
				System.out.println("余额为" + balance);
			} else {
				System.out.println("余额不足");
			}
		} finally {
			// 用finally块保证释放锁
			lock.unlock();
		}
	}

	/* 只提供Getters，不提供Setters，确保安全 */
	public String getAccountNo() {
		return accountNo;
	}

	public double getBalance() {
		return balance;
	}
}

public class DrawThread extends Thread {
	/**
	 * 模拟账户
	 */
	private Account ac;
	/**
	 * 当前取钱线程希望取得的钱数
	 */
	private double drawAmount;

	public DrawThread(String name, Account ac, double drawAmount) {
		super(name);
		this.ac = ac;
		this.drawAmount = drawAmount;
	}

	@Override
	public void run() {
		ac.draw(drawAmount);
	}

	public static void main(String[] args) {
		Account ac = new Account("00000001", 1000);
		Thread t1 = new Thread(new DrawThread("Lily", ac, 800));
		Thread t2 = new Thread(new DrawThread("Tom", ac, 800));
		t1.start();
		t2.start();
	}
}
