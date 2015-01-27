package com.easyway.java.basic.jdk.jdk15;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * JDK各个版本的新特性  
 * 对于很多刚接触java语言的初学者来说，要了解一门语言，最好的方式就是要能从基础的版本进行了解，升级的过程，以及升级的新特性，这样才能循序渐进的
 * 学好一门语言。今天先为大家介绍一下JDK1.5版本到JDK1.7版本的特性。希望能给予帮助。
 * JDK1.5新特性：
 *  
 * 1.自动装箱与拆箱：
 * 自动装箱的过程：每当需要一种类型的对象时，这种基本类型就自动地封装到与它相同类型的包装中。
 * 自动拆箱的过程：每当需要一个值时，被装箱对象中的值就被自动地提取出来，没必要再去调用intValue()和doubleValue()方法。
 * 自动装箱，只需将该值赋给一个类型包装器引用，java会自动创建一个对象。
 * 自动拆箱，只需将该对象值赋给一个基本类型即可。
 * java——类的包装器
 * 类型包装器有：Double,Float,Long,Integer,Short,Character和Boolean
 *  
 * 2.枚举
 * 把集合里的对象元素一个一个提取出来。枚举类型使代码更具可读性，理解清晰，易于维护。枚举类型是强类型的，从而保证了系统安全性。而以类的静态字段
 * 实现的类似替代模型，不具有枚举的简单性和类型安全性。
 * 简单的用法：JavaEnum简单的用法一般用于代表一组常用常量，可用来代表一类相同类型的常量值。
 * 复杂用法：Java为枚举类型提供了一些内置的方法，同事枚举常量还可以有自己的方法。可以很方便的遍历枚举对象。
 *  
 * 3.静态导入
 * 通过使用 import static，就可以不用指定 Constants 类名而直接使用静态成员，包括静态方法。
 * import xxxx 和 import static xxxx的区别是前者一般导入的是类文件如import java.util.Scanner;后者一般是导入静态的方法，
 * import static java.lang.System.out。
 *  
 * 4.可变参数（Varargs）
 *  
 * 可变参数的简单语法格式为：
 * methodName([argumentList], dataType...argumentName);
 *  
 * 5.内省（Introspector）
 *  
 * 是 Java语言对Bean类属性、事件的一种缺省处理方法。例如类A中有属性name,那我们可以通过getName,setName来得到其值或者设置新 的值。
 * 通过getName/setName来访问name属性，这就是默认的规则。Java中提供了一套API用来访问某个属性的getter /setter方法，通过这些API
 * 可以使你不需要了解这个规则（但你最好还是要搞清楚），这些API存放于包java.beans中。
 * 一 般的做法是通过类Introspector来获取某个对象的BeanInfo信息，然后通过BeanInfo来获取属性的描述器 （PropertyDescriptor），
 * 通过这个属性描述器就可以获取某个属性对应的getter/setter方法，然后我们就可以通过反射机制来 调用这些方法。
 *  
 * 6.泛型(Generic) 
 *  
 * C++ 通过模板技术可以指定集合的元素类型，而Java在1.5之前一直没有相对应的功能。一个集合可以放任何类型的对象，相应地从集合里面拿对象的时
 * 候我们也 不得不对他们进行强制得类型转换。猛虎引入了泛型，它允许指定集合里元素的类型，这样你可以得到强类型在编译时刻进行类型检查的好处。
 *  
 * 7.For-Each循环 
 *  
 * For-Each循环得加入简化了集合的遍历。假设我们要遍历一个集合对其中的元素进行一些处理。
 * 
 * 
 * 一 基本概念
 * 1 装箱
 * 把基本类型用它们相应的引用类型包装起来，使其具有对象的性质。如int——>Integer，float——>Float。
 * 自动装箱
 * Integer a = 100，编译器调用的是static Integer valueOf(int i)
 *               
 * 2 拆箱
 * 和装箱相反，将引用类型的对象简化成值类型的数据
 * 自动拆箱
 * int b = new Integer(100);
 * int c = a +2;
 *  
 * 
 * 二 是否相等
 * 
 * 例一
 * Integer a = new Integer(100); 
 * Integer b = 100; 
 * System.out.println(a == b); 
 * false，很好理解，b的值在编译时就确定下来了，而a是分配一块堆给它，地址当然不等
 * 
 * 例二
 * Integer a = new Integer(100); 
 * Integer b = new Integer(100); 
 * System.out.println(a == b); 
 * false，不解释
 * 
 * 例三
 * Integer a = 100; 
 * Integer b = 100; 
 * System.out.println(a == b); 
 * true，java为了提高效率，IntegerCache类中有一个数组缓存了值从-128到127的Integer对象。如果i的值是[-128，127]时，会直接从这个缓存中返回一个对象，否则就new一个Integer对象。类比String的驻留池。 
 * 
 * 例四
 * Integer a = 128; 
 * Integer b = 128; 
 * System.out.println(a == b); 
 * false，解释见例3
 * 
 * 例五
 * Integer a = Integer.valueOf(100); 
 * Integer b = 100; 
 * System.out.println(a == b); 
 * true
 *  
 * 例六
 * Integer a = Integer.valueOf(128); 
 * Integer b = 128; 
 * System.out.println(a == b); 
 * false
 *  
 * 
 * 三 享元模式
 * 享元模式(Flyweight Pattern)是一种软件设计模式。它使用共享物件，用来尽可能减少内存使用量以及分享资讯给尽可能多的相似物件；它适合用于当大量物件只是重复因而导致无法令人接受的使用大量内存。通常物件中的部分状态是可以分享。常见做法是把它们放在外部数据结构，当需要使用时再将它们传递给享元。正如我们例三展示的。
 * 
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class Jdk15Main {
	public static void main(String args) {
		// 匿名类适合那些只需要使用一次的类
		// 不仅可以使抽象类，也可以是接口。匿名类没有什么特别的地方，同样还是要实现需要实现的方法。
		AnonymousClassDesk desk = new AnonymousClassDesk() {
			@Override
			public double getPrice() {
				return 100;
			}

			@Override
			public String getName() {
				return "匿名书桌";
			}
		};
		// JDK1.5支持静态导入
		System.out.println(desk.getName());
		System.out.print(max(3, 6));
		System.out.print(min(3, 6));
	}

	/**
	 * Java类型后面三个点如(String... strs)是从Java1.5开始对方法参数支持一种新写法，
	 * 叫可变长度参数列表，其语法就是类型后跟三个点，表示此处接受的参数为0到多个Object类型的对象或者是一个Object[]。 三 注意点
	 * 
	 * 值得注意的是动态参数列表必须放在方法签名的最后一个
	 * 
	 * @param strings
	 */
	public static void testVarchar(String... strings) {
		for (int i = 0; i < strings.length; i++) {
			System.out.print(strings[i]);
		}
		System.out.print("\n");
	}

	/**
	 * jdk1.5新特性3之加强for循环
	 */
	public static void testFor() {
		List<String> lst = new ArrayList<String>();
		lst.add("aaa");
		lst.add("bbb");
		lst.add("ccc");
		// 普通循环
		for (int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i));
		}

		// 加强版
		for (String s : lst) {
			System.out.print(s);
		}
	}

	/**
	 * 
	 * 

	 */
	public void dd() {
		Integer a = new Integer(100);
		Integer b = new Integer(100);
		System.out.println(a == b);
		Integer aa = new Integer(100);
		Integer bb = 100;
		System.out.println(aa == bb);
		// false，很好理解，b的值在编译时就确定下来了，而a是分配一块堆给它，地址当然不等
	}
}

abstract class AnonymousClassDesk {
	public abstract double getPrice();

	public abstract String getName();
}
