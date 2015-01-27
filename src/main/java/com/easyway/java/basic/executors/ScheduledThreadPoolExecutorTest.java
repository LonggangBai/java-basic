package com.easyway.java.basic.executors;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor 定时任务线程池 此类 类似于Timer ，但优于Timer
 * 
 * @author Administrator
 * 
 */
public class ScheduledThreadPoolExecutorTest {

	public static void main(String[] args) {

		ScheduledThreadPoolExecutor poll = new ScheduledThreadPoolExecutor(1);

		poll.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println(new Date().toLocaleString());
			}
		}, 0, 1, TimeUnit.SECONDS);

	}

}
