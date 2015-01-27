package com.easyway.java.basic.sockets.vote.multicast;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

import com.easyway.java.basic.sockets.vote.VoteMsg;
import com.easyway.java.basic.sockets.vote.VoteMsgCoder;
import com.easyway.java.basic.sockets.vote.text.VoteMsgTextCoder;

/**
 * 多播发送和单播发送的区别，1、对给定地址是否是多播地址进行验证；2、多播报文设置了初始的TTL（Time To Live，生命周期）。
网络多播只将消息发送给指定的一组接收者，这组接收者叫做多播组。
 * @author Administrator
 *
 */
public class VoteMulticastReceiver
{

	public static void main(String[] args) throws IOException
	{
		if (args.length != 2)
		{
			throw new IllegalArgumentException("Parameters:<Muticast Addr> <Port>");
		}

		InetAddress address = InetAddress.getByName(args[0]);
		
		// 检查是否是多播地址
		if (!address.isMulticastAddress())
		{
			throw new IllegalArgumentException("Not a multicast address	");
		}

		int port = Integer.parseInt(args[1]);

		MulticastSocket multicastSocket = new MulticastSocket(port);
		// 设置要获取消息的多播地址
		multicastSocket.joinGroup(address);

		VoteMsgCoder coder = new VoteMsgTextCoder();

		System.out.println("Receiver is OK!!!Waiting for data from sender");

		DatagramPacket message = new DatagramPacket(new byte[VoteMsgTextCoder.MAX_WIRE_LENGTH],
				VoteMsgTextCoder.MAX_WIRE_LENGTH);
		// 接收消息
		multicastSocket.receive(message);

		VoteMsg vote = coder.fromWire(Arrays.copyOfRange(message.getData(), 0, message.getLength()));

		System.out.println("Received Text-encoded Request (" + message.getLength() + " bytes)");
		System.out.println(vote);

		multicastSocket.close();
	}

}
