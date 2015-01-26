package com.easyway.java.basic.clazz;

class A {
	public A() {
		System.out.print("2");
	}

}

class B extends A {
	public B() {
		System.out.print("b");
	}
}

public class Clazz {
	public static void main(String[] args) {
		A a = new B();
		a = new B();
	}
}