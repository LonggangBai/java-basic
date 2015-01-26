package com.easyway.java.basic.thread;

/**
 * 
 * 
 * <pre>
 * join()方法：jdk中只有一句话描述：等待该线程终止，很精简。先写个小程序玩玩：
 * 在上面的程序中，当flag等于5时，主线程的打印语句就不在执行，而是OtherThread中run方法的中的打印语句，知道这个线程执行完毕，
 * 在打印主线程中的语句，意思是将这个线程合并到当前线程，所以，join方法的作用，是给调用这个方法的线程对象一个特权，就是让它单独执
 * 行完毕后在恢复正常，就比如我们开会过程中，主持人在做开始前的讲话，陆陆续续的有人进来入座，突然有个大领导进来，主持人就会提醒大家
 * ***入场，开始欢迎，然后就是鼓掌欢迎，主持人就停止讲话，知道领导坐好，掌声停止，他才开始讲话。
 * 同时join方法有join(long millis)，join(long millis, int nanos)两个重载的方法，设置合并时长，达到时长后又分离，一
 * 个是毫秒，一个是精确到纳秒。
 * 
 * 
 * yield()方法
 * 
 *     是一个静态方法，在JDK文档中，是这样描述的：暂停当前正在执行的线程对象，并执行其他线程，听起来跟join的描述有点类似，我也做过实验，
 *     好像没有什么作用，不调用这个方法时，线程交替执行，调用后还是交替执行，yield()方法是让当前线程暂停，由于没有说明执行其他线程的时间，
 *     很有可能，执行了其他同优先级的线程后马上又回到该线程了。以便让具有相同优先级的线程进入执行状态，但不是绝对的。因为虚拟机可能会让该线程
 *     重新进入执行状态，另外优先级别再windows系统中不可靠，所以这个方法方法不如sleep好使，不多说了。
 * 
 * 
 * sleep(long millis), sleep(long millis,int nanos)
 * 
 * 显然是睡觉的意思，就是让当前正在执行的线程暂停，但是不释放资源，这个可以与wait()方法区分开来，所谓的资源，意思就是锁的，在后面线程的同步会
 * 讲，wait()方法在线程间的通信会讲到，这个是Object的方法。
 * 
 * 
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class JoinThread {
	public static void main(String[] args) throws InterruptedException {
		Integer flag = new Integer(1);
		OtherThread ot = new OtherThread(flag);
		Thread t = new Thread(ot);
		t.start();
		while (true) {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName()
					+ "------>is running......" + flag);
			if (flag++ == 5) {
				try {
					t.join();// 将t线程合并到主线程，主线程释放执行权，先执行t线程
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class OtherThread implements Runnable {
	private int flag;

	public OtherThread(int flag) {
		this.flag = flag;
	}

	public void run() {
		while (flag < 5) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()
					+ "-->is running..." + flag);
		}
	}
}