package com.easyway.java.basic.strings;

import java.util.StringTokenizer;

import java.util.Arrays;
/**
 * String 和StringBuffer的区别
 * <pre>
 * JAVA平台提供了两个类：String和StringBuffer，它们可以储存和操作字符串，即包含多个字符的字符数据。
 * 这个String类提供了数 值不可改变的字符串。而这个StringBuffer类提供的字符串进行修改。当你知道字符数
 * 据要改变的时候你就可以使用StringBuffer。典型 地，你可以使用StringBuffers来动态构造字符数据。
 * </pre>
 * @author Administrator
 *
 */
public class StringTokenizerSplit {

	public static String reverse(String s) {
		int length = s.length();
		StringBuffer result = new StringBuffer(length);
		for (int i = length - 1; i >= 0; i--) {
			result.append(s.charAt(i));
		}
		return result.toString();
	}

	public static void main(String[] args)

	{

		String s = "2,654,24,6554,3234,3544,666,354,5435,11";

		StringTokenizer tok = new StringTokenizer(s, ",");

		// 返回有多少个被分割元素

		int count = tok.countTokens();

		int[] result = new int[count];

		// 把分割得到的数字存到数组中去。

		for (int i = 0; i < count; i++)

		{

			String temp = (String) tok.nextElement();

			result[i] = Integer.parseInt(temp);

		}

		// 排序

		Arrays.sort(result);

		for (int i = 0; i < result.length; i++)

		{

			if (i == 0)

			{

				System.out.print(result[i]);

				continue;// 第一个元素的前面不打印“，”

			}

			System.out.print("," + result[i]);

		}

		System.out.println();// 输出：2,11,24,354,654,666,3234,3544,5435,6554

	}

}