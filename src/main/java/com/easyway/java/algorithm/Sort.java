package com.easyway.java.algorithm;

public class Sort

{

	public static void main(String[] args)

	{

		int[] array = { 2, 3, 4, 1, 43, 432, 1, 344, 234, 2, 3, 43, 32, 434,
				3432, 43, 2432, 432, 4, 43 };

		int num = 0;

		/*
		 * 
		 * 冒泡排续
		 */

		for (int i = 0; i < array.length; i++)

		{

			/*
			 * 
			 * 每下底一个元素，则调换的次数减一。
			 * 
			 * 注意：j<array.length-i-1,不减一则数组下标越界。
			 */

			for (int j = 0; j < array.length - i - 1; j++)

			{

				int temp = 0;

				/*
				 * 
				 * 两两比较若前面的大于后面的则进行调换。
				 */

				if (array[j] > array[j + 1])

				{

					temp = array[j];

					array[j] = array[j + 1];

					array[j + 1] = temp;

				}

			}

		}

		/*
		 * 
		 * 输出排序后的数组
		 */

		System.out.println("****************已排序的数组********");

		for (int i = 0; i < array.length; i++)

		{

			System.out.print(array[i] + "");

			num++;

			if (num == 5)// 每行输出5个。

			{

				System.out.println();

				num = 0;

			}

		}

	}

}