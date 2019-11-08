package com.easyway.change;

/**
 *
 * <pre>
 * 硬币找零(leetcode322)
 *
 * 给定一组硬币数，找出一组最少的硬币数，来找换零钱N。
 *
 * 比如，可用来找零的硬币为: 1、3、4   待找的钱数为 6。用两个面值为3的硬币找零，最少硬币数为2。而不是 4，1，1
 * </pre>
 */
public class ChangeSolution {

    /**
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {

        if(amount <= 0){
            return 0;
        }
        if(coins.length == 0 ){
            return -1;
        }

        //dp[i]:凑成i(前j的硬币中)元所需的最少硬币数
        int[] dp = new int[amount+1];
        for(int i = 1; i<dp.length; i++)
        {
            dp[i] = Integer.MAX_VALUE;
            for(int j =0; j<coins.length; j++)
            {
                //dp[i - nums[kind]] != Integer.MAX_VALUE保证dp[i - nums[kind]]是可以凑出来的
                if(coins[j]<=i && dp[i - coins[j]] != Integer.MAX_VALUE)
                {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]]+1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE? -1 : dp[amount];
    }

}
