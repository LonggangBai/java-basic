package com.easyway.java.basic.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * <pre>
 * 第四章 进阶
 * 4.1 多任务处理
 * 迭代服务器：当一个客户端向一个已经被其他客户端占用的服务器发送连接请求时，虽然其在连接建立后即可向服务器端发送数据，
 * 服务器端在处理完已有客户端的请求前，却不会对新的客户端作出响应。
 * 并行服务器：可以单独处理没一个连接，且不会产生干扰。并行服务器分为两种：一客户一线程和线程池。
 * 4.1.1 Java多线程
 * Java提供了两种在一个新线程中执行任务的方法：1）为Thread类定义一个带有run()方法的子类，在run()方法中包含要执行
 * 的任务，并实例化这个子类；或2）定义一个实现了Runnable接口的类，并在run()方法中包含要执行的任务，再将这个类的一个
 * 实例传递给Thread的构造函数。
 * 4.1.2 服务器协议
 * 要求：多任务服务器方法与客户端--服务器协议独立。
 * 下面是一个显示回显协议的代码。
 * </pre>
 * 
 * 独立于客户服务器的协议
 * 
 * @author Suifeng
 * 
 */
public class EchoProtocol implements Runnable {
	private static final int BUFFER_SIZE = 32;

	private Socket socket;
	private Logger logger;

	public EchoProtocol(Socket socket, Logger logger) {
		super();
		this.socket = socket;
		this.logger = logger;
	}

	public static void handleEchoClient(Socket socket, Logger logger) {
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			int totalBytes = 0;
			int recvSize = -1;

			byte[] buffer = new byte[BUFFER_SIZE];

			// 循环执行知道连接关闭
			while ((recvSize = in.read(buffer)) != -1) {
				// 接收到数据后立即返回
				out.write(buffer, 0, recvSize);
				totalBytes += recvSize;
			}

			logger.info(Thread.currentThread().getName()
					+ " is handling Echo now");
			logger.info("Client " + socket.getRemoteSocketAddress()
					+ " echoed " + totalBytes + " bytes");
		} catch (IOException e) {
			logger.warning("Exception in echo protocol:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		handleEchoClient(socket, logger);
	}

}
