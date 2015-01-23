package com.easyway.java.basic.thread;

/**
 * =============================================================================
 * 文件：ThreadDemo_04.java 描述：多线程不同步的原因
 * =============================================================================
 * 
 * <pre>
 * 
 * 三、线程的同步问题
 *    有些时候，我们需要很多个线程共享一段代码，比如一个私有成员或一个类中的静态成员，但是由于线程赛跑的问题，所以我们得到的常常不
 *    
 *    由于线程的赛跑问题，所以输出的结果往往是Thread1对应“这是第 2 个线程”，这样与我们要输出的结果是不同的。为了解决这种问题（错误），
 *    Java为我们提供了“锁”的机制来实现线程的同步。锁的机制要求每个线程在进入共享代码之前都要取得锁，否则不能进入，而退出共享代码之前则
 *    释放该锁，这样就防止了几个或多个线程竞争共享代码的情况，从而解决了线程的不同步的问题。可以这样说，在运行共享代码时则是最多只有一个
 *    线程进入，也就是和我们说的垄断。
 *    是正确的输出结果，而相反常常是张冠李戴，与我们预期的结果大不一样。看下面的例子：
 * </pre>
 */
// 共享一个静态数据对象
class ShareData {
	public static String szData = "";
}

public class BugSyncThreadDemo extends Thread {

	private ShareData oShare;

	BugSyncThreadDemo() {
	}

	BugSyncThreadDemo(String szName, ShareData oShare) {
		super(szName);
		this.oShare = oShare;
	}

	public void run() {
		// 为了更清楚地看到不正确的结果，这里放一个大的循环
		for (int i = 0; i < 50; i++) {
			if (this.getName().equals("Thread1")) {
				oShare.szData = "这是第 1 个线程";
				// 为了演示产生的问题，这里设置一次睡眠
				try {
					Thread.sleep((int) Math.random() * 100);
				} catch (InterruptedException e) {
				}
				// 输出结果
				System.out.println(this.getName() + ":" + oShare.szData);
			} else if (this.getName().equals("Thread2")) {
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

	public static void main(String[] args) {
		ShareData oShare = new ShareData();
		BugSyncThreadDemo th1 = new BugSyncThreadDemo("Thread1", oShare);
		BugSyncThreadDemo th2 = new BugSyncThreadDemo("Thread2", oShare);

		th1.start();
		th2.start();
	}
}