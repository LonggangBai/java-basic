package com.easyway.java.basic.sockets.vote;


import java.util.HashMap;
import java.util.Map;

/**
 * 3.5.3 发送和接收
 *下面是一个投票服务器用到的服务，用于处理服务器端接收到的投票信息的处理。
 * 服务器端对获取的投票信息进行处理
 * @author Administrator
 *
 */
public class VoteService
{
	private Map<Integer, Long> results = new HashMap<Integer, Long>();
	
	public VoteMsg handleRequest(VoteMsg msg)
	{
		if(msg.isResponse())
		{
			return msg;
		}
		
		// 设置投票信息返回标记
		msg.setResponse(true);
		
		// 投票人编号
		int candidate = msg.getCandidate();
		
		// 获取当前人的投票总数
		Long count = results.get(candidate);
		
		// 没有该候选人的投票信息
		if(count == null)
		{
			count = 0L;
		}
	
		// 为该候选人投票
		if(!msg.isInquery())
		{
			results.put(candidate, ++count);
		}
		
		// 设置投票总数
		msg.setVoteCount(count);
		
		return msg;
	}
}
