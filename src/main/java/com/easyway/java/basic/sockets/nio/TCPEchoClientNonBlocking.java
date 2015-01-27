package com.easyway.java.basic.sockets.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 
 * <pre>
 * 第五章 NIO
 * 
 * 5.1我们为什么需要NIO
 * 多线程环境下对共享状态进行同步访问，增加了系统调度和切换上下文的开销，程序员对这些开销无法控制。
 * 阻塞等待。
 * 我们需要一种可以一次轮询一组客户端，以查找那个客户端需要服务。在NIO中，一个Channel代表一个可以轮询的I/O目标，
 * Channel能够注册一个Selector实例。Selector的select可以查找“在当前一组信道中，哪一个需要服务”。
 * Buffer提供了比Stream抽象更高效和可预测的I/O。Stream抽象好的方面是隐藏了底层缓冲区的有限性，提供了一个能够容纳
 * 任意长度数据的容器的假象，要么会产生大量的内存开销，要么会引入大量的上下文切换。使用线程的过程中，这些开销都隐藏在具
 * 体实现中，也失去了对其的可控性和可预测性。这种方法使得编写程序变得容易，但调整他们的性能则变得困难。不幸的是，使用
 * Java的Socket抽象，流是唯一的选择。
 * Buffer抽象代表了一个有限容量的数据容器——其本质是一个数组，由指针指示了在哪存放数据和在哪读取数据。使用Buffer有
 * 两个好处，第一、与读写缓冲区数据相关联的系统开销暴露给了程序员，第二、一些对Java对象特殊的Buffer映射能够直接操作
 * 底层的平台的资源，这样操作节省了在不同的地址空间复制数据的开销。
 * 
 * 5.2 与Buffer一起使用Channel
 * Channel使用的不是流而是缓冲区来发送或者读取数据。Buffer类或者其任何子类的实例都可以看做是一个定长的Java基本数
 * 据类型元素序列。与流不同，缓冲区由固定的、有限的容量，并由内部状态记录了由多少数据放入或者取出，就像是有限容量的队列
 * 一样。在Channel中使用的Buffer通常不是构造函数创建的，而是通过调用allocate()方法创建指定容量的Buffer实例：
 * ByteBuffer buffer = ByteBuffer.allocate(CAPACITY)
 * 或者使用包装一个已有数据来实现
 * ByteBuffer buffer = ByteBuffer.wrap(byteArray)
 * NIO的强大来自于channel的非阻塞特性。
 * 下面是一个字符回显的非阻塞客户端
 * </pre>
 * 
 * 非阻塞客户端
 * 
 * @author Suifeng
 * 
 */
public class TCPEchoClientNonBlocking {

	public static void main(String[] args) throws IOException {
		if (args.length < 2 || args.length > 3) {
			throw new IllegalArgumentException(
					"Parameters:<Server> <Word> [<Port>]");
		}

		String server = args[0];

		byte[] msg = args[1].getBytes();

		int serverPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

		SocketChannel channel = SocketChannel.open();

		// 将信道置为非阻塞方式
		channel.configureBlocking(false);

		if (!channel.connect(new InetSocketAddress(server, serverPort))) {
			System.out.print("Trying connected server");
			// 轮询连接的状态，知道建立连接，这样忙等比较浪费资源，
			while (!channel.finishConnect()) {
				System.out.print(".");
			}
		}

		System.out.println("\nClient has connected to server successfully");

		// 写缓冲区
		ByteBuffer writeBuffer = ByteBuffer.wrap(msg);
		// 读缓冲区
		ByteBuffer readBuffer = ByteBuffer.allocate(msg.length);

		int totalBytesReceived = 0;
		int bytesReceived = -1;

		System.out.print("Waiting for server Response");

		while (totalBytesReceived < msg.length) {
			// 向服务器发送数据
			if (writeBuffer.hasRemaining()) {
				channel.write(writeBuffer);
			}

			// 等待服务器返回数据
			if ((bytesReceived = channel.read(readBuffer)) == -1) {
				throw new SocketException("Connection closed prematurely");
			}

			totalBytesReceived += bytesReceived;
			System.out.print(".");
		}

		System.out.println("");

		System.out.println("Received: "
				+ new String(readBuffer.array(), 0, totalBytesReceived));
		channel.close();
	}
}
