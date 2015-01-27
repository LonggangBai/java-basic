package com.easyway.java.basic.sockets.compress;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;

/**
 * <pre>
 * 4.4 控制默认的行为
 * 
 * 4.4.1 Keep-alive
 * 如果一段时间内没有数据交换，通信的每个终端可能都会怀疑对方是否还处于活跃状态。TCP协议提供了一种keep-alive的机制，该机制在经过一段不活动时间后，
 * 将向另一个终端发送一个探测消息。如果另一个终端还出于活跃状态，它将回复一个确认消息。如果经过几次尝试后依然没有收到另一终端的确认消息，则终止发送探
 * 测信息，关闭套接字，并在下一次尝试I/O操作时抛出一个异常。注意，应用程序只要在探测信息失败时才能察觉到keep-alive机制的工作。
 * 
 * 4.4.2 发送和接收缓冲区的大小
 * Socket, DatagramSocket: 设置和获取发送接收缓存区大小
 * int getReceiveBufferSize()
 * void setReceiveBufferSize(int size)
 * int getSendBufferSize()
 * void setSendBufferSize(int size)
 * ServerSocket上指定接收缓冲区大小。不过，这实际上是为accept()方法所创建的新Socket实例设置接收缓冲区大小。为什么可以只设置接收缓冲区大小而不
 * 设置发送缓冲区的大小呢？当接收了一个新的Socket，它就可以立刻开始接收数据，因此需要在accept()方法完成连接之前设置好缓冲区的大小。另一方面，由于
 * 可以控制什么时候在新接受的套接字上发送数据，因此在发送之前还有时间设置发送缓冲区的大小。
 * ServerSocket: 设置/获取所接受套接字的接收缓冲区大小
 * getReceiveBufferSize()和setReceiveBufferSize()方法用于获取和设置由accept()方法创建的Socket实例的接收缓冲区的大小（字节）。
 * 
 * 4.4.3 超时
 * Socket, ServerSocket, DatagramSocket: 设置/获取I/O超时时间
 * getSoTimeout()和setSoTimeout()方法分别用于获取和设置读/接收数据操作以及accept操作的最长阻塞时间。超时设置为0表示该操作永不超时。如果
 * 阻塞超过了超时时长，则抛出一个异常。
 * 
 * 4.4.4 地址重用
 * 在某些情况下，可能希望能够将多个套接字绑定到同一个套接字地址。对于UDP多播的情况，在同一个主机上可能有多个应用程序加入了相同的多播组。对于TCP，
 * 当一个连接关闭后，通信的一端（或两端）必须在"Time-Wait"状态上等待一段时间，以对传输途中丢失的数据包进行清理。不幸的是，通信终端可能无法等到
 * Time-Wait结束。对于这两种情况，都需要能够与正在使用的地址进行绑定的能力，这就要求实现地址重用。
 * Socket, ServerSocket, DatagramSocket: 设置/获取地址重用
 * getReuseAddress()和setReuseAddress()方法用于获取和设置地址重用许可。设置为true表示启用了地址重用功能。
 * 
 * 4.4.5 消除缓冲延迟
 * TCP协议将数据缓存起来直到足够多时一次发送，以避免发送过小的数据包而浪费网络资源。虽然这个功能有利于网络，但应用程序可能对所造成的缓冲延迟
 * 不能容忍。好在可以人为禁用缓存功能。
 * getTcpNoDelay()和setTcpNoDelay()方法用于获取和设置是否消除缓冲延迟。将值设置为true表示禁用缓冲延迟功能。
 * 
 * 4.4.6 紧急数据
 * Java中的紧急数据几乎没什么用，因为紧急字节与常规字节按照传输的顺序混在了一起。实际上，Java接收者并不能区分其是否在接收紧急数据。
 * 
 * 4.4.7 关闭后停留
 * 当调用套接字的close()方法后，即使套接字的缓冲区中还有没有发送的数据，它也将立即返回。这样不发送完所有数据可能导致的问题是主机将在后面的某个
 * 时刻发生故障。其实可以选择让close()方法"停留"或阻塞一段时间，直到所有数据都已经发送并确认，或发生了超时。
 * Socket: 在close()方法停留
 * 如果调用setSoLinger()并将其设置为true，那么后面再调用的close()方法将阻塞等待，直到远程终端对所有数据都返回了确认信息，或者发生了指定的
 * 超时（秒）。如果发生了超时，TCP连接将强行关闭。如果开启了停留功能，getSoLinger()方法将返回指定的超时时间，否则返回-1。
 * 
 * 4.4.8 广播许可
 * 一些操作系统要求显示地对广播许可进行请求。你可以对广播许可进行控制。正如前面所介绍的，DatagramSockets类提供了广播服务功能。
 * DatagramSocket: 设置/获取广播许可
 * getBroadcast()和setBroadcast()方法分别用于获取和设置广播许可。设置为true表示允许广播。在Java中，默认情况下是允许进行广播的。
 * 
 * 4.4.9 通信等级
 * 有的网络对满足服务条件的数据包提供了增强的服务或"额外的保险"。一个数据包的通信等级（traffic class）由数据包在网络中传输时其内部的一个值来指定。
 * Socket, DatagramSocket: 设置/获取通信等级
 * int getTrafficClass()
 * void setTrafficClass(int tc)
 * 通信等级通常由一个整数或一组位标记指定。位标记的数量和意义则取决于所使用的IP协议版本。
 * 
 * 4.4.10 基于性能的协议选择
 * TCP协议并不是套接字惟一可选的协议。使用什么样的协议取决于应用程序的侧重点在是什么。 Java允许开发者根据不同性能特征对于应用程序的重要程度，
 * 为具体实现给出"建议"。底层的网络系统可能会根据这些建议，在一组能够提供同等的数据流服务，同时又具有不同的性能特征的不同协议中做出选择。
 * Socket, ServerSocket: 指定协议参数选择
 * void setPerformancePreferences(int 
 * connectionTime, int latency, int bandwidth)
 * 套接字的性能参数由三个整数表示，分别代表连接时间，延迟和带宽。具体的数值并不重要，Java将比较各种标准的相关参数值，并返回与之最匹配的
 * 可用协议。例如，如果connectionTime和latency都等于0，bandwidth等于1，那么则将选择能够使带宽最大的协议。注意，要使这个方法生效，
 * 必须在套接字建立连接之前调用。
 * 
 * 4.5 关闭连接
 * Socket类的shutdownInput()和shutdownOutput()方法能够将输入输出流相互独立地关闭。调用shutdownInput()后，套接字的输入
 * 流将无法使用。任何没有发送的数据都将毫无提示地被丢弃，任何想从套接字的输入流读取数据的操作都将返回-1。当Socket调用shutdownOutput() 
 * 方法后，套接字的输出流将无法再发送数据，任何尝试向输出流写数据的操作都将抛出一个IOException异常。在调用shutdownOutput()之前写出
 * 的数据可能能够被远程套接字读取，之后，在远程套接字输入流上的读操作将返回-1。应用程序调用shutdownOutput()后还能继续从套接字读取数据，
 * 类似的，在调用shutdownInput()后也能够继续写数据。
 * 下面考虑另一种协议。假设你需要一个压缩服务器，将接收到的字节流压缩后，发回给客户端。这种情况下应该由哪一端来关闭连接呢？由于从客户端发来的
 * 字节流的长度是任意的，客户端需要关闭连接以通知服务器要压缩的字节流已经发送完毕。那么客户端应该什么时候调用close()方法呢？如果客户端在其
 * 发送完最后一个字节后立即调用套接字的close()，它将无法接收到压缩后数据的最后一些字节。或许客户端可以像回显协议那样，在接收完所有压缩后的
 * 数据才关闭连接。但不幸的是，这样一来服务器和客户端都不知道到底有多少数据要接收，因此这也不可行.
 * 客户端向服务器发送待压缩的字节，发送完成后调用shutdownOutput()关闭输出流，并从服务器读取压缩后的字节流。服务器反复地获取未压缩的数据，
 * 并将压缩后的数据发回给客户端，直到客户端执行了停机操作，导致服务器的read操作返回-1，这表示数据流的结束。然后服务器关闭连接并退出。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class CompressProtocol implements Runnable {
	private static final int BUFFER_SIZE = 1024;

	private Logger logger;
	private Socket socket;

	public CompressProtocol(Logger logger, Socket socket) {
		super();
		this.logger = logger;
		this.socket = socket;
	}

	public void handleCompress(Socket socket, Logger logger) {
		try {
			InputStream in = socket.getInputStream();
			GZIPOutputStream out = new GZIPOutputStream(
					socket.getOutputStream());

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}

			out.finish();

			logger.info("Client " + socket.getRemoteSocketAddress()
					+ " finished");
		} catch (IOException e) {
			logger.warning("Excetion in compress protocol");
		}

		try {
			socket.close();
		} catch (IOException e) {
			logger.info("close socket ERROR:" + e.getMessage());
		}
	}

	@Override
	public void run() {
		handleCompress(socket, logger);

	}

}
