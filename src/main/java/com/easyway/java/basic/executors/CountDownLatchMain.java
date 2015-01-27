package com.easyway.java.basic.executors;

import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 * java.util.concurrent包是在并发中很常用的实用工具类。此包包括了几个小的、已标准化的可扩展框架，以及一些提供有用功能的类，没有这些类，
 * 这些功能很难实现或实现起来冗长乏味。下面简要描述主要的组件。
 * 
 * 一、执行程序
 * 
 * 1、接口
 *     Executor是一个简单的标准化接口，用于定义类似于线程的自定义子系统，包括线程池、异步I/O和轻量级任务框架。
 * 根据所使用的具体Executor类的不同，可能在新创建的线程中，现有的任务执行线程中，或者调用execute()的线程中执行任务，并且可能顺序或并发执行。
 * 		ExecutorService提供了多个完整的异步任务执行框架。ExecutorService管理任务的排队和安排，并允许受控制的关闭。
 * 		ScheduledExecutorService子接口添加了对延迟的和定期任务执行的支持。
 * 		ExecutorService提供了安排异步执行的方法，可执行由Callable表示的任何函数，结果类似于Runnable。
 * 		Future返回函数的结果，允许确定执行是否完成，并提供取消执行的方法。
 *  2、实现类
 * 		类ThreadPoolExecutor和ScheduledThreadPoolExecutor提供可调的、灵活的线程池。
 * 		Executors类提供大多数Executor的常见类型和配置的工厂方法，以及使用它们的集中实用工具方法。
 * 其它基于Executor的实用工具包括具体类FutureTask，它提供Future的常见扩展实现，以及ExecutorCompletionService，
 * 它有助于协调对异步任务组的处理。 
 * 
 * 二、队列
 * 		java.util.concurrent.ConcurrentLinkedQueue<E>提供了高效的、可伸缩的、线程安全的非阻塞FIFO队列
 * 		java.util.concurrent中的五个实现都支持扩展的BlockingQueue接口，该接口定义了put和take的阻塞版本：
 * LinkedBlockingQueue、ArrayBlockingQueue、SynchronousQueue、PriorityBlockingQueue和DelayQueue
 * 。这些不同的类覆盖了生产者-使用者、消息传递、并行任务执行和相关并发设计的大多数常见使用的上下文。
 * 
 *  三、计时
 * 		TimeUnit类为指定和控制基于超时的操作提供了多重粒度
 * （包括纳秒级）。该包中的大多数类除了包含不确定的等待之外，还包含基于超时的操作。在使用超时的所有情况中
 * ，超时指定了在表明已超时前该方法应该等待的最少时间。在超时发生后
 * ，实现会“尽力”检测超时。但是，在检测超时与超时之后再次实际执行线程之间可能要经过不确定的时间。
 * 
 * 四、同步器 
 * 	四个类可协助实现常见的专用同步语句 
 * 		Semaphore是一个经典的并发工具。
 * 		CountDownLatch是一个极其简单但又极其常用的实用工具，用于在保持给定数目的信号、事件或条件前阻塞执行。
 * 		CyclicBarrier是一个可重置的多路同步点，在某些并行编程风格中很有用。
 * 		Exchanger允许两个线程在集合点交换对象，它在多流水线设计中是有用的。 
 * 
 * 五、并发Collection
 * 		除队列外，此包还提供了几个设计用于多线程上下文中的Collection实现： 
 * 		ConcurrentHashMap CopyOnWriteArrayList	CopyOnWriteArraySet
 * 此包中与某些类一起使用的“Concurrent”是一种简写，表明与类似的“同步”类有所不同。例如，java.util
 * .Hashtable和Collections.synchronizeMap(new HashMap())是同步的，但ConcurrentHashMap则是并发的
 * 。并发集合是线程安全的，但是不受单个排它锁定的管理。在ConcurrentHashMap这一特定情况下
 * ，它可以安全的允许进行任意数目的并发读取，以及数目可调的并发写入
 * 。需要通过单个锁定阻止对集合的所有访问时，“同步”类是很有用的，其代价是较差的可伸缩性。在期望多个线程访问公共集合的其他情况中
 * ，通常“并发”版本要更好一些。当集合是未共享的，或者仅保持其他锁定时集合是可访问的情况下，非同步集合则要更好一些。
 * 大多数并发Collection实现（包括大多数Queue
 * ）与常规的java.util约定也不同，因为它们的迭代器提供了“弱一致”的，而不是快速失败的遍历。“
 * 弱一致”的迭代器是线程安全的，但是在迭代时没有必要冻结集合，所以它不一定反应自迭代器创建以来的所有更新。
 * 
 * 六、内存一致性属性 Java Language Specification
 *
 *
 *内存操作（如共享变量的读写）的“happen-before”的关系
 * 。只有写入操作happen-before读取操作时，才保证一个线程写入的结果对另一个线程的读取是可视的
 * 。synchronized和volatile构造happen
 * -before的关系，Thread.start()和Thread.join()方法形成happen-before关系。尤其是：
 * 线程中的每个操作happen-before稍后按线程顺序传入的该线程中的每个操作。
 * 一个解除锁监视器的（synchronized阻塞或方法退出）happen
 * -before相同监视器的每个后续锁（synchronized阻塞或方法进入）。并且因为happen
 * -before关系是可传递的，所以解除锁定之前的线程的所有操作happen-before锁定该监视器的任何线程后续的所有操作。
 * 写入volatile字段happen
 * -before每个后续读取相同字段。volatile字段的读取和写入与进入和退出监视器具有相似的内存一致性效果，但不需要互斥锁。 在线程上调用start
 * happen-before已启动的线程中的任何线程。 线程中的所有操作 happen-before从该线程上的join成功返回的任何其他线程。
 * java.util.concurrent中所有类的方法及其子包扩展了这些对更高级别同步的保证。尤其是：
 * 线程中将一个对象放入任何并发collection之前的操作 happen-before 从另一个线程中的collection访问或移除该元素的后续操作。
 * 线程中向Executor提交Runnable之前的操作 happen-before
 * 其执行开始。同样适用于向ExecutorService提交Callables。 异步计算（由Future表示）所采取的操作 happen-before
 * 通过另一线程中 Future.get() 获取结果后续的操作。 “释放”同步存储方法（如 Lock.unlock、Semaphore.release 和
 * CountDownLatch.countDown()）之前的操作 happen-before 另一线程中相同同步存储对象成功“获取”方法（如
 * Lock.lock、 Semaphore.acquire、 Condition.await 和 CountDownLatch.await）的后续操作。
 * 对于通过Exchanger成功交换对象的每个线程对，每个线程中exchange()之前的操作 happen-before 另一个线程中对应
 * exchange() 后续的操作。 调用 CyclicBarrier.await 之前的操作 happen-before
 * 屏障操作所执行的操作，屏障操作所执行的操作 happen-before 从另一个线程中对应 await 成功返回的后续操作。 备注：
 * happen-before（先行发生）规则：
 * Java内存模型中定义的两项操作之间的偏序关系，如果操作A先行发生于操作B，其意思就是说，在发生操作B之前，操作A产生的影响都能被操作B观察到
 * ，“影响”包括修改了内存中共享变量的值
 * 、发送了消息、调用了方法等，它与时间上的先后发生基本没有基本没有太大关系。这个原则非常重要，它是判断数据数据是否存在竞争、线程是否安全的主要依据。
 * 
 * 本文源自Java 2 Platform SE 5.0 API（即JDK 1.5/1.6 API）中，对于java.util.concurrent包的描述
 * 
 * CountDownLatch 门插销计数器 启动线程，然后等待线程结束。即常用的主线程等所有子线程结束后再执行的问题。
 * JDK1.4时，常用办法是给子线程设置状态，主线程循环检测。易用性和效率都不好。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class CountDownLatchMain {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		final int count = 10;
		final CountDownLatch completeLatch = new CountDownLatch(count);// 定义了门插销的数目是10

		for (int i = 0; i < count; i++) {
			Thread thread = new Thread("worker thread" + i) {
				public void run() {
					// do xxxx
					completeLatch.countDown();// 减少一根门插销
				}
			};
			thread.start();
		}
		completeLatch.await();// 如果门插销还没减完则等待。
	}
}
