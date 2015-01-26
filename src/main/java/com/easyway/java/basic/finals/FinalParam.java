package com.easyway.java.basic.finals;

/**
 * <pre>
 * 

 * 4、final参数 
 * 当函数参数为final类型时，你可以读取使用该参数，但是无法改变该参数的值。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class FinalParam {
	public static void main(String[] args) {
		new FinalParam().f1(2);
	}

	public void f1(final int i) {
		// i++; //i是final类型的,值不允许改变的.
		System.out.print(i);
	}
}