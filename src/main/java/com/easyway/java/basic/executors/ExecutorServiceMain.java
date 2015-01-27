package com.easyway.java.basic.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 任务提交者与执行者通讯
 * 
 * <pre>
 * Executors.newSingleThreadExecutor()取得的Executor实例有以下特性:
 * 
 * 任务顺序执行. 比如：
 * executor.submit(task1);
 * executor.submit(task2);
 * 必须等task1执行完，task2才能执行。
 * 
 * task1和task2会被放入一个队列里，由一个工作线程来处理。即：一共有2个线程(主线程、处理任务的工作线程）。
 * 其它的类请参考Java Doc
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class ExecutorServiceMain {
	public static void main(String args[]) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<String> task = new Callable<String>() {
			public String call() throws Exception {
				return "test";
			}
		};
		Future<String> f = executor.submit(task);
		String result;
		try {
			result = f.get();
			// 等待（阻塞）返回结果
			System.out.println(result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}

	}
}
