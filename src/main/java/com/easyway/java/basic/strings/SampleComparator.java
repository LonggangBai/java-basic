package com.easyway.java.basic.strings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * <pre>
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