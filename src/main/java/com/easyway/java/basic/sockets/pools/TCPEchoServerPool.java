package com.easyway.java.basic.sockets.pools;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import com.easyway.java.basic.sockets.thread.EchoProtocol;

/**
 * 
 * <pre>
 * 4.1.4 线程池
 * 每个新线程都会消耗系统资源：创建一个线程将占用CPU周期，而且每个线程都自己的数据结构（如，栈）也要消耗系统内存。
 * 另外，当一个线程阻塞（block）时，JVM将保存其状态，选择另外一个线程运行，并在上下文转换（context switch）
 * 时恢复阻塞线程的状态。随着线程数的增加，线程将消耗越来越多的系统资源。这将最终导致系统花费更多的时间来处理上下
 * 文转换和线程管理，更少的时间来对连接进行服务。那种情况下，加入一个额外的线程实际上可能增加客户端总服务时间。
 * 我们可以通过限制总线程数并重复使用线程来避免这个问题。与为每个连接创建一个新的线程不同，服务器在启动时创建一个
 * 由固定数量线程组成的线程池（thread pool）。当一个新的客户端连接请求传入服务器，它将交给线程池中的一个线程
 * 处理。当该线程处理完这个客户端后，又返回线程池，并为下一次请求处理做好准备。如果连接请求到达服务器时，线程池
 * 中的所有线程都已经被占用，它们则在一个队列中等待，直到有空闲的线程可用。
 * </pre>
 * 
 * 采用自定义线程池的方式实现多任务处理
 * 
 * @author Suifeng
 * 
 */
public class TCPEchoServerPool {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			throw new IllegalArgumentException("Parameter:<Port> <PoolSize>");
		}

		// 服务器侦听的端口号
		int serverPort = Integer.parseInt(args[0]);

		// 自定义线程池的大小
		int poolSize = Integer.parseInt(args[1]);

		// 日志记录器
		final Logger logger = Logger.getLogger("practical");
		final ServerSocket serverSocket = new ServerSocket(serverPort);

		logger.info("Server is started!!!!");

		/*
		 * 每个线程都反复循环，从（共享的）ServerSocket实例接收客户端连接。
		 * 当多个线程同时调用同一个ServerSocket实例的accept()方法时，
		 * 它们都将阻塞等待，直到一个新的连接成功建立。然后系统选择一个线程， 新建立的连接对应的Socket实例则只在选中的线程中返回。
		 * 其他线程则继续阻塞，直到成功建立下一个连接和选中另一个幸运的线程。
		 */

		for (int i = 0; i < poolSize; i++) {
			Thread t = new Thread() {
				@Override
				public void run() {
					while (true) {
						try {
							// 各个线程阻塞等待，知道有一个客户端连过来，当线程进行处理，其他的线程继续处理
							Socket socket = serverSocket.accept();
							EchoProtocol.handleEchoClient(socket, logger);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			};

			t.start();

			logger.info("Thread " + t.getName() + " has started!!!");
		}
	}

}
