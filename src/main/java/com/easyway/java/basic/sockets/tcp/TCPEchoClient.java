package com.easyway.java.basic.sockets.tcp;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
/**
 * 
 * TCP客户端的步骤
1.创建一个Socket实例：构造器向指定的远程主机和端口建立一个TCP连接。
2. 通过套接字的输入输出流（I/O streams）进行通信：一个Socket连接实例包括一个InputStream和一个OutputStream，它们的用法同于其他Java输入输出流。
3. 使用Socket类的close()方法关闭连接。

下面是一个用于回显的TCP客户端的代码
 * TCP客户端
 * @author Suifeng
 *
 */
public class TCPEchoClient
{

	public static void main(String[] args) throws UnknownHostException,
			IOException
	{
		if (args.length < 2 || args.length > 3)
		{
			throw new IllegalArgumentException(
					"Parameter(s):<Server> <Word> [<Port>]");
		}

		// SerName or IP Address
		String server = args[0];

		// 0.1 获取传输文字的长度
		byte[] data = args[1].getBytes();

		// 0.2 获取端口号（严格的话要对这里进行一下端口的验证）
		int serverPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

		// 1.使用指定的服务器和端口创建Socket
		Socket socket = new Socket(server, serverPort);
		System.out.println("Connected to server ..... Sending echo String");
		
		// 2.输入/输出
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		// Sending encode string to server
		out.write(data);

		int totalBytesLength = 0;
		int bytesRcvd;

		while (totalBytesLength < data.length)
		{
			// 阻塞等待服务器端的返回
			if ((bytesRcvd = in.read(data, totalBytesLength, data.length
					- totalBytesLength)) == -1)
			{
				throw new SocketException("Connection closed prematurely");
			}
			
			totalBytesLength += bytesRcvd;
		}
		
		System.out.println("Received:"+new String(data));
		
		// 3.关闭
		socket.close();
	}
}
