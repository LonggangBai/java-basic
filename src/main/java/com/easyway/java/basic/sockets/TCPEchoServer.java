package com.easyway.java.basic.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * <pre>
 *  TCP服务器端
 * TCP服务器执行的步骤
 * 1. 创建一个ServerSocket实例并指定本地端口。此套接字的功能是侦听该指定端口收到的连接。
 * 2. 重复执行：
 * a. 调用ServerSocket的accept()方法以获取下一个客户端连接。基于新建立的客户端连接，创建一个Socket实例，并由accept()方法返回。
 * b. 使用所返回的Socket实例的InputStream和OutputStream与客户端进行通信。
 * c. 通信完成后，使用Socket类的close()方法关闭该客户端套接字连接。
 * 下面是一个用于回显的TCP的服务器端的代码
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class TCPEchoServer {
	private static final int BUFFER_SIZE = 128;

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			throw new IllegalArgumentException("Parameter:<Port>");
		}

		// 获取服务器的端口
		int serverPort = Integer.parseInt(args[0]);

		// 创建用于客户端连接的SocketServer实例
		ServerSocket server = new ServerSocket(serverPort);
		System.out.println("Server has started!!!!");
		int recvMsgSize = 0;
		byte[] recvBuf = new byte[BUFFER_SIZE];

		while (true) {
			System.out.println("Waiting from client connectting!!!");
			// 阻塞等的客户端的连接
			Socket socket = server.accept();

			// 客户端的地址信息
			SocketAddress address = socket.getRemoteSocketAddress();
			System.out.println("Handling client at " + address + "\n");

			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			while ((recvMsgSize = in.read(recvBuf)) != -1) {
				out.write(recvBuf, 0, recvMsgSize);
			}

			socket.close();
		}
	}

}
