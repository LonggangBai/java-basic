package com.easyway.java.basic.sockets;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class VoteTCPBinaryServer
{
	public static void main(String[] args) throws IOException
	{
		if(args.length != 1)
		{
			throw new IllegalArgumentException("Parameter:<Port>");
		}
		
		// 获取服务器的端口
		int serverPort = Integer.parseInt(args[0]);
		
		// 创建用于客户端连接的SocketServer实例
		ServerSocket server = new ServerSocket(serverPort);
		System.out.println("Server has started!!!!");

		VoteMsgCoder coder = new VoteMsgBinaryCoder();
		VoteService service = new VoteService();
		
		while(true)
		{
			Socket socket = server.accept();
			System.out.println("Handling client at "+socket.getRemoteSocketAddress());
			
			Framer framer = new LengthFramer(socket.getInputStream());
			
			byte[] req;
			while((req = framer.nextMsg()) != null)
			{
				System.out.println("++++++++++++++++++++++++++++++++");
				System.out.println("Received message("+req.length+")byte.");
				
				// 先解码投票信息，再进行处理
				VoteMsg resMsg = service.handleRequest(coder.fromWire(req));
				
				// 对投票信息编码后返回
				framer.frameMsg(coder.toWire(resMsg), socket.getOutputStream());
				System.out.println("Resonpnse message:"+resMsg);
				System.out.println("++++++++++++++++++++++++++++++++");
				System.out.println("");
			}
			
			socket.close();
		}
	}

}
