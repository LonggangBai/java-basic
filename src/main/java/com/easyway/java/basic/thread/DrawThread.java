package com.easyway.java.basic.thread;

import java.util.concurrent.locks.ReentrantLock;

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

