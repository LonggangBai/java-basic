package com.easyway.java.basic.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * <pre>
 * 4.2 阻塞和超时
 * 
 * Socket的I/O调用可能会因为多种原因而阻塞。数据输入方法read()和receive()在没有数据可读时会阻塞。TCP套接字的write()方法在没有足够的空间缓存传输的数据时可能阻塞。 
 * 
 * 4.2.1 accept(),read()和receive()
 * 对于这些方法，我们可以使用Socket类、ServerSocket类和DatagramSocket类的setSoTimeout()方法，设置其阻塞的最长时间（以毫秒为单位）。
 * 如果在指定时间内这些方法没有返回，则将抛出一个InterruptedIOException异常。对于Socket实例，在调用read()方法前，我们还可以使用该套接字
 * 的InputStream的available()方法来检测是否有可读的数据。
 * 
 * 4.2.2 连接和写数据
 * Socket类的构造函数会尝试根据参数中指定的主机和端口来建立连接，并阻塞等待，直到连接成功建立或发生了系统定义的超时。不幸的是，系统定义的超时时间
 * 很长，而Java又没有提供任何缩短它的方法。要改变这种情况，可以使用Socket类的无参数构造函数，它返回的是一个没有建立连接的Socket实例。需要建立
 * 连接时，调用该实例的connect()方法，并指定一个远程终端和超时时间（毫秒）。
 * write()方法调用也会阻塞等待，直到最后一个字节成功写入到了TCP实现的本地缓存中。如果可用的缓存空间比要写入的数据小，在write()方法调用返回前，
 * 必须把一些数据成功传输到连接的另一端。因此，write()方法的阻塞总时间最终还是取决于接收端的应用程序。不幸的是Java现在还没有提供任何使write()
 * 超时或由其他线程将其打断的方法。所以如果一个可以在Socket实例上发送大量数据的协议可能会无限期地阻塞下去。
 * 
 * 4.2.3 限制每个客户端的时间
 * 使用如下协议可以在代码级别对服务时间进行限制
 * </pre>
 * 
 * 独立于客户服务器的协议
 * 
 * @author Suifeng
 * 
 */
public class TimeLimitEchoProtocol implements Runnable {
	private static final int TIME_LIMIT = 5000;
	private static final int BUFFER_SIZE = 32;

	private Socket socket;
	private Logger logger;

	public TimeLimitEchoProtocol(Socket socket, Logger logger) {
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

			long endTime = System.currentTimeMillis() + TIME_LIMIT;
			int timeBounds = TIME_LIMIT;

			byte[] buffer = new byte[BUFFER_SIZE];

			// 设置读取的超时时间
			socket.setSoTimeout(TIME_LIMIT);

			// 限制服务时间，避免超时
			while (timeBounds > 0 && (recvSize = in.read(buffer)) != -1) {
				out.write(buffer, 0, recvSize);
				totalBytes += recvSize;

				timeBounds = (int) (endTime - System.currentTimeMillis());

				// 重新设置超时时间
				socket.setSoTimeout(timeBounds);
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
