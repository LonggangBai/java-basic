/**
 * Project Name:java-basic
 * File Name:ArrayFinder.java
 * Package Name:com.easyway.java.algorithm
 * Date:2015-3-24下午2:33:06
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.algorithm;
/**
 * ClassName:ArrayFinder <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-24 下午2:33:06 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ArrayFinder {

    public static void print(int[] array,int middle){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
            if(i==middle){
                System.out.print("*");
            }
            System.out.println(" ");
        }
    }
    
    public static int indexOf(int[] array,int value){
        int low=0;
        int high=array.length-1;
        int middle=-1;
        while(low<high){
            middle=(low+high)/2;
            print(array,middle);
            if(array[middle]==value){
                return middle;
            }
            if(value<array[middle]){
                high=middle;
            }else{
                low=middle;
            }
        }
        return middle;
    }
    
    public static void main(String[] args){
        int[] array={4,5,6,7,8,9,19,89};
        System.out.println("location of 19 : indexof "+indexOf(array,19));
    }
}

