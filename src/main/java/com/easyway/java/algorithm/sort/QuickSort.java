package com.easyway.java.algorithm.sort;



/**
 * 快排是最重要，使用最多的一种排序方法，其思想应用在了很多其他的快速查找等算法里。
主要思想是： 
1.把数组的第一个元素作为基准（pivot），将数组划分； 
2.比较要查找的值与pivot的大小，确定在左半边还是右半边进行递归查找
 * @author treeroot
 * @since 2006-2-2
 * @version 1.0
 */
public class QuickSort implements SortUtil.Sort{

    /* (non-Javadoc)
     * @see org.rut.util.algorithm.SortUtil.Sort#sort(int[])
     */
    public void sort(int[] data) {
        quickSort(data,0,data.length-1);      
    }
    private void quickSort(int[] data,int i,int j){
        int pivotIndex=(i+j)/2;
        //swap
        SortUtil.swap(data,pivotIndex,j);
       
        int k=partition(data,i-1,j,data[j]);
        SortUtil.swap(data,k,j);
        if((k-i)>1) quickSort(data,i,k-1);
        if((j-k)>1) quickSort(data,k+1,j);
       
    }
    /**
     * @param data
     * @param i
     * @param j
     * @return
     */
    private int partition(int[] data, int l, int r,int pivot) {
        do{
           while(data[++l]<pivot);
           while((r!=0)&&data[--r]>pivot);
           SortUtil.swap(data,l,r);
        }
        while(l<r);
        SortUtil.swap(data,l,r);      
        return l;
    }

}
