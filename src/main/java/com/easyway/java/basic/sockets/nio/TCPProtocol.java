package com.easyway.java.basic.sockets.nio;


import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * 3 Selector
一个Selector实例可以检查一组信道的I/O状态。
下面使用信道和选择器实现一个回显服务器，并且不适用多线程和忙等。
协议接口
 * 回显服务器协议接口
 * @author Suifeng
 *
 */
public interface TCPProtocol
{
	/**
	 * 接收请求
	 * @param key
	 * @throws IOException
	 */
	void handleAccept(SelectionKey key) throws IOException;
	
	/**
	 * 读取数据
	 * @param key
	 * @throws IOException
	 */
	void handleRead(SelectionKey key) throws IOException;
	
	/**
	 * 接收数据
	 * @param key
	 * @throws IOException
	 */
	void handleWrite(SelectionKey key) throws IOException;
}
