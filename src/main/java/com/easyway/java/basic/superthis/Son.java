package com.easyway.java.basic.superthis;

/**
 * <pre>
 * 
 * 二、super
 *  
 *         super关键和this作用类似，是被屏蔽的成员变量或者成员方法或变为可见，或者说用来引用被屏蔽的成员变量和成员成员方法。
 * 不过super是用在子类中，目的是访问直接父类中被屏蔽的成员，注意是直接父类（就是类之上最近的超类）。下面是一个综合运用super的例子，
 * 有两个类：一个Father类，一个Father类的子类Son，通过这两个类完全演示了super的用法，一下是代码：
 *    说明：次例子仅仅为了说明super的用法，实际在设计类的时候一般都尽可能私有(private)化。
 *  
 *         通过上面的例子，下面总结一下super的用法：
 *         第一、在子类构造方法中要调用父类的构造方法，用“super(参数列表)”的方式调用，参数不是必须的。同时还要注意的一点是：“super(参数列表)”这条语句只能用在子类构造方法体中的第一行。
 *         第二、当子类方法中的局部变量或者子类的成员变量与父类成员变量同名时，也就是子类局部变量覆盖父类成员变量时，用“super.成员变量名”来引用父类成员变量。当然，如果父类的成员变量没有被覆盖，也可以用“super.成员变量名”来引用父类成员变量，不过这是不必要的。
 *         第三、当子类的成员方法覆盖了父类的成员方法时，也就是子类和父类有完全相同的方法定义（但方法体可以不同），此时，用“super.方法名(参数列表)”的方式访问父类的方法。
 *  
 *         this、super的用法也不过这些，只有理解了其中的原理，才不会跌入陷阱！
 *  
 * 参考资料
 * Thinking in Java
 * Java2参考大全
 * 还有什么书都不记得了。
 * </pre>
 * 
 * @author Administrator
 * 
 */
class Father {
	public String v = "Father";
	public String x = "输出了Father类的public成员变量x!!!";

	public Father() {
		System.out.println("Father构造方法被调用!");
	}

	public Father(String v) {
		this.v = "Father类的带参数构造方法!运行了.";
	}

	public void outinfo() {
		System.out.println("Father的outinfo方法被调用");
	}

	public static void main(String[] args) {
		// TODO 自动生成方法存根
	}
}

public class Son extends Father {
	public String v = "Son";

	public Son() {
		super(); // 调用超类的构造方法,只能放到第一行.
		System.out.println("Son无参数构造方法被调用!");
		// super(); //错误的,必须放到构造方法体的最前面.
	}

	public Son(String str) {
		super(str);
		System.out.println("Son带参数构造方法被调用!");
	}

	// 覆盖了超类成员方法outinfo()
	public void outinfo() {
		System.out.println("Son的outinfo()方法被调用");
	}

	public void test() {

		String v = "哈哈哈哈!"; // 局部变量v覆盖了成员变量v和超类变量v

		System.out.println("------1-----");
		System.out.println(v); // 输出局部变量v
		System.out.println(this.v); // 输出(子类)成员变量v
		System.out.println(super.v); // 输出超类成员变量v

		System.out.println("------2-----");
		System.out.println(x); // 输出超类成员变量v,子类继承而来
		System.out.println(super.x); // 输出超类成员变量v

		System.out.println("------3-----");
		outinfo(); // 调用子类的outinfo()方法
		this.outinfo(); // 调用子类的outinfo()方法
		super.outinfo(); // 调用父类的outinfo()方法
	}

	public static void main(String[] args) {
		new Son().test();

	}
}
