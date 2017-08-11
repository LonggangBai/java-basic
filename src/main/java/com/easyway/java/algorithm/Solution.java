package com.easyway.java.algorithm;

/**
 * 当前位置往前进行枚举法
 * 求直方图中的最大矩形面积： 
例如给定直方图{2,3,1,2,4,2} 
则直方图中最大矩形面积为x=(3,6),|x|=3,y=2，max面积=6 
思考：利用枚举法
 * */
public class Solution {
	static int histogramMaxArea(int[] a) {
		int maxS = a[0];
		for (int i = 0; i < a.length; i++) { // 直方图中依次向后枚举
			int min = a[i]; // 记录当前条图及之前最小值
			int m = 0; // 记录底部边长
			for (int j = i; j >= 0; j--) { // 依次向前取最大矩形
				m++;
				if (a[j] < min) {
					min = a[j];
				}
				int s = m * min; // 矩形面积计算
				if (s > maxS) {
					maxS = s;
				}
			}
		}
		return maxS;
	}

	public static void main(String args[]) {
		int a[] = { 2, 1, 1, 2 };
		int maxArea = histogramMaxArea(a);
		System.out.print(maxArea);
	}
}