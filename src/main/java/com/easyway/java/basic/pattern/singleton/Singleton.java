package com.easyway.java.basic.pattern.singleton;

public class Singleton {
	private static Singleton instance = null;

	private Singleton(){
		
	}
	//其他形式:
	//	定义一个类，它的构造函数为private的，所有方法为static的。
	//	一般认为第一种形式要更加安全些 
	public static synchronized Singleton getInstance() {
		// 这个方法比上面有所改进，不用每次都进行生成对象，只是第一次　 　
		// 使用时生成实例，提高了效率！
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}