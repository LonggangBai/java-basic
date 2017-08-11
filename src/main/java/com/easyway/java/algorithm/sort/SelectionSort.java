package com.easyway.java.algorithm.sort;

/**
 * 简单选择排序：每次选择一个当前最小的数字放在最终位置上。
 * @author treeroot
 * @since 2006-2-2
 * @version 1.0
 */
public class SelectionSort implements SortUtil.Sort {

    /*
     * (non-Javadoc)
     *
     * @see org.rut.util.algorithm.SortUtil.Sort#sort(int[])
     */
    public void sort(int[] data) {
        int temp;
        for (int i = 0; i < data.length; i++) {
            int lowIndex = i;
            for (int j = data.length-1; j > i; j--) {
                if (data[j] < data[lowIndex]) {
                    lowIndex = j;
                }
            }
            SortUtil.swap(data,i,lowIndex);
        }
    }

}