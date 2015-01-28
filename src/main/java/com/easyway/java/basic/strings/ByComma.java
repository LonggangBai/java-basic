/**
 * Project Name:java-basic
 * File Name:ByComma.java
 * Package Name:com.easyway.java.basic.strings
 * Date:2015-1-21下午2:18:54
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.strings;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * java基础知识精华总结
 * 
 * <pre>
 * 1、 对象的初始化
 * (1) 非静态对象的初始化
 * 在创建对象时，对象所在类的所有数据成员会首先进行初始化。
 * 基本类型：int型，初始化为0。
 * 如果为对象：这些对象会按顺序初始化。
 * ※在所有类成员初始化完成之后，才调用本类的构造方法创建对象。
 * 构造方法的作用就是初始化。
 * (2) 静态对象的初始化
 * 程序中主类的静态变量会在main方法执行前初始化。
 * 不仅第一次创建对象时，类中的所有静态变量都初始化，并且第一次访问某类（注意此时
 * 未创建此类对象）的静态对象时，所有的静态变量也要按它们在类中的顺序初始化。
 * 2、 继承时，对象的初始化过程
 * (1) 主类的超类由高到低按顺序初始化静态成员，无论静态成员是否为private。
 * (2) 主类静态成员的初始化。
 * (3) 主类的超类由高到低进行默认构造方法的调用。注意，在调用每一个超类的默认构造
 * 方法前，先进行对此超类进行非静态对象的初始化。
 * (4) 主类非静态成员的初始化。
 * (5) 调用主类的构造方法。
 * 3、 关于构造方法
 * (1) 类可以没有构造方法，但如果有多个构造方法，就应该要有默认的构造方法，否则在继承此类时，需要在子类中显式调用父类的某一个非默认的构造方法了。
 * (2) 在一个构造方法中，只能调用一次其他的构造方法，并且调用构造方法的语句必须是
 * 第一条语句。
 * 4、 有关public、private和protected
 * (1) 无public修饰的类，可以被其他类访问的条件是：a.两个类在同一文件中，b.两个类
 * 在同一文件夹中，c.两个类在同一软件包中。
 * (2) protected：继承类和同一软件包的类可访问。
 * (3) 如果构造方法为private，那么在其他类中不能创建该类的对象。
 * 5、 抽象类
 * (1) 抽象类不能创建对象。
 * (2) 如果一个类中一个方法为抽象方法，则这个类必须为abstract抽象类。
 * (3) 继承抽象类的类在类中必须实现抽象类中的抽象方法。
 * (4) 抽象类中可以有抽象方法，也可有非抽象方法。抽象方法不能为private。
 * (5) 间接继承抽象类的类可以不给出抽象方法的定义。
 * 6、 final关键字
 * (1) 一个对象是常量，不代表不能转变对象的成员，仍可以其成员进行操作。
 * (2) 常量在使用前必须赋值，但除了在声明的同时初始化外，就只能在构造方法中初始化
 * 。
 * (3) final修饰的方法不能被重置（在子类中不能出现同名方法）。
 * (4) 如果声明一个类为final，则所有的方法均为final，无论其是否被final修饰，但数据
 * 成员可为final也可不是。
 * 7、 接口interface （用implements来实现接口）
 * (1) 接口中的所有数据均为 static和final即静态常量。尽管可以不用这两个关键字修饰
 * ，但必须给常量赋初值。
 * (2) 接口中的方法均为public，在实现接口类中，实现方法必须可public关键字。
 * (3) 如果使用public来修饰接口，则接口必须与文件名相同。
 * 8、 多重继承
 * (1) 一个类继承了一个类和接口，那么必须将类写在前面，接口写在后面，接口之间用逗
 * 号分隔。
 * (2) 接口之间可多重继承，注意使用关键字extends。
 * (3) 一个类虽只实现了一个接口，但不仅要实现这个接口的所有方法，还要实现这个接口
 * 继承的接口的方法，接口中的所有方法均须在类中实现。
 * 9、 接口的嵌入
 * (1) 接口嵌入类中，可以使用private修饰。此时，接口只能在所在的类中实现，其他类不
 * 能访问。
 * (2) 嵌入接口中的接口一定要为public。
 * 10、类的嵌入
 * (1) 类可以嵌入另一个类中，但不能嵌入接口中。
 * (2) 在静态方法或其他方法中，不能直接创建内部类对象，需通过手段来取得。
 * 手段有两种：
 * class A {
 * class B {}
 * B getB() {
 * B b = new B();
 * return b;
 * }
 * }
 * static void m() {
 * A a = new A();
 * A.B ab = a.getB(); // 或者是 A.B ab = a.new B();
 * }
 * (3) 一个类继承了另一个类的内部类，因为超类是内部类，而内部类的构造方法不能自动
 * 被调用，这样就需要在子类的构造方法中明确的调用超类的构造方法。
 * 接上例：
 * class C extends A.B {
 * C() {
 * new A().super(); // 这一句就实现了对内部类构造方法的调用。
 * }
 * }
 * 构造方法也可这样写：
 * C(A a) {
 * a.super();
 * } // 使用这个构造方法创建对象，要写成C c = new C(a); a是A的对象。
 * 11、异常类
 * JAVA中除了RunTimeException 类，其他异常均须捕获或抛出。
 * 
 * </pre>
 * 
 * 1．现在输入n个数字，以逗号，分开；然后可选择升或者降序排序；按提交键就在另一页面显示按什么排序，结果为，提供reset
 * 
 * ClassName:ByComma <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午2:18:54 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ByComma {
	public static String[] splitStringByComma(String source) {
		if (source == null || source.trim().equals(""))
			return null;
		StringTokenizer commaToker = new StringTokenizer(source, ",");
		String[] result = new String[commaToker.countTokens()];
		int i = 0;
		while (commaToker.hasMoreTokens()) {
			result[i] = commaToker.nextToken();
			i++;
		}
		return result;
	}

	public static void main(String args[]) {
		String[] s = splitStringByComma("5,8,7,4,3,9,1");
		int[] ii = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			ii[i] = Integer.parseInt(s[i]);
		}
		Arrays.sort(ii);
		// asc
		for (int i = 0; i < s.length; i++) {
			System.out.println(ii[i]);
		}
		// desc
		for (int i = (s.length - 1); i >= 0; i--) {
			System.out.println(ii[i]);
		}
	}
}
