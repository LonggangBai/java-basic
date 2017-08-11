package com.easyway.java.algorithm.sort;

/**
 * 对给定数组（字符串）顺序进行查找元素是否存在。
 * 思想：按顺序依次将需要排序的数字插入已排序好的队列中。

将temp与d[j-1]比较，找到temp的位置，将插入位置之后的元组后移一位。
 * @author treeroot
 * @since 2006-2-2
 * @version 1.0
 */
public class InsertSort implements SortUtil.Sort{

    /* (non-Javadoc)
     * @see org.rut.util.algorithm.SortUtil.Sort#sort(int[])
     */
    public void sort(int[] data) {
        int temp;
        for(int i=1;i<data.length;i++){
            for(int j=i;(j>0)&&(data[j]<data[j-1]);j--){
                SortUtil.swap(data,j,j-1);
            }
        }      
    }
   

}