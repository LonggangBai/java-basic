package com.easyway.java.basic.collection;

/**
 * Java中的锁与排队上厕所。
 * 锁就是阻止其它进程或线程进行资源访问的一种方式，即锁住的资源不能被其它请求访问。
 * 在JAVA中，sychronized关键字用来对一个对象加锁。比如:
 * 
 * @author Administrator
 * 
 */
public class MyStack {
	int idx = 0;
	char[] data = new char[6];

	public synchronized void push(char c) {
		data[idx] = c;
		idx++;
	}

	public synchronized char pop() {
		idx--;
		return data[idx];
	}

	public static void main(String args[]) {
		MyStack m = new MyStack();
		/**
		 * 下面对象m被加锁。严格的说是对象m的所有synchronized块被加锁。
		 * 如果存在另一个试图访问m的线程T，那么T无法执行m对象的push和 pop方法。
		 */
		m.pop();// 对象m被加锁。
	}
}