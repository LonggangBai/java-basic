package com.easyway.java.error;

public class Something {
	//Final int i是个final的instant variable (实例变量，或叫成员变量)。Final的instant variable没有default value，必须在constructor 
	//(构造器)结束之前被赋予一个明确的值。可以修改为"final int I = 0;"。
	//final int m;
	/**
	 *  Int x被修饰成final，意味着x不能在addOne method中被修改
	 * @param x
	 * @return
	 */
	public int addOne(final int x) {
		// return ++x;
		return x;
	}

	public static void main(String[] args) {
		Other o = new Other();
		new Something().addOne(o);
	}

	/**
	 * 在addOne method中，参数o被修饰成final。如果在addOne method里我们修改了o的reference (比如: o =
	 * new Other();)，那么如同上例这题也是错的。但这里修改的是o的member vairable
	 * (成员变量)，而o的reference并没有改变。
	 * 
	 * @param o
	 */
	public void addOne(final Other o) {
		o.i++;
	}
}

class Other {
	public int i;
}