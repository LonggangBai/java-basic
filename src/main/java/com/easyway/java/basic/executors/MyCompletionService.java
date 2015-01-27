package com.easyway.java.basic.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * Table of Contents
 * 1 Executor接口
 * 留给开发者自己实现的接口，一般情况下不需要再去实现。它只有一个方法
 * void execute(Runnable command)2 ExecutorService接口
 * 它继承自Executor接口，但多了如下3个功能
 * 终止任务。体现在这几个方法
 * void shutdown()
 * 线程池关闭，不接受新任务，即不能再执行submit(task)，如果你硬要执行新任务则会抛异常。
 * 还没运行完的任务继续运行完毕.
 * List<Runnable> shutdownNow()
 * 试图停止所有正在执行的活动任务，但不一定成功。
 * 返回等待执行的任务列表。
 * 任务提交者与任务的通信，就是Future对象的使用。Future有点类似 windows开发中的句柄概念，通过它可对任务进行监控并获取任务信息。有三种使用方式: 1. <T> Future<T> submit(Callable<T> task)
 * 2. Future<?> submit(Runnable task) 
 * 3. <T> Future<T> submit(Runnable task, T result)
 * 第一种方法提交的任务，当任务完成后，可以通过Future.get()获取任务的返回值.
 * 第三种方法提交的任务，当任务完成后，可以通过Future.get()获取提交时传递的参数T result. 比如： Future<String> future = service.submit(task,"aaaa");
 * String s = future.get();// s="aaaa";
 * 有趣的是第二种方式，因为它的Future.get()得不到任何内容，它返回值是Null，那为什么不直接把ExecutorService的接口设计成如下这种形式呢 void submit(Runnable task)
 * 这是因为Future除了get这种获取任务信息外，还可以控制任务，体现在 Future的这个方法上
 * boolean cancel(boolean mayInterruptIfRunning)
 * 这个方法的JavaDoc解释的很清楚
 * Attempts to cancel execution of this task. This attempt will
 * fail if the task has already completed, already been
 * cancelled, or could not be cancelled for some other reason. If
 * successful, and this task has not started when cancel is
 * called, this task should never run. If the task has already
 * started, then the mayInterruptIfRunning parameter determines
 * whether the thread executing this task should be interrupted
 * in an attempt to stop the task.
 * 注意：如果任务没执行完，这3种方式的Future.get()都会阻塞。
 * 终止任务后可以阻塞一会儿。由ExecutorService的下面这个方法完成 boolean awaitTermination(long timeout,TimeUnit unit)
 * 比如
 * service.shutdown();
 * service.awaitTermination(20, TimeUnit.SECONDS);
 * 它的意思是，当service.shutdown()后，主线程阻塞，任务执行结束或者阻塞20秒以后，阻塞解除。
 * 3 ScheduledExecutorService接口
 * 它继承自ExecutorService接口。顾名思义，它主要用来定期执行任务或周期执行任务。它只有4个方法，都比较好理解。下面用最简单的例子来做个说明
 * scheduler.schedule(callable,10,SECONDS); //10秒后开始执行callable任务
 * scheduler.schedule(runnable,10,Seconds); //10秒后开始执行runnable任务
 * scheduler.scheduleAtFixedRate(runnable, 5, 10, SECONDS); //5秒后开始执行Runnable任务，然后每隔10秒执行一遍该任务.
 * scheduler.scheduleWithFixedDelay(runnable,5,10,SECONDS); //5秒后开始执行Runnable任务，然后任务执行完后再等10秒就执行一遍任务，即，每隔任务执行的时间+10秒再执行一遍任务。
 * 这4个方法都返回ScheduledFuture对象.它继承自Future接口，用途和Future差不多。
 * 在JDK之前，计划任务一般由java.until.Timer类来完成。但相比起 ScheduleExecutorService来说，Timer类的功能较为简单，比如下例
 * private final static long fONCE_PER_DAY = 1000*60*60*24;
 * Timer timer = new Timer();
 * timer.scheduleAtFixedRate(fetchMail, getTomorrowMorning4am(), fONCE_PER_DAY);
 * Timer没有返对象（我总理解为一种句柄），不方便细粒度控制任务。
 * Timer的任务类型比较单一，只有TimerTask一种。
 * 没有类似scheduleWithFixedDelay的方法。
 * 间隔时间的表示方法也不友好。
 * 性能上也不如ScheduledExecutorService(通过线程池等方式进行了性能优化）出色。
 * 4 Executors类
 * 这个类是一个工厂类，用来生成不同特点的ExecutorService或 ScheduledExecutorService实例。这里主要介绍这些不同特点的实例不同在什么地方。
 * 3类不同的ExecutorService实例. static ExecutorService newSingleThreadExecutor()
 * 启动一个线程负责任务顺序执行，顺序意味着先提交的任务先执行。其原理是：任务会被提交到一个队列里，启动的那个线程会从队里里取任务，然后执行，执行完，再从队列里取下一个任务，再执行。如果该线程执行一个任务失败，并导致线程结束，系统会创建一个新的线程去执行队里里后续的任务，不会因为前面的任务有异常导致后面无辜的任务无法执行。
 * static ExecutorService newCachedThreadPool()
 * 启动N个线程处理N个任务。既然是多个线程运行，意味着任务不会顺序运行。一个任务完成后，该线程空闲60秒会被结束。新提交的任务会发现空闲线程，并使用它，如果没有空闲线程可用则创建新线程。其实，这就是一个动态线程池。适合于规模比较小、创建较频繁的任务。
 * static ExecutorService newFixedThreadPool(int nThreads)
 * 动态线程池不限制线程的数量，在有些情况下我们不希望线程数量不可控，则可以使用拥有固定线程数目的线程池。运作原理是：任务被提交到一个队列里排队，线程池里的空闲线程会把队列里的任务提出来执行，每个线程执行完一个任务后，就去队列里抓另一个任务出来执行。如果一个线程由于失败而终止，系统会创建另一个线程执行后续任务。
 * 带ThreadFactory参数生成的ExecutorService实例。以上3种实例创建工作线程时都是用的默认的线程工厂类来创建。也可以指定自己的线程工厂类来创建，以newSingleThreadExecutor(ThreadFactory threadFactory)为例: //你自己的实现
 * class YourselfThreadFactory implements ThreadFactory {
 * public Thread newThread(Runnable r) {
 * Thread thread = new Thread(r);
 * doXXX;
 * return thread;
 * }
 * }
 * newSingleThreadExecutor 与 newFixedThreadPool(1) 区别.
 * JavaDoc上说：Unlike the otherwise equivalent newFixedThreadPool(1) the returned executor is guaranteed not to be reconfigurable to use additional threads.
 * 什么意思？不懂。为什么？不具体。具体一下就懂了。
 * ((ThreadPoolExecutor)newFixedThreadPool(1)).setCorePoolSize(3);
 * 即newFixedThreadPool(1)可以后期修改线程数，不能保证线程只有一个。而newSingleThreadExecutor可以保证。
 * static Callable<Object> callable(Runnable task)
 * 把Runnable任务转换成Callable任务.例子如下
 * public static void test() throws Exception {
 * Runnable task = new Runnable() {
 * public void run() {
 * log("begin task");
 * try {
 * Thread.sleep(1000);
 * } catch (InterruptedException e) {
 * }
 * log("end task");
 * }
 * };
 * Callable c = Executors.callable(task);
 * ExecutorService service = Executors.newCachedThreadPool();
 * Future f = service.submit(c);
 * System.out.println(f.get());//返回null
 * log("end");
 * }
 * 
 * private static void log(String message) {
 * System.out.println(new Date() + ": " + message);
 * }
 * 
 * public static void main(String args[]) throws Exception {
 * test();
 * }
 * 5 结束
 * JDK1.5引入的concurrent包使多线程编写更加容易、代码更容易理解、可读性更好. 相关文章，请参考
 * 
 * CompletionService
 * 将生产新的异步任务与使用已完成任务的结果分离开来的服务。生产者 submit 执行的任务。使用者 take 已完成的任务，
 * 并按照完成这些任务的顺序处理它们的结果。例如，CompletionService 可以用来管理异步 IO ，执行读操作的任务作为程序或系统的一部分提交，
 * 然后，当完成读操作时，会在程序的不同部分执行其他操作，执行操作的顺序可能与所请求的顺序不同。
 * 通常，CompletionService 依赖于一个单独的 Executor 来实际执行任务，在这种情况下，
 * CompletionService 只管理一个内部完成队列。ExecutorCompletionService 类提供了此方法的一个实现。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class MyCompletionService implements Callable<String> {
	private int id;

	public MyCompletionService(int i) {
		this.id = i;
	}

	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newCachedThreadPool();
		CompletionService<String> completion = new ExecutorCompletionService<String>(
				service);
		for (int i = 0; i < 10; i++) {
			completion.submit(new MyCompletionService(i));
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(completion.take().get());
		}
		service.shutdown();
	}

	public String call() throws Exception {
		Integer time = (int) (Math.random() * 1000);
		try {
			System.out.println(this.id + " start");
			Thread.sleep(time);
			System.out.println(this.id + " end");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.id + ":" + time;
	}
}