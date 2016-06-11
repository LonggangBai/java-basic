package com.easyway.java.basic.jdk.jdk15;
/**
 *  jdk1.5新特性5之枚举之模拟枚举类型
 * @author Administrator
 *
 */
public abstract class TrafficLamp {
	/**
	 * 下一个灯
	 */
	public abstract TrafficLamp nextLamp();

	/**
	 * 获取值
	 */
	public abstract String getValue();

	/**
	 * 时长
	 */
	private int time;

	public TrafficLamp() {
	}

	public TrafficLamp(int time) {
		this.time = time;
	}

	/**
	 * 红灯，匿名类，相当于继承TrafficLamp抽象类，并实现抽象方法
	 */
	public final static TrafficLamp REDLAMP = new TrafficLamp(50) {
		public TrafficLamp nextLamp() {
			return GREENLAMP;
		}

		public String getValue() {
			return "红灯,时长:" + this.getTime();
		}
	};
	public final static TrafficLamp GREENLAMP = new TrafficLamp(50) {
		public TrafficLamp nextLamp() {
			return YELLOWLAMP;
		}

		public String getValue() {
			return "绿灯,时长:" + this.getTime();
		}
	};
	public final static TrafficLamp YELLOWLAMP = new TrafficLamp(2) {
		public TrafficLamp nextLamp() {
			return REDLAMP;
		}

		public String getValue() {
			return "黄灯,时长:" + this.getTime();
		}
	};

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	public static void main(String[] args) {
		TrafficLamp red = TrafficLamp.REDLAMP;
		System.out.println(red.getValue());
		System.out.println(red.nextLamp().getValue());
	}
}