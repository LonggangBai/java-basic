package com.easyway.java.basic.atomic;


import java.util.concurrent.atomic.AtomicLong;

/* 线程安全计数器 
 * 类似功能的还有 	AtomicBoolean ab = new AtomicBoolean();
 * */

public class Test6 {
	
	final AtomicLong count = new AtomicLong(0); 
	//count.incrementAndGet()方法会让count的值 +1
	public void test(){
		System.out.println(count.incrementAndGet());
	}  
	public static void main(String[] args) {
		Test6 test = new Test6();
		test.test();
		test.test();
		test.test();
		test.test();
	}
	
}
