package com.easyway.java.algorithm.sort;

public class MergeSort1 {

    public static void mergeSort(int[] data,int left,int right){ //left,right均为数字元素下标
        if(left<right){
            int half=(left+right)/2;
            mergeSort(data,left,half);
            mergeSort(data,half+1,right);
            merge(data,left,right);
        }
    }
    public static void merge(int []a,int l,int h){
        int mid=(l+h)/2;
        int i=l;
        int j=mid+1;
        int count=0;
        int temp[]=new int[h-l+1];
        while(i<=mid&&j<=h){
            if(a[i]<a[j]){
                temp[count++]=a[i++];
            }else{
                temp[count++]=a[j++];
            }        
        }
        while(i<=mid){
            temp[count++]=a[i++];
        }
        while(j<=h){
            temp[count++]=a[j++];
        }
        count=0;
        while(l<=h){
            a[l++]=temp[count++];
        }
    }
    public static void printArray(int arr[]){
        for(int k=0;k<arr.length;k++){
            System.out.print(arr[k]+"\t");
        }
    }
    public static int[] getArray(){
//      int[] data={4,2,3,1};
        int[] data={543,23,45,65,76,1,456,7,77,88,3,9};
        return data;
    }

    public static void main(String args[]){
        int[]a=getArray();
        System.out.print("数组排序前：");
        printArray(a);
        System.out.print("\n");
        mergeSort(a,0,a.length-1);
        System.out.print("归并排序后：");
        printArray(a);
    }
}