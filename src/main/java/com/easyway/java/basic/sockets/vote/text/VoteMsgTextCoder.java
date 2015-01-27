package com.easyway.java.basic.sockets.vote.text;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.easyway.java.basic.sockets.vote.VoteMsg;
import com.easyway.java.basic.sockets.vote.VoteMsgCoder;

/**
 * 3.5.1 基于文本的表示方法
 * 首先介绍一个用文本方式对消息进行编码的版本。该协议指定使用utf-8字符集对文本进行编码。消息的开头是一个所谓的"魔术字符串"
 * ，即一个字符序列，用于接收者快速将投票协议的消息和网络中随机到来的垃圾消息区分开
 * 。投票/查询布尔值被编码成字符形式，'v'表示投票消息，'i'表示查询消息。
 * 消息的状态，即是否为服务器的响应，由字符'R'指示。状态标记后面是候选人ID，其后跟的是选票总数
 * ，它们都编码成十进制字符串。VoteMsgTextCoder类提供了一种基于文本的VoteMsg编码方法。 使用文本编码、解码投票信息
 */
public class VoteMsgTextCoder implements VoteMsgCoder {
	/**
	 * 魔术字符串，用于排除冗余信息
	 */
	public static final String MAGIC = "Voting";
	/**
	 * 投票标记
	 */
	public static final String VOTESTR = "v";
	/**
	 * 查询标记
	 */
	public static final String INQSTR = "i";
	/**
	 * 返回标记
	 */
	public static final String RESPONSESTR = "R";
	/**
	 * 编码方式
	 */
	public static final String CHARSETNAME = "utf-8";
	/**
	 * 分隔符
	 */
	public static final String DELIMSTR = " ";
	/**
	 * 编码最大字节数
	 */
	public static final int MAX_WIRE_LENGTH = 200;

	@Override
	public VoteMsg fromWire(byte[] input) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(input);
		Scanner s = new Scanner(new InputStreamReader(in, CHARSETNAME));

		String token = "";
		boolean isInquery = false;
		boolean isResponse = false;
		int candidate = 0;
		long voteCount = 0;

		token = s.next();
		// 解析是否是有效的魔术字符串，防止冗余信息
		if (!MAGIC.equals(token)) {
			throw new IOException("Bad magic String:" + token);
		}

		// 记录查询标记
		token = s.next();
		if (INQSTR.equals(token)) {
			isInquery = true;
		} else if (VOTESTR.equals(token)) {
			isInquery = false;
		} else {
			throw new IOException("Bad vote/inq indicator:" + token);
		}

		// 返回标记
		token = s.next();
		if (RESPONSESTR.equals(token)) {
			isResponse = true;
			// 去下一个字段--候选人编号
			token = s.next();
		} else {
			// 非返回信息，该字段是候选人编号
			isResponse = false;
		}

		candidate = Integer.parseInt(token);

		if (isResponse) {
			token = s.next();
			// 读取投票总数
			voteCount = Long.parseLong(token);
		} else {
			voteCount = 0;
		}

		return new VoteMsg(isInquery, isResponse, candidate, voteCount);
	}

	@Override
	public byte[] toWire(VoteMsg msg) throws IOException {
		StringBuilder voteBuilder = new StringBuilder(256);

		// 魔术字符串
		voteBuilder.append(MAGIC).append(DELIMSTR);

		// 查询/投票标记
		voteBuilder.append((msg.isInquery()) ? INQSTR : VOTESTR).append(
				DELIMSTR);

		if (msg.isResponse()) {
			// 返回标记
			voteBuilder.append(RESPONSESTR).append(DELIMSTR);
		}

		// 候选人编号
		voteBuilder.append(msg.getCandidate()).append(DELIMSTR);

		// 投票总数
		voteBuilder.append(msg.getVoteCount()).append(DELIMSTR);

		return voteBuilder.toString().getBytes(CHARSETNAME);
	}

}
