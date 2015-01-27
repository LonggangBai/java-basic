package com.easyway.java.basic.sockets.vote;

/**
 * 投票消息实体
 * <pre>
 *  3.5 构建和解析消息协议 下面看一个简单的例子。
 * 程序支持两种请求。一种是查询（inquiry），即向服务器询问给定候选人当前获得的投票总数。服务器发回一个响应消息，包含了原来的候
 * 选人ID和该候选人当前（查询请求收到时）获得的选票总数。另一种是投票（voting）请求，即向指定候选人投一票。服务器对这种请求也
 * 发回响应消息，包含了候选人ID和其获得的选票数（包括了刚投的一票）。
 * 下面是投票信息的实体类，包含四个属性是否查询消息、是否返回消息、候选人编号和投票总数。
 * </pre>
 * @author Suifeng
 * 
 */
public class VoteMsg {
	private boolean isInquery; // 是否查询消息
	private boolean isResponse; // 是否返回的消息
	private int candidate; // 候选人编号
	private long voteCount; // 投票总数

	private static final int MAX_CANDIDATE_ID = 1000;

	public VoteMsg(boolean isInquery, boolean isResponse, int candidate,
			long voteCount) {

		if ((!isResponse) && voteCount > 0) {
			throw new IllegalArgumentException(
					"Request vote count must be zero");
		}

		if (candidate < 0 || candidate > MAX_CANDIDATE_ID) {
			throw new IllegalArgumentException("Bad candidate ID:" + candidate);
		}

		if (voteCount < 0) {
			throw new IllegalArgumentException(
					"Total count must be greanter than zero");
		}

		this.isInquery = isInquery;
		this.isResponse = isResponse;
		this.candidate = candidate;
		this.voteCount = voteCount;
	}

	public boolean isInquery() {
		return isInquery;
	}

	public void setInquery(boolean isInquery) {
		this.isInquery = isInquery;
	}

	public boolean isResponse() {
		return isResponse;
	}

	public void setResponse(boolean isResponse) {
		this.isResponse = isResponse;
	}

	public int getCandidate() {
		return candidate;
	}

	public void setCandidate(int candidate) {
		if (candidate < 0 || candidate > MAX_CANDIDATE_ID) {
			throw new IllegalArgumentException("Bad candidate ID:" + candidate);
		}

		this.candidate = candidate;
	}

	public long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(long voteCount) {
		if (((!isResponse) && voteCount > 0) || voteCount < 0) {
			throw new IllegalArgumentException("Bad vote count");
		}

		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		String res = "";

		res = (isInquery ? "inquery" : "vote") + " for cadidate " + candidate;

		if (isResponse) {
			res = "Response to " + res + " who now has " + voteCount
					+ " vote(s)";
		}

		return res;
	}

}
