package com.easyway.java.algorithm;

/**
 * <pre>
 * 问题：要找K元的零钱，零钱的种类已知，保存在数组coins[]中，要求：求出构成K所需的最少硬币的数量和零钱的具体数值。 
分析：（1）贪心算法：，先从面额最大的硬币开始尝试，一直往下找，知道硬币总和为N。但是贪心算法不能保证能够找出解（例如，给,2,3,5,
然后N=11，导致无解5,5,1）。 
（2）动态规划： 
思想：快速实现递归（将前面计算的结果保存在数据里，后面重复用的时候直接调用就行，减少重复运算）
动态规划算法与分治法类似，其基本思想也是将待求解问题分解成若干个子问题，先求解子问题，然后从这些子问题的解得到原问题的解。
与分治法不同的是，适合于用动态规划求解的问题，经分解得到子问题往往不是互相独立的。若用分治法来解这类问题，则分解得到的子问题
数目太多，有些子问题被重复计算了很多次。如果我们能够保存已解决的子问题的答案，而在需要时再找出已求得的答案，这样就可以避免大
量的重复计算，节省时间。我们可以用一个表来记录所有已解的子问题的答案。不管该子问题以后是否被用到，只要它被计算过，就将其结果填入表中。 
 * </pre>
 * @author longgangbai
 *
 */
/*找零钱问题：要找 K元的零钱，零钱的种类有 coins[],要求零钱的张数最少，用 road[]来找出具体使用的零钱*/
public class MinCount_coins {
    public static void main (String[] args) { 
       int coins[]={3,5};
       int k=4;
       int road[]=new int[k+1];
       int min=getMinCount(k ,coins ,road );
       if(min>Integer. MAX_VALUE-k ){ //min 没有另外赋值，则表示零钱不够
        System.out. println( "零钱不够！" );
       }else{
        System.out. println( "数值为" +k +" 时，需要的最少的硬币数为： "+ min);
           for(int j=k;j>0;){
            System.out. print( road[ j]+ "\t");
            j=j-road[j];  //j为当前要找的零钱值， road[j]为当前面额下，最近加入的零钱
           }
       }
    } 

    public static int getMinCount (int k,int c[],int r[]){
     int a[]=new int[k+1];//保存最近加入的零钱值
     a [0]=0;
     for(int x=1;x<k+1;x++){ //要求a[k],先求a[1]~a[k-1]
         if(x>=c[0]){  //给a[x]附初值
             a[x]=a[x-c[0]]+1;
             r[x]=c[0];
         }else{   //要找零钱比最小零钱值还小，零钱不够
             a[x]=Integer.MAX_VALUE- k;
         }
         for(int i=1;i<c.length;i++){
             if(x>=c[i]&&(a[x]>a[x-c[i]]+1)){//x-c[i]表示当前值减去coins[]中值，即可由前面那些子问题+1一次得来
                  a[ x]= a[ x- c[ i]]+1;
                  r[ x]= c[ i];
             }
         }
     }
     return a[k];
    }
}