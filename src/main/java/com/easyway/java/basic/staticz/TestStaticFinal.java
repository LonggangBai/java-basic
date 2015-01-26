package com.easyway.java.basic.staticz;

import java.util.ArrayList;

/**
 * <pre>
 * class T{
 * 
 * static int i=47;
 * 
 * }
 * 
 * 1.java 中，使用new创建对象后才会分配存储空间，方法才能被外界调用，否则可能出现“NullPointerException”的错误。
 * 
 * 2.但是对于有static声明的变量和方法则不需要。这些变量和方法不与任何包含它的类的任何对象关联，即使没有创建对象也可以调用。
 * 
 * 3.即使创建了两个对象T，变量i只有一份存储空间，共享一个i。
 * 
 * 4.可以创建对象去访问，也可以使用类名访问
 * 
 * 5.static修饰的方法差别没有那么大。
 * 
 * 
 * 4、static和final一块用表示什么
 *         static final用来修饰成员变量和成员方法，可简单理解为“全局常量”！
 *         对于变量，表示一旦给值就不可修改，并且通过类名可以访问。
 *         对于方法，表示不可覆盖，并且可以通过类名直接访问。
 *        
 *         特别要注意一个问题：
 *         对于被static和final修饰过的实例常量，实例本身不能再改变了，但对于一些容器类型（比如，ArrayList、HashMap）的实例变量，
 *         不可以改变容器变量本身，但可以修改容器中存放的对象，这一点在编程中用到很多。
 *         也许说了这么多，反倒把你搞晕了，还是看个例子吧：
 *         
 *         
 *         运行结果如下：
 * -------------值处理前----------
 * strStaticFinalVar=aaa
 * strStaticVar=null
 * strFinalVar=null
 * intStaticFinalVar=0
 * integerStaticFinalVar=8
 * alStaticFinalVar=[]
 * -------------值处理后----------
 * strStaticFinalVar=aaa
 * strStaticVar=哈哈哈哈
 * strFinalVar=null
 * intStaticFinalVar=0
 * integerStaticFinalVar=8
 * alStaticFinalVar=[aaa, bbb]
 * 
 * Process finished with exit code 0
 *  
 *         看了上面这个例子，就清楚很多了，但必须明白：通过static final修饰的容器类型变量中所“装”的对象是可改变的。这是和一般基本类型和类类型变量差别很大的地方。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class TestStaticFinal {
	private static final String strStaticFinalVar = "aaa";
	private static String strStaticVar = null;
	private final String strFinalVar = null;
	private static final int intStaticFinalVar = 0;
	private static final Integer integerStaticFinalVar = new Integer(8);
	private static final ArrayList<String> alStaticFinalVar = new ArrayList<String>();

	private void test() {
		System.out.println("-------------值处理前----------\r\n");
		System.out.println("strStaticFinalVar=" + strStaticFinalVar + "\r\n");
		System.out.println("strStaticVar=" + strStaticVar + "\r\n");
		System.out.println("strFinalVar=" + strFinalVar + "\r\n");
		System.out.println("intStaticFinalVar=" + intStaticFinalVar + "\r\n");
		System.out.println("integerStaticFinalVar=" + integerStaticFinalVar
				+ "\r\n");
		System.out.println("alStaticFinalVar=" + alStaticFinalVar + "\r\n");

		// strStaticFinalVar="哈哈哈哈"; //错误，final表示终态,不可以改变变量本身.
		strStaticVar = "哈哈哈哈"; // 正确，static表示类变量,值可以改变.
		// strFinalVar="呵呵呵呵"; //错误, final表示终态，在定义的时候就要初值（哪怕给个null），一旦给定后就不可再更改。
		// intStaticFinalVar=2; //错误,
		// final表示终态，在定义的时候就要初值（哪怕给个null），一旦给定后就不可再更改。
		// integerStaticFinalVar=new Integer(8); //错误,
		// final表示终态，在定义的时候就要初值（哪怕给个null），一旦给定后就不可再更改。
		alStaticFinalVar.add("aaa"); // 正确，容器变量本身没有变化，但存放内容发生了变化。这个规则是非常常用的，有很多用途。
		alStaticFinalVar.add("bbb"); // 正确，容器变量本身没有变化，但存放内容发生了变化。这个规则是非常常用的，有很多用途。

		System.out.println("-------------值处理后----------\r\n");
		System.out.println("strStaticFinalVar=" + strStaticFinalVar + "\r\n");
		System.out.println("strStaticVar=" + strStaticVar + "\r\n");
		System.out.println("strFinalVar=" + strFinalVar + "\r\n");
		System.out.println("intStaticFinalVar=" + intStaticFinalVar + "\r\n");
		System.out.println("integerStaticFinalVar=" + integerStaticFinalVar
				+ "\r\n");
		System.out.println("alStaticFinalVar=" + alStaticFinalVar + "\r\n");
	}

	public static void main(String args[]) {
		new TestStaticFinal().test();
	}
}