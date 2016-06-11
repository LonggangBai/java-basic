package com.easyway.java.basic.jdk.jdk15;

public enum TrafficLampComplex {
	// 元素列表必须放在枚举类的最上面
	RED(10) {
		public TrafficLampComplex nextLamp() {
			return GREEN;
		}

		public String getValue() {
			return "红灯，时长" + this.getTime();
		}
	},
	GREEN(10) {
		public TrafficLampComplex nextLamp() {
			return YELLOW;
		}

		public String getValue() {
			return "绿灯，时长" + this.getTime();
		}
	},
	YELLOW(5) {
		public TrafficLampComplex nextLamp() {
			return YELLOW;
		}

		public String getValue() {
			return "黄灯，时长" + this.getTime();
		}
	};
	/**
	 * 时长
	 */
	private int time;

	TrafficLampComplex() {
	}

	TrafficLampComplex(int time) {
		this.time = time;
	}

	/**
	 * 下一个灯
	 * 
	 * @return
	 */
	public abstract TrafficLampComplex nextLamp();

	/**
	 * 取值
	 * 
	 * @return
	 */
	public abstract String getValue();

	public int getTime() {
		return time;
	}

	public static void main(String[] args) {
		TrafficLampComplex tcRed = TrafficLampComplex.RED;
		System.out.println(tcRed.nextLamp());
		System.out.println(tcRed.getValue());

	}

	public void setTime(int time) {
		this.time = time;
	}
}
