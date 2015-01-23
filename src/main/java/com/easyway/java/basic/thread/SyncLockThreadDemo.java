package com.easyway.java.basic.thread;

/**
 * =============================================================================
 * 文件：ThreadDemo_05.java 描述：多线程不同步的解决方法--锁
 * =============================================================================
 */
// 共享一个静态数据对象
class BShareData {
	public static String szData = "";
}

class SyncLockThreadDemo extends Thread {

	private BShareData oShare;

	SyncLockThreadDemo() {
	}

	SyncLockThreadDemo(String szName, BShareData oShare) {
		super(szName);
		this.oShare = oShare;
	}

	public void run() {
		// 为了更清楚地看到不正确的结果，这里放一个大的循环
		for (int i = 0; i < 50; i++) {
			if (this.getName().equals("Thread1")) {
				// 锁定oShare共享对象
				synchronized (oShare) {
					oShare.szData = "这是第 1 个线程";
					// 为了演示产生的问题，这里设置一次睡眠
					try {
						Thread.sleep((int) Math.random() * 100);
					} catch (InterruptedException e) {
					}
					// 输出结果
					System.out.println(this.getName() + ":" + oShare.szData);
				}
			} else if (this.getName().equals("Thread2")) {
				// 锁定共享对象
				synchronized (oShare) {
					oShare.szData = "这是第 2 个线程";
					// 为了演示产生的问题，这里设置一次睡眠
					try {
						Thread.sleep((int) Math.random() * 100);
					} catch (InterruptedException e) {
					}
					// 输出结果
					System.out.println(this.getName() + ":" + oShare.szData);
				}
			}
		}
	}

	public static void main(String[] args) {
		BShareData oShare = new BShareData();
		SyncLockThreadDemo th1 = new SyncLockThreadDemo("Thread1", oShare);
		SyncLockThreadDemo th2 = new SyncLockThreadDemo("Thread2", oShare);

		th1.start();
		th2.start();
	}
}
