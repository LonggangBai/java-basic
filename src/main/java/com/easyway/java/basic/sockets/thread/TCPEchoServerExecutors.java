package com.easyway.java.basic.sockets.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * 
 * <pre>
 * 4.1.5 系统管理调度——Executor接口
 * Executor接口（java.util.concurrent包的一部分）就代表了一个根据某种策略来执行Runnable实例的对象，其中可能包括了排队和调度的细节，
 * 或如何选择要执行的任务。Executor接口只定义了一个方法：
 * [java] view plaincopyprint?
 * interface Executor {  
 * void execute(Runnable task);  
 * }  
 * 
 * Java提供了大量的内置Executor接口实现，它们都可以简单方便地使用，也可以进行扩展性的配置。其中一些还提供了处理维护线程等繁琐细节的功能。例如，
 * 如果一个线程因为未捕获的异常或其他故障停止，它们就自动创建一个新的线程来替换原来的线程。
 * ExecutorService接口继承于Executor接口，并提供了一个更高级的工具来关闭服务器，包括正常的关闭和突然的关闭。ExecutorService还允许在完
 * 成任务后返回一个结果，这需要用到Callable接口，它和Runnable接口很像，只是多了一个返回值。
 * 我们可以通过调用Executors类的各种静态工厂方法来获取ExecutorService实例。示例程序TCPEchoServerExecutor.java演示了基本Executor工具的使用方法。
 * 下面是详细代码
 * </pre>
 * 
 * 采用JDK自带线程池的方式
 * 
 * @author Suifeng
 * 
 */
public class TCPEchoServerExecutors {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			throw new IllegalArgumentException("Parameter:<Port>");
		}

		// 服务器监听端口
		int serverPort = Integer.parseInt(args[0]);

		ServerSocket serverSocket = new ServerSocket(serverPort);

		/*
		 * 在使用一个实现了Runnable接口的实例调用它的execute()方法时， 如果必要它将创建一个新的线程来处理任务。
		 * 然而，它首先会尝试使用已有的线程。 如果一个线程空闲了60秒以上，则将移出线程池。
		 */
		Executor service = Executors.newCachedThreadPool();

		Logger logger = Logger.getLogger("practical");
		logger.info("Server is started!!!!");

		while (true) {
			Socket socket = serverSocket.accept();
			service.execute(new EchoProtocol(socket, logger));
		}

	}

}
