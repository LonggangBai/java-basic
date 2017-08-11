package com.easyway.java.algorithm.sort;

import java.util.LinkedList;
/**
 * 基数排序
 * <pre>
 * 基数排序：需要借助radix个队列来暂时存储元素。 
思想： 
1.new一个元素为LinkedList的queue，用来存放各个基数的队列； 
2.新建radix个元素为整数的queue1（命名一样），均存放在queue中； 
3.根据最大元素的位数，对数组进行max_digits次分配和收集； 
4.从低位到高位，计算data[j]的该位上的值r，放在queue中的第r个LinkedList中（需要借助临时的Linkedlist表queue2）； 
5.根据每一位的分配完成后，将LinkedListqueue中的数据从前到后收集起来一次（即对数组进行了一次排序）。
 * </pre>
 * @author longgangbai
 *
 */
public class RadixSort {
	public static void radixSort (int[]data,int radix,int digits){ //radix表示基数（进制），digits表示最大数值位数 
	          LinkedList<LinkedList> queue= new LinkedList<LinkedList>();
	           for(int r =0;r <radix ;r ++){
	              LinkedList<Integer> queue1= new LinkedList<Integer> (); 
	               queue.offer( queue1);   //offer用于queue中（Linkedlist同时实现了List和queue接口），add用于List中
	          }
	           //最大元素的位数,进行digits次分配和收集
	           for(int i =0;i <digits ;i ++){
	               //分配数组元素
	               for(int j =0;j <data .length;j ++){
	                    //得到digits的第i+1位
	                    int r =(int)(data[j]%Math. pow(radix , i+1)/Math.pow( radix, i));
	                   LinkedList<Integer> queue2=queue .get(r );
	                    queue2.offer( data[ j]);
	                    queue.set( r, queue2);
	              }
	               //将收集队列元素
	               int count =0;
	               for(int k =0;k <radix ;k ++){
	                    while(queue .get(k ).size()>0){
	                         data[ count++]=(Integer) queue.get( k).poll();
	                   }
	              }
	          }
	     }
}