package com.easyway.java.basic.collection;

/**
 * <pre>
 * Java中的锁与排队上厕所。
 * 锁就是阻止其它进程或线程进行资源访问的一种方式，即锁住的资源不能被其它请求访问。
 * 在JAVA中，sychronized关键字用来对一个对象加锁。比如:
 * 
 * Java的加锁解锁跟多个人排队等一个公共厕位完全一样。第一个人进去后顺手把门从里面锁住，其它人只好排队等。第一个人结束后出来时，门才会打开（解锁）。轮到第二个人进去，同样他又会把门从里面锁住，其它人继续排队等待。
 * 
 * 用厕所理论可以很容易明白: 一个人进了一个厕位，这个厕位就会锁住，但不会导致另一个厕位也被锁住，因为一个人不能同时蹲在两个厕位里。对于Java 就是说：Java中的锁是针对同一个对象的，不是针对class的。看下例：
 * 
 * MyStatck m1 = new MyStack();
 * MyStatck m2 = new Mystatck();
 * m1.pop();
 * m2.pop();  
 * m1对象的锁是不会影响m2的锁的，因为它们不是同一个厕位。就是说，假设有 3线程t1,t2,t3操作m1，那么这3个线程只可能在m1上排队等，假设另2个线程 t8,t9在操作m2，那么t8,t9只会在m2上等待。而t2和t8则没有关系，即使m2上的锁释放了，t1,t2,t3可能仍要在m1上排队。原因无它，不是同一个厕位耳。
 * 
 * Java不能同时对一个代码块加两个锁，这和数据库锁机制不同，数据库可以对一条记录同时加好几种不同的锁，请参见：
 * </pre>
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