package com.easyway.java.basic.sockets.vote.binary;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.easyway.java.basic.sockets.vote.Framer;
import com.easyway.java.basic.sockets.vote.LengthFramer;
import com.easyway.java.basic.sockets.vote.VoteMsg;
import com.easyway.java.basic.sockets.vote.VoteMsgCoder;


public class VoteTCPBinaryClient
{
	private static final int CANDIDATE = 888;
	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException,
			IOException
	{
		if (args.length != 2)
		{
			throw new IllegalArgumentException(
					"Parameter(s):<Server> <Port>");
		}

		// SerName or IP Address
		String server = args[0];

		// 获取端口号
		int serverPort = Integer.parseInt(args[1]);

		// 使用指定的服务器和端口创建Socket
		Socket socket = new Socket(server, serverPort);
		System.out.println("Connected to server ..... Sending echo String");

		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		VoteMsgCoder coder = new VoteMsgBinaryCoder();
		Framer framer = new LengthFramer(in);
		
		// 第一个参数为true表示是查询消息，第二个参数为false表示非返回消息
		VoteMsg msg = new VoteMsg(true,false,CANDIDATE,0);
		// 使用文本方式进行编码
		byte[] encodeMsg = coder.toWire(msg);
		
		// 发起查询
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		System.out.println("Sending Inquery("+encodeMsg.length+" bytes)");
		System.out.println(msg);
		framer.frameMsg(encodeMsg, out);
		
		// 发起投票
		msg.setInquery(false);
		encodeMsg = coder.toWire(msg);
		framer.frameMsg(encodeMsg, out);
		
		System.out.println("Sending Inquery("+encodeMsg.length+" bytes)");
		System.out.println(msg);
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		
		System.out.println("");
		
		// 接收查询返回
		encodeMsg = framer.nextMsg();
		msg = coder.fromWire(encodeMsg);
		
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		System.out.println("Received Response("+encodeMsg.length+" bytes)");
		System.out.println(msg);
		
		// 接收投票返回
		msg = coder.fromWire(framer.nextMsg());
		
		System.out.println("Received Response("+encodeMsg.length+" bytes)");
		System.out.println(msg);
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		
		System.out.println("");
		
		/*
		// 第二次发起投票
		msg.setResponse(false);
		encodeMsg = coder.toWire(msg);
		
		System.out.println("Second Sending Inquery("+encodeMsg.length+" bytes)");
		framer.frameMsg(encodeMsg, out);
		
		System.out.println(msg);
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		
		// 第二次接收投票返回
		msg = coder.fromWire(framer.nextMsg());
		
		System.out.println("Second Received Response("+encodeMsg.length+" bytes)");
		System.out.println(msg);
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		*/
		socket.close();
	}
}
