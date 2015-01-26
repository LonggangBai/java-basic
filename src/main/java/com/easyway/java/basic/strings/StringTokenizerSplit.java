package com.easyway.java.basic.strings;

import java.util.StringTokenizer;

import java.util.Arrays;

public class StringTokenizerSplit
{

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