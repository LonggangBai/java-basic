package com.easyway.java.basic.thread;

/**
 * 
 * 
 * <pre>
 * wait(): 当调用wait()方法后，告诉了当前线程放弃监视器，就是我们前面说的锁，进入睡眠状态，自己被放到等待池中，直到其他线程
 * 进入同一监视器，并调用了notify()或notifyAll()方法，将其唤醒，它也sleep的区别是，它会放弃监视器，而sleep不会放弃，就
 * 是当前线程sleep了，别的线程不能进入，好比我们晚上睡觉要把房间的门锁上，别人进不来了。
 * 
 * notify():唤醒同一对象监视器调用wait()方法的第一个线程，好比酒店的生意很好，有很多人在排队，一有空位就会通知排在第一位的
 * 客户，当然这个客户必须是在这个酒店里，如果在别的酒店里，就不行了，呵呵，相信，这点都能理解。
 * 
 * notifyAll():唤醒同一对象监视器中调用wait()方法的所有线程，，具有最高优先级的线程先被唤醒并执行，好比，火车检票进站，一
 * 道检票时间，就唤醒所有等待的乘客，你们可以检票了，这是大家都被唤醒了，都起来了，当然只有最前面的人最先进去，同理，别的火车的
 * 乘客是唤不醒的，不然他就要坐错火车了，那样就不好了。
 * </pre>
 * 
 * @author Administrator
 * 
 */
class Temple {
	private String husband;
	private String wife;
	private boolean flag = false;// 将标记为false，boolean成员属性缺省值也是false

	// 为了更好模拟，初始时，祠堂没有人
	public Temple() {
	}

	// 司仪的代码功能,注意这里家里同步
	public synchronized void emceePut(String husband, String wife) {
		if (flag) {
			try {
				// 如果有人，flag为true，即庙里有一对了，就等待，释放锁
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.husband = husband;
		this.wife = wife;
		flag = true;// 放了一对新人进去，标记为true，即表示庙里有人了
		notify();// 通知小p孩叫喊
	}

	// 小p孩的代码功能，这里也加了同步
	public synchronized void pChildSay() {
		if (!flag) {
			try {
				// 如果庙里没有人，小p孩就等待，不叫唤
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(husband + "和 " + wife + " 是一对恩爱的夫妻啊!");
		flag = false; // 叫唤完了后，将flag标记为false，
		notify();// 给司仪通知即庙里的新人我喊过了，司仪可以换另外的新人了
	}
}

// 同样Emecc和PChild也要做一些小的改动：
// 司仪
class Emcee implements Runnable {
	private Temple temple;

	public Emcee(Temple temple) {
		this.temple = temple;
	}

	@Override
	public void run() {
		int temp = 0;
		while (true) {
			if (temp == 0) {
				temple.emceePut("husband2", "wife2");
			} else {
				temple.emceePut("husband1", "wife1");
			}
			temp = (temp + 1) % 2;// 这是让temp在0,1循环
		}
	}
}

// 小p孩类
class PChild implements Runnable {
	private Temple temple;

	public PChild(Temple temple) {
		this.temple = temple;
	}

	@Override
	public void run() {
		while (true) {
			temple.pChildSay();
		}
	}
}