package com.easyway.change;

/**
 * <pre>
 * 等和拆分数组
 *
 *     判断能否将给定数组拆分成数组和相等的两个数组(leetcode416)
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * 思路：背包的变形题。可以看作是从数组中取数填满容积为sum/2的背包。如果可以填满则返回true，否则返回false。
 * </pre>
 */
public class CanPartition {


    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum % 2 != 0) {
            return false;
        }
        return helper(nums, nums.length - 1, sum / 2);
    }

    private boolean helper(int[] nums, int index, int c) {

        boolean[] dp = new boolean[c + 1];

        for (int i = 0; i <= c; i++) {
            dp[i] = (nums[0] == i);
        }
        //i需要从c递减，而不能从1递增。因为dp[]只记录T,F,递增就会数组中的数据就会重复选取了。
        for (int j = 1; j < nums.length; j++) {
            for (int i = c; i >= nums[j]; i--) {
                dp[i] = dp[i] || dp[i - nums[j]];
            }
        }
        return dp[c];
    }
}
