package com.easyway.java.basic.sockets.vote.multicast;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import com.easyway.java.basic.sockets.vote.VoteMsg;
import com.easyway.java.basic.sockets.vote.VoteMsgCoder;
import com.easyway.java.basic.sockets.vote.text.VoteMsgTextCoder;

/**
 * <pre>
 * 4.3 多接收者

我们的套接字都处理的是两个实体之间的通信，通常是一个服务器和一个客户端。这种一对一的通信方法有时称为单播（unicast）。而对于某些信息，
多个接收者都可能对其感兴趣。对于这种情况，我们可以向每个接收者单播一个数据副本，但是这样做效率可能非常低。由于将同样的数据发送了多次，
在一个网络连接上单播同一数据的多个副本非常浪费带宽。
幸运的是网络提供了一个更有效地使用带宽的方法。我们可以将复制数据包的工作交给网络来做，而不是由发送者负责。
有两种类型的一对多（one-to-many）服务：广播（broadcast）和多播（multicast）。对于广播，（本地）网络中的所有主机都会接收到
一份数据副本。对于多播，消息只是发送给一个多播地址（multicast address），网络只是将数据分发给那些表示想要接收发送到该多播地址
的数据的主机。总的来说，只有UDP套接字允许广播或多播。

4.3.1 广播
广播UDP数据报文与单播数据报文相似，唯一的区别是其使用的是一个广播地址而不是一个常规的（单播）IP地址。注意，IPv6并没有明确地提供
广播地址；然而，有一个特殊的全节点（all - nodes）、本地连接范围（link-local-scope）的多播地址，FFO2::1，发送给该地址的
消息将多播到一个连接上的所有节点。IPv4的本地广播地址（255.255.255.255）将消息发送到在同一广播网络上的每个主机。本地广播信息
决不会被路由器转发。在以太网上的一个主机可以向在同一以太网内的其他主机发送消息，但是该消息不会被路由器转发。IPv4还指定了定向广播
地址，允许向指定网络中的所有主机进行广播。
并不存在可以向网络范围内所有主机发送消息的广播地址。在这种地址发送单个数据报文就可能会由路由器产生非常大量的数据包副本，并可能会耗尽所有网络的带宽。
即使如此，本地广播功能还是非常有用的，它通常用于在网络游戏中处于同一本地（广播）网络的玩家之间交换状态信息。

4.3.2 多播
一个多播地址指示了一组接收者。IP协议的设计者为多播分配了一定范围的地址空间。IPV4的多播地址范围为224.0.0.0到239.255.255.255，
IPV6的多播地址范围为任何由FF开头的地址。
下面是一个通过多播发送消息的例子。
 * </pre>
 * @author Administrator
 *
 */
public class VoteMulticastSender
{

	public static final int CANDIDATE = 888;

	public static void main(String[] args) throws IOException
	{
		if (args.length < 2 || args.length > 3)
		{
			throw new IllegalArgumentException("Parameters:<Multicast Address> <Port> [<TTL>]");
		}

		InetAddress destAddress = InetAddress.getByName(args[0]);

		// 检查是否是多播地址
		if (!destAddress.isMulticastAddress())
		{
			throw new IllegalArgumentException("Not a multicast address");
		}

		
		int destPort = Integer.parseInt(args[1]);
		int TTL = (args.length == 3) ? Integer.parseInt(args[2]) : 1;

		MulticastSocket multicastSocket = new MulticastSocket();
		// 设置报文的声明周期
		multicastSocket.setTTL((byte) TTL);

		VoteMsgCoder coder = new VoteMsgTextCoder();
		VoteMsg vote = new VoteMsg(true, true, CANDIDATE, 100001L);

		// 整理消息
		byte[] msg = coder.toWire(vote);
		DatagramPacket message = new DatagramPacket(msg, msg.length, destAddress, destPort);
		System.out.println("Sending Text-Encode request (" + msg.length + " bytes)");
		System.out.println(vote);
		
		// 发送消息
		multicastSocket.send(message);
		multicastSocket.close();
	}

}
