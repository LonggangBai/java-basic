package com.easyway.java.basic.staticz;

/**
 * <pre>
 * 
 * 二、static
 * 
 *         static表示“全局”或者“静态”的意思，用来修饰成员变量和成员方法，也可以形成静态static代码块，但是Java语言中没有全局变量的概念。
 * 
 *         被static修饰的成员变量和成员方法独立于该类的任何对象。也就是说，它不依赖类特定的实例，被类的所有实例共享。只要这个类被加载，Java虚
 *         拟机就能根据类名在运行时数据区的方法区内定找到他们。因此，static对象可以在它的任何对象创建之前访问，无需引用任何对象。
 * 
 *         用public修饰的static成员变量和成员方法本质是全局变量和全局方法，当声明它类的对象市，不生成static变量的副本，而是类的所有实例共享同一个static变量。
 *  
 *         static变量前可以有private修饰，表示这个变量可以在类的静态代码块中，或者类的其他静态成员方法中使用（当然也可以在非静态成员方法中使用--废话），
 *         但是不能在其他类中通过类名来直接引用，这一点很重要。实际上你需要搞明白，private是访问权限限定，static表示不要实例化就可以使用，这样就容易理解多了。
 *         static前面加上其它访问权限关键字的效果也以此类推。
 *  
 *         static修饰的成员变量和成员方法习惯上称为静态变量和静态方法，可以直接通过类名来访问，访问语法为：
 * 类名.静态方法名(参数列表...) 
 * 类名.静态变量名
 *         用static修饰的代码块表示静态代码块，当Java虚拟机（JVM）加载类时，就会执行该代码块（用处非常大，呵呵）。
 *  
 * 1、static变量
 *         按照是否静态的对类成员变量进行分类可分两种：一种是被static修饰的变量，叫静态变量或类变量；另一种是没有被static修饰的变量，叫实例变量。两者的区别是：
 *         对于静态变量在内存中只有一个拷贝（节省内存），JVM只为静态分配一次内存，在加载类的过程中完成静态变量的内存分配，可用类名直接访问（方便），当然也可以
 *         通过对象来访问（但是这是不推荐的）。
 *         对于实例变量，没创建一个实例，就会为实例变量分配一次内存，实例变量可以在内存中有多个拷贝，互不影响（灵活）。
 *  
 * 2、静态方法
 *         静态方法可以直接通过类名调用，任何的实例也都可以调用，因此静态方法中不能用this和super关键字，不能直接访问所属类的实例变量和实例方法(就是不带static的成员变量和成员成员方法)，只能访问所属类的静态成员变量和成员方法。因为实例成员与特定的对象关联！这个需要去理解，想明白其中的道理，不是记忆！！！
 *         因为static方法独立于任何实例，因此static方法必须被实现，而不能是抽象的abstract。
 *  
 * 3、static代码块
 *         static代码块也叫静态代码块，是在类中独立于类成员的static语句块，可以有多个，位置可以随便放，它不在任何的方法体内，JVM加载类时会执行这些静态的代码块，如果static代码块有多个，JVM将按照它们在类中出现的先后顺序依次执行它们，每个代码块只会被执行一次。例如：
 *
 *    利用静态代码块可以对一些static变量进行赋值，最后再看一眼这些例子，都一个static的main方法，这样JVM在运行main方法的时候可以直接调用而不用创建实例。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class Staticblock {
	private static int a;
	private int b;

	static {
		Staticblock.a = 3;
		System.out.println(a);
		Staticblock t = new Staticblock();
		t.f();
		t.b = 1000;
		System.out.println(t.b);
	}

	static {
		Staticblock.a = 4;
		System.out.println(a);
	}

	public static void main(String[] args) {
		// TODO 自动生成方法存根
	}

	static {
		Staticblock.a = 5;
		System.out.println(a);
	}

	public void f() {
		System.out.println("hhahhahah");
	}
}