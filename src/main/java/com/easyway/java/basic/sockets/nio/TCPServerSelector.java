package com.easyway.java.basic.sockets;

package com.suifeng.tcpip.chapter5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * 非阻塞处理服务器端
 * 
 * @author Suifeng
 * 
 */
public class TCPServerSelector
{
	private static final int BUFFER_SIZE = 32;
	private static final int TIMEOUT = 3000;

	public static void main(String[] args) throws IOException
	{
		if (args.length < 1)
		{
			throw new IllegalArgumentException("Parameter(s):<Port> ...");
		}
		
		// 创建选择器实例
		Selector selector = Selector.open();

		// 可以同时监听来自多个信道的数据，使用不同的端口
		for (String arg : args)
		{
			// 创建信道
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			// 侦听指定的端口
			serverChannel.socket().bind(new InetSocketAddress(Integer.parseInt(arg)));
			// 将信道设置为非阻塞方式
			serverChannel.configureBlocking(false);
			
			// 该信道可以进行accept操作
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		}
		
		TCPProtocol protocol = new EchoSelectorProtocol(BUFFER_SIZE);
		
		System.out.println("Server is Running.");
		
		while(true)
		{
			// 阻塞等待直到超时
			if(selector.select(TIMEOUT) == 0)
			{
				System.out.println("Waiting data from client.");
				continue;
			}
			
			// 获取选择器下的键集
			Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
			
			while(keys.hasNext())
			{
				SelectionKey key = keys.next();
				
				if(key.isAcceptable())					// accept操作
				{
					protocol.handleAccept(key);
				}
					
				if(key.isReadable())					// 可读
				{
					protocol.handleRead(key);
				}
				
				if(key.isValid() && key.isWritable())	// 可写
				{
					protocol.handleWrite(key);
				}
				
				 keys.remove();
			}
		}
	}
}
