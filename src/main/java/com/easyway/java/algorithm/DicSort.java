package com.easyway.java.algorithm;
/**
 * n位数字的全排列共有n!种。 
本排列只对字符型数字排列进行输出，输出的是字符型数字。这种问题一般都需要用递归的方法。
 * @author longgangbai
 *
 */
public class DicSort {
	static int k = 0;

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 5 };
		permutations(a, 0, 4);
	}

	public static void permutations(int[] a, int m, int n) {
		if (m == n) {
			k++;
			System.out.print(k + "个:");
			for (int i = 0; i <= n; i++) {
				System.out.print(a[i]);
			}
			System.out.println();
		} else {
			for (int i = m; i <= n; i++) {
				int temp = a[m];
				a[m] = a[i];
				a[i] = temp;
				permutations(a, m + 1, n);
				temp = a[m];
				a[m] = a[i];
				a[i] = temp;
			}
		}
	}
}