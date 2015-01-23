package com.easyway.java.error;

interface Playable {
	void play();
}

interface Bounceable {
	void play();
}

interface Rollable extends Playable, Bounceable {
	Ball ball = new Ball("PingPang");
}

public class Ball implements Rollable {
	private String name;

	public String getName() {
		return name;
	}

	public Ball(String name) {
		this.name = name;
	}

	/**
	 * "interface Rollable extends Playable, Bounceable"没有问题。
	 * Interface可继承多个interfaces，所以这里没错。问题出在interface
	 * Rollable里的"Ball ball = new Ball("PingPang");"。任何在interface里声明的interface
	 * variable (接口变量，也可称成员变量)，默认为public static
	 * final。也就是说"Ball ball = new Ball("PingPang");"
	 * 实际上是"public static final Ball ball = new Ball("
	 * PingPang");"。在Ball类的Play()方法中，"ball = new Ball("Football");
	 * "改变了ball的reference，而这里的ball来自Rollable
	 * interface，Rollable interface里的ball是public static
	 * final的，final的object是不能被改变reference的
	 * 。因此编译器将在"ball = new Ball("Football");"这里显示有错。
	 */
	public void play() {
		// ball = new Ball("Football");
		// System.out.println(ball.getName());
	}
}
