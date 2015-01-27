package com.easyway.java.basic.executors;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * <pre>
 * Future
 * Future 表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并检索计算的结果。
 * 计算完成后只能使用 get 方法来检索结果，如有必要，计算完成前可以阻塞此方法。取消则由 cancel 方法来执行。
 * 还提供了其他方法，以确定任务是正常完成还是被取消了。一旦计算完成，就不能再取消计算。
 * 如果为了可取消性而使用 Future但又不提供可用的结果，则可以声明 Future<?> 形式类型、并返回 null 作为基础任务的结果。
 * 这个我们在前面CompletionService已经看到了，这个Future的功能，而且这个可以在提交线程的时候被指定为一个返回对象的。
 * 
 * ScheduledExecutorService
 * 一个 ExecutorService，可安排在给定的延迟后运行或定期执行的命令。
 * schedule 方法使用各种延迟创建任务，并返回一个可用于取消或检查执行的任务对象。scheduleAtFixedRate 和 scheduleWithFixedDelay 方法创建并执行某些在取消前一直定期运行的任务。
 * 用 Executor.execute(java.lang.Runnable) 和 ExecutorService 的 submit 方法所提交的命令，通过所请求的 0 延迟进行安排。
 * schedule 方法中允许出现 0 和负数延迟（但不是周期），并将这些视为一种立即执行的请求。
 * 所有的 schedule 方法都接受相对 延迟和周期作为参数，而不是绝对的时间或日期。将以 Date 所表示的绝对时间转换成要求的形式很容易。
 * 例如，要安排在某个以后的日期运行，可以使用：schedule(task, date.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS)。
 * 但是要注意，由于网络时间同步协议、时钟漂移或其他因素的存在，因此相对延迟的期满日期不必与启用任务的当前 Date 相符。
 * Executors 类为此包中所提供的 ScheduledExecutorService 实现提供了便捷的工厂方法。
 * 一下的例子也是网上比较流行的。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class TestScheduledThread {
	public static void main(String[] args) {
		final ScheduledExecutorService scheduler = Executors
				.newScheduledThreadPool(2);
		final Runnable beeper = new Runnable() {
			int count = 0;

			public void run() {
				System.out.println(new Date() + " beep " + (++count));
			}
		};
		// 1秒钟后运行，并每隔2秒运行一次
		final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(
				beeper, 1, 2, SECONDS);
		// 2秒钟后运行，并每次在上次任务运行完后等待5秒后重新运行
		final ScheduledFuture beeperHandle2 = scheduler.scheduleWithFixedDelay(
				beeper, 2, 5, SECONDS);
		// 30秒后结束关闭任务，并且关闭Scheduler
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
				beeperHandle2.cancel(true);
				scheduler.shutdown();
			}
		}, 30, SECONDS);
	}
}