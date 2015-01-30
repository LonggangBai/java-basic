
package com.easyway.java.basic.enums;
public class DataType {
	private String type;
	
	private DataType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return type;
	}

	/** 
	 * Incoming data type for {@link ClientHandler} 
	 */
	public static final DataType IN = new DataType("Incoming");
	/** 
	 * Outgoing data type for {@link ClientHandler} 
	 */
	public static final DataType OUT = new DataType("Outgoing");
}
