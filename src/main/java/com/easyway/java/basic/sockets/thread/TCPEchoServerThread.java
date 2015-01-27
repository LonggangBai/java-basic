package com.easyway.java.basic.sockets.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * <pre>
 * 一客户一线程
 * 在一客户一线程（thread-per-client）的服务器中，为没一个连接创建一个新的线程来处理。服务器循环执行一些任务，在指定的端口侦听，
 * 反复接收客户端传入的连接请求，并为每个连接创建新的线程来对其进行处理。
 * </pre>
 * 
 * 一客户一线程服务器处理
 * 
 * @author Suifeng
 * 
 */
public class TCPEchoServerThread {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			throw new IllegalArgumentException("Parameter:<Port>");
		}

		// 日志记录器
		Logger logger = Logger.getLogger("practical");

		// 服务器侦听端口
		int serverPort = Integer.parseInt(args[0]);
		ServerSocket serverSocket = new ServerSocket(serverPort);

		logger.info("Server is started!!!!");

		while (true) {
			Socket socket = serverSocket.accept();

			// 接收到客户端的请求后立即创建新的线程进行处理
			Thread t = new Thread(new EchoProtocol(socket, logger));

			t.start();

			logger.info("Created and started Thread " + t.getName());
		}
	}

}
