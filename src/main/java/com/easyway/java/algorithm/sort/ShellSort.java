package com.easyway.java.algorithm.sort;


/**
 * 希尔排序（插入排序-渐减增量排序diminishing increment sort）： 
思想：1.将原始数组按照增量分解为多个数组，分别按插入排序调好子序列的顺序； 
2. 减小增量，重复第一步，直至增量为1。
 * @author treeroot
 * @since 2006-2-2
 * @version 1.0
 */
public class ShellSort implements SortUtil.Sort{

    /* (non-Javadoc)
     * @see org.rut.util.algorithm.SortUtil.Sort#sort(int[])
     */
    public void sort(int[] data) {
        for(int i=data.length/2;i>2;i/=2){
            for(int j=0;j<i;j++){
                insertSort(data,j,i);
            }
        }
        insertSort(data,0,1);
    }

    /**
     * @param data
     * @param j
     * @param i
     */
    private void insertSort(int[] data, int start, int inc) {
        int temp;
        for(int i=start+inc;i<data.length;i+=inc){
            for(int j=i;(j>=inc)&&(data[j]<data[j-inc]);j-=inc){
                SortUtil.swap(data,j,j-inc);
            }
        }
    }

}
