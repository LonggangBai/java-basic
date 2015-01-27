package com.easyway.java.basic.sockets.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 3.2 组合输入输出流 Java中与流相关的类可以组合起来从而提供强大的功能。
 * 我们可以将一个Socket实例的OutputStream包装在一个BufferedOutputStream实例中
 * ，这样可以先将字节暂时缓存在一起，然后再一次全部发送到底层的通信信道中
 * ，以提高程序的性能。我们还能再将这个BufferedOutputStream实例包裹在一个DataOutputStream实例中
 * ，以实现发送基本数据类型的功能。以下是实现这种组合的代码： [java] view plaincopyprint? Socket socket = new
 * Socket(server, port); DataOutputStream out = new DataOutputStream( new
 * BufferedOutputStream(socket.getOutputStream()));
 * 
 * @author Administrator
 * 
 */
public class TCPEchoServerStream {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			throw new IllegalArgumentException("Parameter:<Port>");
		}

		// 获取服务器的端口
		int serverPort = Integer.parseInt(args[0]);

		// 创建用于客户端连接的SocketServer实例
		ServerSocket server = new ServerSocket(serverPort);
		System.out.println("Server has started!!!!\nWaiting data from client.");

		while (true) {
			System.out.println("正在等待客户端发送数据");
			Socket socket = server.accept();

			SocketAddress address = socket.getRemoteSocketAddress();
			System.out.println("Handling client at " + address);

			// 组合输入流
			DataInputStream dis = new DataInputStream(new BufferedInputStream(
					socket.getInputStream()));

			// 读取一个double值
			double value = dis.readDouble();
			System.out.println("Server received Double Value=" + value);

			// 读取一个Int值
			int value2 = dis.readInt();
			System.out.println("Server received Int Value=" + value2);

			// 读取一个Long值
			long value3 = dis.readLong();
			System.out.println("Server received Long Value=" + value3);

			System.out.println("服务器端正在发送数据.....");
			DataOutputStream out = new DataOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
			out.writeLong(value3);
			out.writeDouble(value);
			out.writeInt(value2);
			System.out.println("服务器端正在发送数据结束");
			out.flush();

			socket.close();
		}
	}

}
