package com.easyway.java.basic.clazz;

/**
 * <pre>
 * Java对象初始化
 *          这是一道阿里巴巴的关于Java对象初始化的面试题，堪称经典，代码很简单（编写格式做了些修改），
 *          但是需要面试者对Java中对象初始化有一个透彻的认识，那么通过这道面试题，对我有点启发，所以希望在这里分享给大家
 *          ，希望能给迷惘的初学者一起指引，下面我们直入主题，先看看代码：
 *          
 *          
 *          做些小补充，这也是很多Java学习者最初都能接触到的一些但是不是很理解的概念：
 * 核心理念：
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * 1.静态属性和静态代码块都是在类加载的时候初始化和执行，两者的优先级别是一致的，  
 *   且高于非静态成员，执行按照编码顺序。  
 * 2.非静态属性和匿名构造器在所有的构造方法之前执行，两者的优先级别一致，执行按照编码顺序。  
 * 3.以上执行完毕后执行构造方法中的代码。  
 * 读者仔细揣摩上面三条句子，也就是Java对象初始化的顺序，也就明白以上程序的输出结果为什么如下：
 * 1:j   i=0    n=0
 * 2:构造块   i=1    n=1
 * 3:t1   i=2    n=2
 * 4:j   i=3    n=3
 * 5:构造块   i=4    n=4
 * 6:t2   i=5    n=5
 * 7:i   i=6    n=6
 * 8:静态块   i=7    n=99
 * 9:j   i=8    n=100
 * 10:构造块   i=9    n=101
 * 11:init   i=10    n=102
 * 
 * 如果还是没有明白，就看下面详解，一下详解的顺序就是按照上文的核心理念的顺序来执行的（建议读者把自己带入JVN的世界里，跟着JVM一步一步往下面走）：
 * 1.运行main方法的时候，JVM会调用ClassLoader来加载InitializeDemo类，那么一起源于这次加载。
 * 2.上面有四个静态属性，所以会按顺序逐一初始化这四个静态属性。
 * 3.private static int k = 1; 此时将k初始化为1。
 * 4.private static InitializeDemo t1 = new InitializeDemo("t1");创建InitializeDemo对象，
 *   那么按照核心理念中的顺序，先执行private int j = print("j");，打印出j，然后执行构造
 *   块，最后执行构造方法。
 * 5.private static InitializeDemo t2 = new InitializeDemo("t2");同步骤4。
 * 6.private static int i = print("i");打印i。
 * 7.private static int n = 99;直到这一步，n才被赋值为99，之前是从默认的0开始++的。
 * 8.静态属性初始化完毕，代码走到静态块，打印出静态块，此时n=99。
 * 9.静态属性和静态块执行完毕，然后执行main方法中的代码new InitializeDemo("init");
 * 10.main方法中创建对象，先初始化非静态属性，private int j = print("j");打印j，
 *    然后执行构造块，最后执行构造方法。
 *    
 * 不知道我解说清楚了没有，只要把握住核心理念，碰到在复杂的问题也都不会怕了。
 * 用一个公式概括一下Java对象初始化执行优先级别：
 * （静态属性=静态代码块）> （非静态属性 = 构造块）> 构造方法
 * 总结一下核心理念：
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * 1.静态只在类加载的时候执行，且执行一次。  
 * 2.非静态只在实例化的时候执行，且每次实例化都执行。  
 * 3.静态在非静态之前执行。  
 * 4.静态属性和静态块的执行顺序取决于编码顺序，对它们一视同仁。  
 * 5.非静态属性和构造块的执行顺取决于编码顺序，对它们也一视同仁。  
 * 6.最后执行构造方法。  
 * 上面的总结有点绕对吧，问题进一步简化的话，就更好理解了：
 * 读者将静态代码块视为一个静态属性，将构造块视为一个非静态属性，那么问题简化到了这种路线“静态属性-->非静态属性-->构造方法“
 * 
 * 
 * Java 提供两种不同的类型：引用类型和原始类型（或内置类型）。Int是java的原始数据类型，Integer是java为int提供的封装类。Java为每个原始类型提供了封装类。
 * 
 * 引用类型和原始类型的行为完全不同，并且它们具有不同的语义。引用类型和原始类型具有不同的特征和用法，
 * 它们包括：
 * 大小和速度问题，这种类型以哪种类型的数据结构存储，
 * 当引用类型和原始类型用作某个类的实例数据时所指定的缺省值。
 * 对象引用实例变量的缺省值为 null，而原始类型实例变量的缺省值与它们的类型有关。
 * 
 * 不积跬步无以至千里，祝愿大家学习进步，乐于分享~ ~
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class InitializeDemo {
	private static int k = 1;
	private static InitializeDemo t1 = new InitializeDemo("t1");
	private static InitializeDemo t2 = new InitializeDemo("t2");
	private static int i = print("i");
	private static int n = 99;
	static {
		print("静态块");
	}
	private int j = print("j");
	{
		print("构造块");
	}

	public InitializeDemo(String str) {
		System.out.println((k++) + ":" + str + "   i=" + i + "    n=" + n);
		++i;
		++n;
	}

	public static int print(String str) {
		System.out.println((k++) + ":" + str + "   i=" + i + "    n=" + n);
		++n;
		return ++i;
	}

	public static void main(String args[]) {
		new InitializeDemo("init");
	}
}