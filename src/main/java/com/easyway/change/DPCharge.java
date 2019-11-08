package com.easyway.change;

import java.util.Arrays;

public class DPCharge {

    /**
     *
     * @param coinsValues 可用来找零的硬币 coinsValues.length是硬币的种类
     * @param n 待找的零钱
     * @return
     */
    public static int charge(int[] coinsValues, int n){
        int[][] c = new int[coinsValues.length + 1][n + 1];

        //特殊情况1
        for(int i = 0; i <= coinsValues.length; i++)
            c[i][0] = 0;
        for(int i = 0; i <= n; i++)
            c[0][i] = Integer.MAX_VALUE;

        for(int j_money = 1; j_money <=n; j_money++)
        {
            for(int i_coinKinds = 1; i_coinKinds <= coinsValues.length; i_coinKinds++)
            {
                if(j_money < coinsValues[i_coinKinds-1])//特殊情况2
                {
                    c[i_coinKinds][j_money] = c[i_coinKinds - 1][j_money];
                    continue;
                }

                //每个问题的选择数目---选其中较小的
                if(c[i_coinKinds - 1][j_money] < (c[i_coinKinds][j_money - coinsValues[i_coinKinds-1]] +1))
                    c[i_coinKinds][j_money] = c[i_coinKinds - 1][j_money];
                else
                    c[i_coinKinds][j_money] = c[i_coinKinds][j_money - coinsValues[i_coinKinds-1]] +1;
            }
        }
        return c[coinsValues.length][n];
    }

    public static void main(String[] args) {
        int[] coinsValues = {1,3,4};
        Arrays.sort(coinsValues);//需要对数组排序,不然会越界.....
        int n = 6;
        int minCoinsNumber = charge(coinsValues, n);
        System.out.println(minCoinsNumber);
    }
}