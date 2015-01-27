package com.easyway.java.basic.sockets;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

/**
 * UDP服务器端
 * 
 * @author Suifeng
 * 
 */
public class VoteUDPServer
{

	// 最大显示数组字节数
	private static final int ECHO_MAX = 255;

	public static void main(String[] args) throws IOException
	{
		if (args.length != 1)
		{
			throw new IllegalArgumentException("Parameter:<Port>");
		}

		// 服务器端口
		int serverPort = Integer.parseInt(args[0]);
		
		// 服务器端
		DatagramSocket socket = new DatagramSocket(serverPort);
		
		
		System.out.println("UDP服务器已启动....");
		
		VoteMsgCoder coder = new VoteMsgTextCoder();
		VoteService service = new VoteService();
		byte[] inBuffer =new byte[VoteMsgTextCoder.MAX_WIRE_LENGTH];
		
		while (true)
		{
			System.out.println("正在等待客户端发送数据....");
			// 数据报
			DatagramPacket packet = new DatagramPacket(inBuffer, inBuffer.length);
			// 接收客户端发送的数据(阻塞)
			socket.receive(packet);

			byte[] encodeMsg = Arrays.copyOfRange(packet.getData(), 0, packet.getData().length);
			System.out.println("Handing request from "+packet.getSocketAddress()+ " ("+encodeMsg.length+")");
			
			VoteMsg msg = coder.fromWire(encodeMsg);
			msg = service.handleRequest(msg);
			
			packet.setData(coder.toWire(msg));
			System.out.println("Sending Resoponse ("+packet.getLength()+" bytes");
			System.out.println(msg);
			
			socket.send(packet);
		}
	}

}
