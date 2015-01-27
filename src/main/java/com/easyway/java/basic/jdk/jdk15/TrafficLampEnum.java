package com.easyway.java.basic.jdk.jdk15;
/**
 *  jdk1.5新特性5之枚举之枚举类型的应用
 * @author Administrator
 *
 */
public enum TrafficLampEnum {
	RED, GREEN, YELLOW;
	public static void main(String[] args) {
		TrafficLampEnum red = TrafficLampEnum.RED;
		System.out.println(red);
		System.out.println(red.name());
		System.out.println(red.ordinal());
		System.out.println(TrafficLampEnum.valueOf("YELLOW"));
		TrafficLampEnum[] ts = TrafficLampEnum.values();
		for (TrafficLampEnum t : ts) {
			System.out.println(t.name());
		}
	}
}
