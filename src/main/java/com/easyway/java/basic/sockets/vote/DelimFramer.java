package com.easyway.java.basic.sockets.vote;


import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 使用边界符处理和解析数据
 * @author Suifeng
 *
 */
public class DelimFramer implements Framer
{
	private InputStream in;
	private static final int DELIMTER = '\n';
	
	public DelimFramer(InputStream in)
	{
		this.in = in;
	}
	
	@Override
	public void frameMsg(byte[] message, OutputStream out) throws IOException
	{
		for(byte b : message)
		{
			// 检查消息是否包含界定符，如果包含，则要抛出一个异常
			if(DELIMTER == b)
			{
				throw new IOException("Message contains delimiter");
			}
		}
		
		// 写消息
		out.write(message);
		
		// 将成帧信息输出到流中
		out.write(DELIMTER);
		
		out.flush();
	}

	@Override
	public byte[] nextMsg() throws IOException
	{
		ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
		int nextByte;
		
		// 读取流中的字节，知道遇到定界符
		while((nextByte = in.read()) != DELIMTER)
		{
			// 到达流末尾
			if(nextByte == -1)
			{
				if(messageBuffer.size() == 0)
				{
					// 消息已经接收完
					return null;
				}
				else
				{
					// 消息没有定界符，抛出异常
					throw new EOFException("Non-empty message without delimiter");
				}
			}
			
			// 将无定界符的消息写入消息缓冲区
			messageBuffer.write(nextByte);
		}
		
		// 转换成字节数组返回
		return messageBuffer.toByteArray();
	}

}
