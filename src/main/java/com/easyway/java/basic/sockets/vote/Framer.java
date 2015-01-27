package com.easyway.java.basic.sockets.vote;

import java.io.IOException;
import java.io.OutputStream;

/**
 * <pre>
 * 3.3 成帧与解析
 * 成帧(framing)技术解决了接收端如何定位消息的首尾位置的问题。
 * 主要有两种技术能够查找到消息的结束位置。
 * ·基于界定符(Delimiter-based):消息的结束由唯一的标记指出，即发送者在传输完数据 后显示添加一个特殊的字节序列，这个标记不在传输的数据中出现。
 * ·显示长度：在变长字段或者消息前加一个固定大小的字段，用来指示该字段或者消息 中包含多少个字节。
 * 基于界定符的一个特殊情况是，可以用在TCP连接上传输的最后一个消息上：在发送完这个消息后，发送者就简单的关闭
 * (shutdownOutput()或者close()方法)发送端的TCP连接，接收者在读取完这个消息的最后一个字节后将接收到
 * 一个流结束标记（即read方法返回的-1），该标记指示已经到达消息的末尾。
 * 基于界定符的方法通常用在文本方式编码的消息中：定义一个特殊的字符或者字符串来标识消息的结束。接收者只需要简单的
 * 扫描输入的信息，来查找定界序列，并将定界符前边的字符串返回。这个方法的缺点是消息的本身不能含有定界符。
 * 基于长度的方法要知道消息长度的上限，发送者要首先确定消息的长度，将长度信息存入一个整数，作为消息的前缀。
 * 如果消息的长度不大于255个字节，则需要一个字节，如果消息的长度小于65535个字节，则需要两个字节。
 * 下面是一个Framer的接口。它有两个方法，frameMsg()方法用来添加成帧信息并将制定消息输出到指定的流，
 * nextMsg()则将扫描指定的流，从中抽取一条消息。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public interface Framer {
	/**
	 * 发送前组装数据,添加成帧信息并将制定消息输出到指定的流
	 * 
	 * @param message
	 * @param out
	 * @throws IOException
	 */
	void frameMsg(byte[] message, OutputStream out) throws IOException;

	/**
	 * 解析数据,扫描指定的流，从中抽取一条消息
	 * 
	 * @return
	 * @throws IOException
	 */
	byte[] nextMsg() throws IOException;
}
