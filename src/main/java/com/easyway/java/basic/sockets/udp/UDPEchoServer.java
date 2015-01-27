package com.easyway.java.basic.sockets.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 
 * <pre>
 *  UDP服务器端
 * 与TCP服务器一样，UDP服务器是建一个几个通信终端，并被动等待客户端发起连接。
 * 典型的UDP服务器执行需要以下三步。
 * 1. 创建一个DatagramSocket实例，指定本地端口号，并可以选择指定本地地址。此时，服务器已经准备好从任何客户端接收数据报文。
 * 2. 使用DatagramSocket类的receive()方法来接收一个DatagramPacket实例。当receive()方法返回时，数据报文就包含了客户端的地址，
 * 这样我们就知道了回复信息应该发送到什么地方。
 * 3. 使用DatagramSocket类的send() 和receive()方法来发送和接收DatagramPackets实例，进行通信。
 * </pre>
 * 
 * UDP服务器端
 * 
 * @author Suifeng
 * 
 */
public class UDPEchoServer {

	// 最大显示数组字节数
	private static final int ECHO_MAX = 255;

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			throw new IllegalArgumentException("Parameter:<Port>");
		}

		// 服务器端口
		int serverPort = Integer.parseInt(args[0]);

		// 服务器端
		DatagramSocket socket = new DatagramSocket(serverPort);

		// 数据报
		DatagramPacket packet = new DatagramPacket(new byte[ECHO_MAX], ECHO_MAX);
		System.out.println("UDP服务器已启动....");
		while (true) {
			System.out.println("正在等待客户端发送数据....");
			// 接收客户端发送的数据(阻塞)
			socket.receive(packet);

			System.out.println("Handing client at "
					+ packet.getAddress().getHostAddress() + " on port "
					+ packet.getPort());
			System.out.println("Received Data:" + new String(packet.getData()));

			// 向客户端发送数据(这里只是将数据包进行转发--透传，没有进行任何处理)
			socket.send(packet);
			// 重置缓存区大小
			packet.setLength(ECHO_MAX);
		}
	}

}