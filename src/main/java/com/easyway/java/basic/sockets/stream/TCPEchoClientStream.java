package com.easyway.java.basic.sockets.stream;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPEchoClientStream
{

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException,
			IOException
	{
		if (args.length < 1 || args.length > 2)
		{
			throw new IllegalArgumentException(
					"Parameter(s):<Server> [<Port>]");
		}

		// SerName or IP Address
		String server = args[0];

		// 获取端口号
		int serverPort = (args.length == 2) ? Integer.parseInt(args[1]) : 7;

		// 使用指定的服务器和端口创建Socket
		Socket socket = new Socket(server, serverPort);
		System.out.println("Connected to server ..... Sending echo String");
		
		System.out.println("客户端正在发送数据.....");
		
		// 组合输出流
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
				socket.getOutputStream()));
		
		System.out.println("Client Send Double Value=3.14");
		// 先发送一个double值
		out.writeDouble(3.14);
		
		System.out.println("Client Send Int Value=50");
		// 再发送一个int值
		out.writeInt(50);
		
		System.out.println("Client Send Long Value=123333333333333333L");
		// 最后发送一个long值
		out.writeLong(123333333333333333L);
		out.flush();
		
		System.out.println("发送数据结束");
		
		System.out.println("客户端正在接收数据.....");
		DataInputStream dis = new DataInputStream(new BufferedInputStream(
				socket.getInputStream()));
		
		System.out.println("Client received Long Value=" + dis.readLong());
		System.out.println("Client received Double Value=" + dis.readDouble());
		System.out.println("Client received Int Value=" + dis.readInt());
		System.out.println("客户端正在接收数据结束");
		socket.close();
	}
}
