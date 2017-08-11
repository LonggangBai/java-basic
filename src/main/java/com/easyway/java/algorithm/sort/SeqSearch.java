package com.easyway.java.algorithm.sort;
/**
 * 对给定数组（字符串）顺序进行查找元素是否存在。
 * @author longgangbai
 *
 */
public class SeqSearch {
    public static void main(String args[]){
              int [] array={32,5,67,6,1,7};
              int data=6;
             System. out .println("Location=" +seqSearch( array, data));
   }
    public static int seqSearch( int[] a, int d){
              for( int i=0; i< a.length; i++){
                       if( d== a[ i]){
                                return i+1;
                      }
             }
              return -1;
   }
}