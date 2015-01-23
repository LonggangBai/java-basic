package com.easyway.java.basic.enums;

public class FlowSessionStatus {
	private String label;
	private int code;
	// public static final instances!
	public static FlowSessionStatus CREATED = new FlowSessionStatus(0,
			"Created");
	public static FlowSessionStatus ACTIVE = new FlowSessionStatus(1, "Active");
	public static FlowSessionStatus PAUSED = new FlowSessionStatus(2, "Paused");
	public static FlowSessionStatus SUSPENDED = new FlowSessionStatus(3,
			"Suspended");
	public static FlowSessionStatus ENDED = new FlowSessionStatus(4, "Ended");

	// private constructor!
	private FlowSessionStatus(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}