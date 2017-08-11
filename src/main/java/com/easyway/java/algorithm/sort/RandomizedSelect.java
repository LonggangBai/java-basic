package com.easyway.java.algorithm.sort;

public class RandomizedSelect {
	/*
	 * 以快排为模型，以第一个数为基准对数组进行划分，找到第一个数的正确位置 比较a[low]=pivot 值得位置与 k
	 * 的大小，确定继续在左右还是右边递归查找 平均时间复杂度为 O(n)
	 */

	public static int quickSearch(int[] array, int left, int right, int k) {
		// left,right 位下标，k 为数字：第 k 小
		int low = left;
		int high = right;
		if (low == high) {
			if (low == k - 1)
				return array[low];
			else
				return -1;
		} else {
			int pivot = array[low]; // 以第一个元素为基准，对数组元素进行划分
			while (low < high) {
				while (low < high && pivot < array[high])
					high--;
				array[low] = array[high];
				while (low < high && pivot > array[low])
					low++;
				array[high] = array[low];
			}
			if (low == k - 1) { // low 是第k 小的元素
				return array[low];
			} else if (low < k - 1) {
				left = low + 1;
			} else if (low > k - 1) {
				right = low - 1;
			}
			return quickSearch(array, left, right, k);
		}
	}
}
