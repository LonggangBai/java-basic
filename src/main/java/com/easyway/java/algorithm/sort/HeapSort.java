package com.easyway.java.algorithm.sort;



/**
 * 堆排序的思想是： 
1.比较第i个位置与第2i和2i+1个位置上的节点值，将较大的元素上移； 
2.重复1，使最大的元素移到根节点； 
3.将根节点与最末的元素交换位置，递归1,2,3使剩下的n-1个元素也依次移到当前最后。

 * 堆排序：
 * @author treeroot
 * @since 2006-2-2
 * @version 1.0
 */
public class HeapSort implements SortUtil.Sort{

    /* (non-Javadoc)
     * @see org.rut.util.algorithm.SortUtil.Sort#sort(int[])
     */
    public void sort(int[] data) {
        MaxHeap h=new MaxHeap();
        h.init(data);
        for(int i=0;i<data.length;i++)
            h.remove();
        System.arraycopy(h.queue,1,data,0,data.length);
    }

     private static class MaxHeap{        
       
        void init(int[] data){
            this.queue=new int[data.length+1];
            for(int i=0;i<data.length;i++){
                queue[++size]=data[i];
                fixUp(size);
            }
        }
       
        private int size=0;

        private int[] queue;
               
        public int get() {
            return queue[1];
        }

        public void remove() {
            SortUtil.swap(queue,1,size--);
            fixDown(1);
        }
        //fixdown
        private void fixDown(int k) {
            int j;
            while ((j = k << 1) <= size) {
                if (j < size && queue[j]<queue[j+1])
                    j++;
                if (queue[k]>queue[j]) //不用交换
                    break;
                SortUtil.swap(queue,j,k);
                k = j;
            }
        }
        private void fixUp(int k) {
            while (k > 1) {
                int j = k >> 1;
                if (queue[j]>queue[k])
                    break;
                SortUtil.swap(queue,j,k);
                k = j;
            }
        }

    }

}

