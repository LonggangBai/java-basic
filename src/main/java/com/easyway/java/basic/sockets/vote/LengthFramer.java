package com.easyway.java.basic.sockets.vote;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 下面是基于长度的成帧方法，适用于小于65535自己的消息。
 * 发送者先给出消息的长度，并将长度信息以big-endian顺序写入两个字节的整数中，再将这两个字节放到完整的消息的内容前，连同消息一起写入输出流。
 * 下面是具体的代码实现
 * 
 * @author Administrator
 * 
 */
public class LengthFramer implements Framer {
	public static final int MAX_MESSAGE_LENGTH = 65535;
	public static final int BYTE_MASK = 0xff;
	public static final int SHORT_MASK = 0xff;
	public static final int BYTE_SHIFT = 8;

	private DataInputStream in;

	public LengthFramer(InputStream in) {
		this.in = new DataInputStream(in);
	}

	@Override
	public void frameMsg(byte[] message, OutputStream out) throws IOException {
		if (message.length > MAX_MESSAGE_LENGTH) {
			throw new IOException("Message too long");
		}

		// 高8位
		out.write((message.length >> BYTE_SHIFT) & BYTE_MASK);
		// 低8位
		out.write(message.length & BYTE_MASK);
		out.write(message);

		out.flush();
	}

	@Override
	public byte[] nextMsg() throws IOException {
		int length = 0;
		try {
			length = in.readUnsignedShort(); // 两个字节
		} catch (EOFException e) {
			return null;
		}

		byte[] msg = new byte[length];

		in.readFully(msg); // 阻塞等待，直到收到足够的字节

		return msg;
	}

}
