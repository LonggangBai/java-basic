package com.easyway.java.basic.jdk.jdk15;
/**
 *  jdk1.5新特性5之枚举之模拟枚举类型
 * 从这个例子中我们看出，枚举类型其实就是一个类返回该类本身
 * @author Administrator
 *
 */
public class TrafficLampEasy {
	private int time;
	public final static TrafficLampEasy REDLAMP = new TrafficLampEasy(20);
	public final static TrafficLampEasy GREENLAMP = new TrafficLampEasy(20);
	public final static TrafficLampEasy YELLOWLAMP = new TrafficLampEasy(5);

	public TrafficLampEasy() {
	}

	public TrafficLampEasy(int time) {
		this.time = time;
	}

	public TrafficLampEasy nextLamp() {
		TrafficLampEasy result = new TrafficLampEasy();
		if (this == REDLAMP) {
			result = GREENLAMP;
		} else if (this == GREENLAMP) {
			result = YELLOWLAMP;
		} else if (this == YELLOWLAMP) {
			result = REDLAMP;
		}
		return result;
	}

	public String getValue() {
		String result = "";
		if (this == REDLAMP) {
			result = "红灯,时长:" + time;
		} else if (this == GREENLAMP) {
			result = "绿灯,时长:" + time;
		} else if (this == YELLOWLAMP) {
			result = "黄灯,时长:" + time;
		}
		return result;
	}

	public static void main(String[] args) {
		TrafficLampEasy teRed = TrafficLampEasy.REDLAMP;
		System.out.println(teRed.getValue());
		System.out.println(teRed.nextLamp().getValue());
	}
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}