package com.easyway.java.basic.sockets.vote.binary;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.easyway.java.basic.sockets.vote.VoteMsg;
import com.easyway.java.basic.sockets.vote.VoteMsgCoder;

/**
 * <pre>
 *3.5.2 基于二进制的表示方法
 *下面将展示另一种对投票协议消息进行编码的方法。与基于文本的格式相反，二进制格式使用固定大小的消息。每条消息由一个特殊字节开始，
 *该字节的最高六位为一个"魔术"值010101。
 *这一点少量的冗余信息为接收者收到适当的投票消息提供了一定程度的保证。
 *该字节的最低两位对两个布尔值进行了编码。消息的第二个字节总是0，第三、第四个字节包含了candidateID值。
 *只有响应消息的最后8个字节才包含了选票总数信息。
 *
 * 使用二进制编码、解码投票信息
 * =================================================
 *   0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+ 
 * | Magic           |Flags|        Zero           |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+ 
 * |               Candidate                       |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |											   | 
 * |          Vote Count(Only inresponse)          |
 * |											   |
 * |											   | 
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * 
 * </pre>
 */
public class VoteMsgBinaryCoder implements VoteMsgCoder
{
	/**
	 * 最小字节数
	 */
	public static final int MIN_WIRE_LENGTH = 4;
	
	/**
	 * 最大字节数（包含4个字节投票总数）
	 */
	public static final int MAX_WIRE_LENGTH = 8;
	
	/**
	 * 魔术数字 010101 00 0000 0000
	 */
	public static final int MAGIC = 0x5400;
	
	/**
	 * 魔术数字掩码值 111111 00 0000 0000
	 */
	public static final int MAGIC_MASK = 0xfc00;
	
	/**
	 * 获取魔术数字的偏移字节数
	 */
	public static final int MAGIC_SHIFT = 8;
	/**
	 * 返回标记掩码值 0000 0010 0000 0000
	 */
	public static final int RESPONSE_FLAG = 0x0200;
	
	/**
	 * 查询标记掩码值 0000 0001 0000 0000
	 */
	public static final int INQUERY_FLAG = 0x0100;

	@Override
	public VoteMsg fromWire(byte[] input) throws IOException
	{
		System.out.println("input.length="+input.length);
		if (input.length < MIN_WIRE_LENGTH)
		{
			throw new IOException("Runt Message!!!");
		}

		ByteArrayInputStream bs = new ByteArrayInputStream(input);
		DataInputStream in = new DataInputStream(bs);

		
		int magic = in.readShort();
		// 验证掩码值是否有效，防止冗余数据出现
		if ((magic & MAGIC_MASK) != MAGIC)
		{
			throw new IOException("Bad Magic #:"
					+ ((magic & MAGIC_MASK) >> MAGIC_SHIFT));
		}
		// 获取返回标记
		boolean isResponse = (magic & RESPONSE_FLAG) != 0;
		
		// 获取查询标记
		boolean isInquery = (magic & INQUERY_FLAG) != 0;
		
		// 获取候选人编号（2个字节）
		int candidate = in.readShort();
		if(candidate < 0|| candidate > 1000)
		{
			throw new IOException("Bad candidate ID:"+candidate);
		}
		
		long count = 0;
		if(isResponse)
		{
			// 获取投票总数(4个字节)
			count = in.readLong();
			if(count < 0)
			{
				throw new IOException("Bad vote count:"+count);
			}
		}
		
		return new VoteMsg(isInquery,isResponse,candidate,count);
	}

	@Override
	public byte[] toWire(VoteMsg msg) throws IOException
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(byteStream);

		short magicAndFlags = MAGIC;
		
		// 查询标记
		if (msg.isInquery())
		{
			magicAndFlags |= INQUERY_FLAG;
		}
		
		// 返回标记
		if (msg.isResponse())
		{
			magicAndFlags |= RESPONSE_FLAG;
		}
		 // 记录前两个字节（6位魔术数字+1位返回标记+1位查询标记+8位0）
		out.writeShort(magicAndFlags);
		// 记录候选人标记（两字节，16位）
		out.writeShort((short) msg.getCandidate());
		
		if (msg.isResponse())
		{
			// 总个数（8字节，64位）
			out.writeLong(msg.getVoteCount());
		}

		out.flush();
		byte[] msgBytes = byteStream.toByteArray();
		System.out.println("encode msg byte length="+msgBytes.length);
		return byteStream.toByteArray();
	}

}
