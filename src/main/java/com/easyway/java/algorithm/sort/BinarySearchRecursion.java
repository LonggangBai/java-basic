package com.easyway.java.algorithm.sort;

/**
 * 二分查找的前提是数组必须是已经排好序的，才能对中位数进行比较后选择在哪边查找。
 * 
 * @author longgangbai
 *
 */
public class BinarySearchRecursion {

	// 递归查找（recursion）：
	// 二分查找的前提是数组必须是已经排好序的，才能对中位数进行比较后选择在哪边查找
	public static int binarySearch_Recursion(int[] a, int left, int right, int d) { // left,right均为下标，
																					// d
																					// 为数值
		if (left == right && d == a[left]) {
			return left + 1;
		}
		if (left < right) {
			int mid = (left + right) / 2;
			if (d == a[mid]) { // 中位数是需要查找的值，则返回其位置，否则递归在左或者右分区查找
				return mid + 1;
			} else if (d < a[mid]) {
				return binarySearch_Recursion(a, left, mid - 1, d);
			} else {
				return binarySearch_Recursion(a, mid + 1, right, d);
			}
		}
		return -1;
	}
}