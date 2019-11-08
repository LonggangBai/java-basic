package com.easyway.change;

import java.util.Arrays;
/**
 *
 * @since 2017-10-16
 * @author niaonao
 *https://blog.csdn.net/niaonao/article/details/78249256#
 */
public class ChargeProblem {

    /**
     * 通过面值为 coinsValues[i] 的硬币对金额 chargeMoney 找零
     * @param coinsValues 硬币面值coinsValues[i],硬币面值种类数量coinsValues.length
     * @param chargeMoney 找零金额
     * @return 最小找零硬币数目
     */
    public static int charge(int[] coinsValues, int chargeMoney){
        int coinsKinds = coinsValues.length;
        int[][] chargeOptimalSolution = new int[coinsKinds + 1][chargeMoney + 1];

        //当找零金额为 0 时，不需要找零，最少找零硬币数量为 0
        for(int i = 0; i <= coinsKinds; i++)
            chargeOptimalSolution[i][0] = 0;

        //当找零金额不为 0 时，找零硬币种类不可为 0
        for(int i = 0; i <= chargeMoney; i++)
            chargeOptimalSolution[0][i] = Integer.MAX_VALUE;

        //money 找零金额; coinKind 硬币种类，用来表示第几种硬币
        for(int money = 1; money <=chargeMoney; money++){
            for(int coinKind = 1; coinKind <= coinsKinds; coinKind++){

                //找零金额小于当前硬币面值
                if(money < coinsValues[coinKind-1]){
                    chargeOptimalSolution[coinKind][money] = chargeOptimalSolution[coinKind - 1][money];
                    continue;
                }

                //不使用第 i(coinkind) 种硬币找零时需要的最小硬币数-- 递推 --
                //使用第 i(coinkind) 种硬币时所需的最小硬币数-- 递推 --
                int numberByCoinKind = chargeOptimalSolution[coinKind - 1][money];
                int numberNotByCoinKind = chargeOptimalSolution[coinKind][money - coinsValues[coinKind-1]] + 1;

                //逻辑判断硬币数目选其中较小的
                chargeOptimalSolution[coinKind][money] =
                        numberByCoinKind < numberNotByCoinKind ? numberByCoinKind : numberNotByCoinKind;
            }
        }

        return chargeOptimalSolution[coinsKinds][chargeMoney];
    }

    public static void main(String[] args) {
        //初始化硬币种类数组
        int[] coinsValues = {1,2,5,10,20,50,100};
        Arrays.sort(coinsValues);
        //初始化找零金额为625
        int chargeMoney = 625;
        int minCoinsNumber = charge(coinsValues, chargeMoney);
        System.out.println("给定找零金额" + chargeMoney
                + ",收银员最少的找零硬币数为" + minCoinsNumber);
    }
}
