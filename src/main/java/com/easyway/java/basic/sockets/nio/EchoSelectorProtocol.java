package com.easyway.java.basic.sockets.nio;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 回显协议的实现
 * 使用选择器和信道实现的回显协议
 * @author Administrator
 *
 */
public class EchoSelectorProtocol implements TCPProtocol
{
	private int bufferSize;

	public EchoSelectorProtocol(int bufferSize) {
		super();
		this.bufferSize = bufferSize;
	}

	@Override
	public void handleAccept(SelectionKey key) throws IOException
	{
		System.out.println("Handle Accepting Now...");
		SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
		// 设置为阻塞方式
		channel.configureBlocking(false);
		// 信道可读
		channel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
	}

	@Override
	public void handleRead(SelectionKey key) throws IOException
	{
		System.out.println("Handle Reading Now...");
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buf = (ByteBuffer) key.attachment();

		long bytesRead = channel.read(buf);
			
		System.out.println("Receiving from client:" + channel.socket().getRemoteSocketAddress()+"\nReceived:"+new String(buf.array()));

		if (bytesRead == -1)
		{
			channel.close();
		}
		else if(bytesRead > 0)
		{
			// 信道可读、可写
			key.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
		}
	}

	@Override
	public void handleWrite(SelectionKey key) throws IOException
	{
		System.out.println("Handling Writing Now....");
		ByteBuffer buf = (ByteBuffer) key.attachment();
		buf.flip();
		SocketChannel channel = (SocketChannel) key.channel();
		
		// 向客户端写入数据
		channel.write(buf);

		if (!buf.hasRemaining())
		{
			// 信道可读
			key.interestOps(SelectionKey.OP_READ);
		}

		buf.compact();
	}

}
