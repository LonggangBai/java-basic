package com.easyway.java.basic.superthis;

/**
 * <pre>
 * Java关键字this、super使用总结
 *  
 * 一、this
 *         Java关键字this只能用于方法方法体内。当一个对象创建后，Java虚拟机（JVM）就会给这个对象分配一个引用自身的指针，
 *         这个指针的名字就是this。因此，this只能在类中的非静态方法中使用，静态方法和静态的代码块中绝对不能出现this，
 *         这在“Java关键字static、final使用总结”一文中给出了明确解释。并且this只和特定的对象关联，而不和类关联，
 *         同一个类的不同对象有不同的this。下面给出一个使用this的综合实例，以便说明问题：
 *         
 *         
 *          看着上面的例子，说明在什么情况下需要用到this：
 *         第一、通过this调用另一个构造方法，用发是this(参数列表)，这个仅仅在类的构造方法中，别的地方不能这么用。
 *         第二、函数参数或者函数中的局部变量和成员变量同名的情况下，成员变量被屏蔽，此时要访问成员变量则需要用“this.成员变量名”的方式来引用成员变量。当然，在没有同名的情况下，可以直接用成员变量的名字，而不用this，用了也不为错，呵呵。
 *         第三、在函数中，需要引用该函所属类的当前对象时候，直接用this。
 *         其实这些用法总结都是从对“this是指向对象本身的一个指针”这句话的更深入的理解而来的，死记不然容易忘记而且容易搞错，要理解！
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class TestThis {
	private int number;
	private String username;
	private String password;
	private int x = 100;

	public TestThis(int n) {
		number = n; // 这个还可以写为: this.number=n;
	}

	public TestThis(int i, String username, String password) {
		// 成员变量和参数同名,成员变量被屏蔽,用"this.成员变量"的方式访问成员变量.
		this.username = username;
		this.password = password;
	}

	// 默认不带参数的构造方法
	public TestThis() {
		this(0, "未知", "空"); // 通过this调用另一个构造方法
	}

	public TestThis(String name) {
		this(1, name, "空"); // 通过this调用另一个构造方法
	}

	public static void main(String args[]) {
		TestThis t1 = new TestThis();
		TestThis t2 = new TestThis("游客");
		t1.outinfo(t1);
		t2.outinfo(t2);
	}

	private void outinfo(TestThis t) {
		System.out.println("-----------");
		System.out.println(t.number);
		System.out.println(t.username);
		System.out.println(t.password);
		f(); // 这个可以写为: this.f();
	}

	private void f() {
		// 局部变量与成员变量同名,成员变量被屏蔽,用"this.成员变量"的方式访问成员变量.
		int x;
		x = this.x++;
		System.out.println(x);
		System.out.println(this.x);
	}

	// 返回当前实例的引用
	private TestThis getSelf() {
		return this;
	}
}