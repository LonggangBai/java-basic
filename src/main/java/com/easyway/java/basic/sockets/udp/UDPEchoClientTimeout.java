package com.easyway.java.basic.sockets.udp;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP 客户端
 * 
 * <pre>
 * 2.3 UDP套接字
 * UDP协议提供了一种不同于TCP协议的端对端服务，实际上UDP只实现了两个功能：
 * 1)、 在IP协议的基础上添加了另一层地址（端口）
 * 2)、对数据传输过程中可能产生的错误进行了检测，并抛弃了已损坏的数据。
 * UDP套接字与TCP套接字的不同点：
 * 1. UDP协议在使用前不需要进行连接。
 * 2. UDP协议保存边界信息。
 * 2.3.1 UDP客户端
 * UDP客户端首先向被动等待联系的服务器发送一个数据报文。一个UDP报文要执行以下三步。
 * 1. 创建一个DatagramSocket实例，可以选择对本地地址和端口号进行设置。
 * 2. 使用DatagramSocket类的send() 和 receive()方法来发送和接收DatagramPacket实例，进行通信。
 * 3. 通信完成后，使用DatagramSocket类的close()方法来销毁该套接字。
 * 
 * DatagramSocket创建的时候不需要指定目的地址。因为UDP通信前不需要进行连接。每个数据报可以发送到或者接收于不同的目的地址。
 * UDP协议的一个后果是会出现数据报文丢失。为了避免这个问题，可以设置最大阻塞时间。
 * </pre>
 * 
 * @author Suifeng
 * 
 */
public class UDPEchoClientTimeout {
	// 超时时间
	private static final int TIMEOUT = 3000;
	// 最大连接次数
	private static final int MAXTRIES = 5;

	public static void main(String[] args) throws IOException {
		if (args.length < 2 || args.length > 3) {
			throw new IllegalArgumentException(
					"Paramters:<Server> <Word> [<Port>]");
		}

		// 服务器地址
		InetAddress serverAddress = InetAddress.getByName(args[0]);

		// 要发送的数据
		byte[] byteToSend = args[1].getBytes();

		// 服务器端口
		int serverPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

		// UDP客户端
		DatagramSocket socket = new DatagramSocket();
		// 接收数据阻塞时间
		socket.setSoTimeout(TIMEOUT);

		System.out.println("UDP 客户端已建立....");

		// 发送数据报包
		DatagramPacket sendPacket = new DatagramPacket(byteToSend,
				byteToSend.length, serverAddress, serverPort);

		// 接收的数据报包
		DatagramPacket receiverPacket = new DatagramPacket(
				new byte[byteToSend.length], byteToSend.length);

		// 尝试接收次数
		int tries = 0;
		boolean receivedResponse = false;

		do {
			System.out.println("向服务区端发送数据....");
			// 向服务器端发送数据
			socket.send(sendPacket);

			try {
				System.out.println("接收从服务器端返回的数据...");
				// 从服务器端接收数据
				socket.receive(receiverPacket);

				if (!receiverPacket.getAddress().equals(serverAddress)) {
					throw new IOException(
							"Received packet from an unknown source");
				}

				receivedResponse = true;
			} catch (InterruptedIOException e) {
				tries++;
				System.out.println("Timed out," + (MAXTRIES - tries)
						+ " more ties");
			}

		} while ((!receivedResponse) && (tries < MAXTRIES));

		if (receivedResponse) {
			System.out.println("Received:"
					+ new String(receiverPacket.getData()));
		} else {
			System.out.println("No response--giving up!");
		}
	}

}
