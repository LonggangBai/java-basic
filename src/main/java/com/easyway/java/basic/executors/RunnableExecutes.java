package com.easyway.java.basic.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * static Callable<Object> callable(Runnable task) 把Runnable任务转换成Callable任务.例子如下
 * 
 * @author Administrator
 * 
 */
public class RunnableExecutes {

	public static void main(String[] args) {
		Runnable task = new Runnable() {
			public void run() {
				System.out.println("begin task");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				System.out.println("end task");
			}
		};
		Callable c = Executors.callable(task);
		ExecutorService service = Executors.newCachedThreadPool();
		Future f = service.submit(c);
		try {
			System.out.println(f.get());
			// 返回null
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
