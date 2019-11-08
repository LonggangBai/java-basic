package com.easyway.change;

import java.util.Arrays;
import java.util.Scanner;

public class FindMin {

    private static final int MAX_VALUE = Integer.MAX_VALUE - 2;

    public static int coinChange(int[] coins, int change) {
        int[] seq = new int[change + 1];
        int[] sum = new int[change + 1];
        for (int i = 1; i <= change; i++) {
            sum[i] = MAX_VALUE;
        }
        sum[0] = 0;
        for (int i = 1; i < change + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && (1 + sum[i - coins[j]]) < sum[i]) {
                    sum[i] = 1 + sum[i - coins[j]];
                    seq[i] = j;
                }
            }
        }
        int j = change;
        while (j > 0) {
            System.out.print(coins[seq[j]] + "\t");
            j = j - coins[seq[j]];
        }
        System.out.println();
        return sum[change];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] coins = {2, 3, 4};
        System.out.println(String.format("coins is %s", Arrays.toString(coins)));
        while (in.hasNext()) {
            int change = in.nextInt();
            if (change == 0) {
                break;
            }
            int coinNumber = coinChange(coins, change);
            System.out.println(coinNumber == MAX_VALUE ? 0 : coinNumber);
        }
    }

}
