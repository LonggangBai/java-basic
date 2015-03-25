/**
 * Project Name:java-basic
 * File Name:ArraySorter.java
 * Package Name:com.easyway.java.algorithm
 * Date:2015-3-24下午2:23:56
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.algorithm;

/**
 * ClassName:ArraySorter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-24 下午2:23:56 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ArraySorter {
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {

            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            print(i + 1, array);
        }
    }


    public static void print(int time, int[] array) {
        System.out.println("第 " + time + " 趟排序");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] array = { 4, 7, 3, 9, 2 };
        bubbleSort(array);
    }
}
