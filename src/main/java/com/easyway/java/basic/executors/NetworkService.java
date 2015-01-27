package com.easyway.java.basic.executors;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * 
 * 整个ThreadPoolExecutor的任务处理有4步操作：
 *  
 * 第一步，初始的poolSize < corePoolSize，提交的runnable任务，会直接做为new一个Thread的参数，立马执行
 * 第二步，当提交的任务数超过了corePoolSize，就进入了第二步操作。会将当前的runable提交到一个block queue中
 * 第三步，如果block queue是个有界队列，当队列满了之后就进入了第三步。如果poolSize < maximumPoolsize时，会尝试new 一个Thread的进行救急处理，
 * 立马执行对应的runnable任务
 * 第四步，如果第三步救急方案也无法处理了，就会走到第四步执行reject操作。
 * 几点说明：(相信这些网上一搜一大把，我这里简单介绍下，为后面做一下铺垫)
 * block queue有以下几种实现：
 * 1. ArrayBlockingQueue :  有界的数组队列
 * 2. LinkedBlockingQueue : 可支持有界/无界的队列，使用链表实现
 * 3. PriorityBlockingQueue : 优先队列，可以针对任务排序
 * 4. SynchronousQueue : 队列长度为1的队列，和Array有点区别就是：client thread提交到block queue会是一个阻塞过程，直到有一个worker thread
 * 连接上来poll task。
 * RejectExecutionHandler是针对任务无法处理时的一些自保护处理：
 * 1. Reject 直接抛出Reject exception
 * 2. Discard 直接忽略该runnable，不可取
 * 3. DiscardOldest 丢弃最早入队列的的任务
 * 4. CallsRun 直接让原先的client thread做为worker线程，进行执行
 * 
 * 容易被人忽略的点：
 * 1.  pool threads启动后，以后的任务获取都会通过block queue中，获取堆积的runnable task.
 * 
 * 所以建议： block size >= corePoolSize ，不然线程池就没任何意义
 * 2.  corePoolSize 和 maximumPoolSize的区别， 和大家正常理解的数据库连接池不太一样。
 *  据dbcp pool为例，会有minIdle , maxActive配置。minIdle代表是常驻内存中的threads数量，maxActive代表是工作的最大线程数。
 *  这里的corePoolSize就是连接池的maxActive的概念，它没有minIdle的概念(每个线程可以设置keepAliveTime，超过多少时间多有任务后销毁线程，默认只会针
 *  对maximumPoolSize参数的线程生效，可以设置allowCoreThreadTimeOut=true，就可以对corePoolSize进行idle回收)。 
 * 这里的maximumPoolSize，是一种救急措施的第一层。当threadPoolExecutor的工作threads存在满负荷，并且block queue队列也满了，这时代表接近崩溃边缘。
 * 这时允许临时起一批threads，用来处理runnable，处理完后通过keepAliveTime进行调度回收。
 * 
 * 所以建议：  maximumPoolSize >= corePoolSize =期望的最大线程数。 (我曾经配置了corePoolSize=1, maximumPoolSize=20, blockqueue为无界队列，
 * 最后就成了单线程工作的pool。典型的配置错误)
 * 
 * 3. 善用blockqueue和reject组合. 这里要重点推荐下CallsRun的Rejected Handler，从字面意思就是让调用者自己来运行。
 * 我们经常会在线上使用一些线程池做异步处理，比如我前面做的(业务层)异步并行加载技术分析和设计, 将原本串行的请求都变为了并行操作，但过多的并行会增加系统的负载
 * (比如软中断，上下文切换)。所以肯定需要对线程池做一个size限制。但是为了引入异步操作后，避免因在block queue的等待时间过长，所以需要在队列满的时，执行一个
 * callsRun的策略，并行的操作又转为一个串行处理，这样就可以保证尽量少的延迟影响。
 * 
 * 所以建议：  RejectExecutionHandler = CallsRun ,  blockqueue size = 2 * poolSize (为啥是2倍poolSize，主要一个考虑就是瞬间高峰处理，
 * 允许一个thread等待一个runnable任务)
 * 
 *  使用Executors、Executor、ExecutorService、ThreadPoolExecutor
 * 可以使用线程管理任务。还可以使用jdk1.5提供的一组类来更方便的管理任务。从这些类里我们可以体会一种面向任务的思维方式。这些类是：
 * 
 * Executor接口。使用方法：
 * Executor executor = anExecutor;//生成一个Executor实例。
 * executor.execute(new RunnableTask1());
 * 用意：使用者只关注任务执行，不用操心去关注任务的创建、以及执行细节等这些第三方实现者关心的问题。也就是说，把任务的调用执行和任务的实现解耦。
 * 
 * 实际上，JDK1.5中已经有该接口出色的实现。够用了。
 * 
 * Executors是一个如同Collections一样的工厂类或工具类，用来产生各种不同接口的实例。
 * ExecutorService接口它继承自Executor. Executor只管把任务扔进 executor()里去执行，剩余的事就不管了。而ExecutorService则不同，它会多做点控制工作。比如：
 * 
 * ExecutorService(也就是代码里的pool对象）执行shutdown后，它就不能再执行新任务了，但老任务会继续执行完毕，那些等待执行的任务也不再等待了。
 * </pre>
 * 
 * @author Administrator
 * 
 */
class NetworkService {
	private final ServerSocket serverSocket;
	private final ExecutorService pool;

	public NetworkService(int port, int poolSize) throws IOException {
		serverSocket = new ServerSocket(port);
		pool = Executors.newFixedThreadPool(poolSize);
	}

	public void serve() {
		try {
			for (;;) {
				pool.execute(new Handler(serverSocket.accept()));
			}
		} catch (IOException ex) {
			pool.shutdown(); // 不再执行新任务
		}
	}
}

class Handler implements Runnable {
	private final Socket socket;

	Handler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// read and service request
	}
}