package com.easyway.java.algorithm.sort;

/**
 * @author treeroot
 * @since 2006-2-2
 * @version 1.0
 */
public class BubbleSort implements SortUtil.Sort{

    /* (non-Javadoc)
     * @see org.rut.util.algorithm.SortUtil.Sort#sort(int[])
     */
    public void sort(int[] data) {
        int temp;
        for(int i=0;i<data.length;i++){
            for(int j=data.length-1;j>i;j--){
                if(data[j]<data[j-1]){
                    SortUtil.swap(data,j,j-1);
                }
            }
        }
    }

}