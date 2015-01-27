package com.easyway.java.basic.executors;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
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