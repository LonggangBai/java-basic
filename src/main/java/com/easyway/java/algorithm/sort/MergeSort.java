package com.easyway.java.algorithm.sort;


/**
 * <pre>
 * 归并有递归和非递归两种。

归并的思想是： 
1.将原数组首先进行两个元素为一组的排序，然后合并为四个一组，八个一组，直至合并整个数组； 
2.合并两个子数组的时候，需要借助一个临时数组，用来存放当前的归并后的两个数组； 
3.将临时数组复制回原数组对应的位置。

非递归的代码如下：

 * @author treeroot
 * @since 2006-2-2
 * @version 1.0
 */
public class MergeSort implements SortUtil.Sort{

    /* (non-Javadoc)
     * @see org.rut.util.algorithm.SortUtil.Sort#sort(int[])
     */
    public void sort(int[] data) {
        int[] temp=new int[data.length];
        mergeSort(data,temp,0,data.length-1);
    }
   
    private void mergeSort(int[] data,int[] temp,int l,int r){
        int mid=(l+r)/2;
        if(l==r) return ;
        mergeSort(data,temp,l,mid);
        mergeSort(data,temp,mid+1,r);
        for(int i=l;i<=r;i++){
            temp[i]=data[i];
        }
        int i1=l;
        int i2=mid+1;
        for(int cur=l;cur<=r;cur++){
            if(i1==mid+1)
                data[cur]=temp[i2++];
            else if(i2>r)
                data[cur]=temp[i1++];
            else if(temp[i1]<temp[i2])
                data[cur]=temp[i1++];
            else
                data[cur]=temp[i2++];          
        }
    }

    
    public void mergeSort(int[] a){
        int len = 1;
        while(len < a.length){
            for(int i = 0; i < a.length; i += 2*len){
                merge(a, i, len);
            }
            len *= 2;
        }
    }

    public void merge(int[] a, int i, int len){
        int start = i;
        int len_i = i + len;//归并的前半部分数组
        int j = i + len;
        int len_j = j +len;//归并的后半部分数组
        int[] temp = new int[2*len];
        int count = 0;
        while(i < len_i && j < len_j && j < a.length){
            if(a[i] <= a[j]){
                temp[count++] = a[i++];
            }
            else{
                temp[count++] = a[j++];
            }
        }
        while(i < len_i && i < a.length){//注意：这里i也有可能超过数组长度
            temp[count++] = a[i++];
        }
        while(j < len_j && j < a.length){
            temp[count++] = a[j++];
        }
        count = 0;
        while(start < j && start < a.length){
            a[start++] = temp[count++];
        }
    }
}
