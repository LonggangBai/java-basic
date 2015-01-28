package com.easyway.java.basic.strings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * <pre>
 * Java中的堆空间是什么?如何增加Java堆空间
 * 
 * Java中的堆空间是什么?
 * 当Java程序开始运行时，JVM会从操作系统获取一些内存。JVM使用这些内存，这些内存的一部分就是堆内存。堆内存通常在存储地址的底层，向上排列。
 * 当一个对象通过new关键字或通过其他方式创建后，对象从堆中获得内存。当对象不再使用了，被当做垃圾回收掉后，这些内存又重新回到堆内存中。要学
 * 习垃圾回收，请阅读”Java中垃圾回收的工作原理”。
 * 如何增加Java堆空间
 * 在大多数32位机、Sun的JVM上，Java的堆空间默认的大小为128MB，但也有例外，例如在32未Solaris操作系统(SPARC平台版本)上，默认的最大堆
 * 空间和起始堆空间大小为 -Xms=3670K 和 -Xmx=64M。对于64位操作系统，一般堆空间大小增加约30%。但你使用Java 1.5的throughput垃圾回收
 * 器，默认最大的堆大小为物理内存的四分之一，而起始堆大小为物理内存的十六分之一。要想知道默认的堆大小的方法，可以用默认的设置参数打开一个程序，
 * 使用JConsole(JDK 1.5之后都支持)来查看，在VM Summary页面可以看到最大的堆大小。
 * 用这种方法你可以根据你的程序的需要来改变堆内存大小，我强烈建议采用这种方法而不是默认值。如果你的程序很大，有很多对象需要被创建的话，你可以
 * 用-Xms and -Xmx这两个参数来改变堆内存的大小。Xms表示起始的堆内存大小，Xmx表示最大的堆内存的大小。另外有一个参数 -Xmn，它表示
 * new generation（后面会提到）的大小。有一件事你需要注意，你不能任意改变堆内存的大小，你只能在启动JVM时设定它。
 * 
 * 
 * 
 * 
 * 当需要排序的集合或数组不是单纯的数字型时，通常可以使用Comparator或Comparable，以简单的方式实现对象排序或自定
 * 
 * 一、Comparator
 * 
 * 强行对某个对象collection进行整体排序的比较函数，可以将Comparator传递给Collections.sort或Arrays.sort。
 * 
 * 接口方法：
 * 
 * [html] view plaincopy在CODE上查看代码片派生到我的代码片
 * @return o1小于、等于或大于o2，分别返回负整数、零或正整数。  
 *  int compare(Object o1, Object o2);
 *  
 *  一个类实现了Camparable接口则表明这个类的对象之间是可以相互比较的，这个类对象组成的集合就可以直接使用sort方法排序。
 * Comparator可以看成一种算法的实现，将算法和数据分离，Comparator也可以在下面两种环境下使用：
 * 1、类的设计师没有考虑到比较问题而没有实现Comparable，可以通过Comparator来实现排序而不必改变对象本身
 * 2、可以使用多种排序标准，比如升序、降序等
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class SampleComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		return toInt(o1) - toInt(o2);
	}

	private int toInt(Object o) {
		String str = (String) o;
		str = str.replaceAll("一", "1");
		str = str.replaceAll("二", "2");
		str = str.replaceAll("三", "3");
		//
		return Integer.parseInt(str);
	}

	/**
	 * 测试方法
	 */
	public static void main(String[] args) {
		String[] array = new String[] { "一二", "三", "二" };
		Arrays.sort(array, new SampleComparator());
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}