package com.easyway.java.algorithm.sort;

public class BinarySearchNonRec {
	//非递归算法：
	public static int binarySearch_NonRec (int []a , int left, int right, int d){ //left,right均为小标
	         while (left <right ){ // 利用 while循环改变查找的范围，通过改变 left 和right 大小
	             int mid =(left +right )/2;
	             if( d== a[ mid]){
	                 return mid ;
	            } else if (d <a [mid ]){
	                 right= mid-1;
	            } else {
	                 left= mid+1;
	            }
	        }
	         if( left== right&& d== a[ left]){
	             return left ;
	        }
	         return -1;      
	    }
}
