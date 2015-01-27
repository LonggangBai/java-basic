package com.easyway.java.basic.queue;


import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列 	DelayQueue 
 * 该队列是个优先级队列。被放入的对象需要实现Delayed接口。并指定该对象能被取出的延时时长。
 * 
 */
public class TestDelayQueue {

	public static void main(String[] args) throws Exception {
		DelayQueue<MyDelayed> queue = new DelayQueue<MyDelayed>();
		
		for(int i=1;i<=5;i++){  
			System.out.println("即将放入，延迟为：" + (i * 1000));     
			queue.add(new MyDelayed(i * 1000));  
		}  

		while(queue.size() > 0){
			System.out.println("即将取出");
			MyDelayed s= queue.take();	//延时时间未到就一直等待  
			if(s!=null){  
				System.out.println(System.currentTimeMillis()
						+ "  " + s.getDelayedtimt());// 当前时间 与 添加进去时设定的执行时间比较  
			}  
		}
	}
}

class MyDelayed  implements Delayed{

	long delayedtimt ;	//取出该对象的时间

	/**
	 * @param delayedtimt  延迟取出的时常
	 */
	public MyDelayed(long delayedtimt){	
		this.delayedtimt = System.currentTimeMillis() + delayedtimt; 
	}

	//因为该对象是要放到优先级队列里面去的。 所以要实现该方法，与队列里面的其他元素比较。
	@Override  
	public int compareTo(Delayed o) {
		return (int) (getDelay(TimeUnit.MILLISECONDS)
				- o.getDelay(TimeUnit.MILLISECONDS));
	}

	//返回与该对象关联的剩余延迟，在给定的时间单位。
	@Override
	public long getDelay(TimeUnit unit) {
		long sy =  delayedtimt - System.currentTimeMillis();
		return sy;
	}

	public long getDelayedtimt(){
		return delayedtimt;
	}
	
}