package com.easyway.java.basic.strings;

import java.math.BigInteger;

/**
 * 变态java面试题一览
 * 
 * <pre>
 * 标签：Java面试题
 * 
 * 
 *  1。变态指数 4
 * int x=4;
 * System.out.println(“value is ” +((x>4)?99.9:9));
 * 答案 9.0 问号表达式的后面两个条件有要求,因为前面的是float,所以后面转为float.
 * 估计出题者才通过SCJP的考试。
 * 2.变态指数 5
 * public class Test {
 * 
 * public static void main(String[] args) {
 * int x = 4;
 * java.util.Date date = (x > 4) ? new A() : new B();
 * }
 * }
 * 
 * class A extends java.util.Date {}
 * class B extends java.util.Date {}
 * 答案 jdk1.4编译不通过,1.5可以
 * 不知道出题人的意图
 * 3.变态指数 6
 * String s=new String(“abc”);
 * 创建了几个Ｓtring对象?
 * 答案 2个
 * 这样的公司最好不要去
 * 
 * 4.变态指数 7
 * const是不是java的关键字?
 * 答案 const是java的关键字,但是java没有实现它
 * 一般人绝对用不到它
 * 
 * 5.变态指数 8
 * ，short s1 = 1; s1 = s1 + 1;有什么错? short s1 = 1; s1 += 1;有什么错?
 * 答案 1错2对，1因为向上转型了,最后导致类型不匹配错误 ,
 * 因为s1的+=是一个操作符，能够自动转型,
 * short s1 = 1;
 * s1 = s1+1;这句话在c++里面可以的
 * 不知道出题人的意图
 * 6.变态指数 9
 * 上海贝尔的面试题:你认为效率最高的方法,实现从1加到100.
 * 答案 1-100的累加相当于加50次101，这样循环次数从100次降为50次：
 * int sun = 0
 * for(int i = 1,j = 100 ; i <= 50 ; i++,j–){
 * sun = sun + i + j;
 * }
 * 出题人脑子有问题,直接(1+100)*50不是最快…其实类似这样的优化应该不是程序员考虑的范畴吧
 * 7.变态指数 10
 * System.out.println(5.0942*1000);
 * System.out.println(5.0943*1000);
 * System.out.println(5.0944*1000);的结果
 * 答案 :5094.2 5094.299999999999 5094.400000000001
 * 原理和浮点数的计算机表示方式有关 ,你不用上机,就答对了,你最好去微软,接替安德尔森.
 * 
 * 
 * 同步和异步的概念。
 * 缓存是什么，他的用途是什么。
 * 介绍你熟悉的设计模式。设计模式的作用?什么场合应用设计模式?
 * tcp/ip协议简介。
 * 静态方法，构造方法，成员变量的加载顺序。
 * 举例内部类的使用情景
 * 简述对垃圾回收机制的理解，介绍最少一种进行垃圾回收的方法。
 * 内存溢出和内存泄露是什么?
 * jvm工作原理。
 * java的工作内存模型简介
 * 线程安全是什么意思
 * hash code是什么
 * 写一个最简单的数据结构，可以存储100000000000000这样的数据
 * 举例数据库优化方法
 * 索引的概念和索引的种类。
 * 
 * 6．编程：实现将文件A.txt按“被叫号码”筛选出来联通、移动、电信的内容，并分别保存成B.txt（联通），C.txt（移动），E.txt（电信）。
 * 文件A.txt的每一行包括5个域，分别为：序号、发送时间、主叫号码、被叫号码、发送内容，每个域之间用TAB键“\t”分隔，生成的目标文件格式和文件A.txt一致。
 * （注：以130~133、153开头的11位号码为联通手机号码，以134~135、159开头的11位号码为移动手机号码，以0或1060开头的号码为电信小灵通号码）
 * 
 * 7．编程：设计2个线程分别实现对一个容量为10的队列的入队和出队操作。
 * 
 * 8．编程：用JAVA SOCKET编程，服务端接收客户端提交的字符串，并判断字符串中出现“*”的个数。
 * 
 * 9．将50万个手机用户数据保存于文件中，每个用户的数据对应一个文件夹，请描述文件目录应该如何构造，以及如何访问用户数据。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class ProblemString {
	public static void main(String[] args) {
		int x = 4;
		System.out.println("value is " + ((x > 4) ? 99.9 : 9));
		System.out.println(5.0942 * 1000);
		System.out.println(5.0943 * 1000);
		System.out.println(5.0944 * 1000);
		
		BigInteger i=new BigInteger("100000000000000000000000000000000000");
		System.out.println("i="+i);
	}

}
