package com.easyway.java.basic.sockets.vote;

import java.io.IOException;

/**
 * 
 * 构建和解析消息协议 下面看一个简单的例子。
 * 程序支持两种请求。一种是查询（inquiry），即向服务器询问给定候选人当前获得的投票总数。服务器发回一个响应消息
 * ，包含了原来的候选人ID和该候选人当前（查询请求收到时
 * ）获得的选票总数。另一种是投票（voting）请求，即向指定候选人投一票。服务器对这种请求也发回响应消息
 * ，包含了候选人ID和其获得的选票数（包括了刚投的一票）。 下面是投票信息的实体类，包含四个属性是否查询消息、是否返回消息、候选人编号和投票总数。
 * 有了投票的消息，还需要一定的协议对其进行编码和解码。VoteMsgCoder提供了对投票信息进行编码和解码的接口。
 * 消息与二进制的转换接口
 * 
 * @author Suifeng
 * 
 */
public interface VoteMsgCoder {
	/**
	 * 将投票信息转换成二进制流（根据特定的协议，将消息转换成字节序列）
	 * 
	 * @param msg
	 *            投票信息
	 * @return
	 * @throws IOException
	 */
	byte[] toWire(VoteMsg msg) throws IOException;

	/**
	 * 将二进制流转换成消息（根据相同的协议，对字节序列进行解析，根据消息的内容构造出一个消息类的实例）
	 * 
	 * @param input
	 * @throws IOException
	 */
	VoteMsg fromWire(byte[] input) throws IOException;
}
