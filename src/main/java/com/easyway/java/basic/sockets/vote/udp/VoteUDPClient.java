package com.easyway.java.basic.sockets;

package com.suifeng.tcpip.chapter3.vote;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

/**
 * UDP 客户端
 * 
 * @author Suifeng
 * 
 */
public class VoteUDPClient
{
	// 超时时间
	private static final int TIMEOUT = 3000;
	// 最大连接次数
	private static final int MAXTRIES = 5;

	public static void main(String[] args) throws IOException
	{
		if (args.length != 3)
		{
			throw new IllegalArgumentException("Paramters:<Server> <Port> <Candidate>");
		}

		// 服务器地址
		InetAddress serverAddress = InetAddress.getByName(args[0]);
		// 服务器端口
		int serverPort = Integer.parseInt(args[1]);
		// 候选人编号
		int candidate = Integer.parseInt(args[2]);

		// UDP客户端
		DatagramSocket socket = new DatagramSocket();
		socket.connect(serverAddress, serverPort);
		// 接收数据阻塞时间
		socket.setSoTimeout(TIMEOUT);

		System.out.println("UDP 客户端已建立....");

		VoteMsg vote = new VoteMsg(false, false, candidate, 0);
		VoteMsgCoder coder = new VoteMsgTextCoder();

		byte[] encodeVote = coder.toWire(vote);
		System.out.println("Sending Text-encode Request (" + encodeVote.length + "bytes)");
		System.out.println(vote);

		DatagramPacket message = new DatagramPacket(encodeVote, encodeVote.length);
		socket.send(message);

		message = new DatagramPacket(new byte[VoteMsgTextCoder.MAX_WIRE_LENGTH], VoteMsgTextCoder.MAX_WIRE_LENGTH);
		socket.receive(message);
		
		encodeVote = Arrays.copyOfRange(message.getData(), 0, message.getData().length);
		System.out.println("Received Text-encoded Response ("+encodeVote.length+" bytes)");
		
		vote = coder.fromWire(encodeVote);
		System.out.println(vote);
		
	}

}
