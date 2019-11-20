package com.easyway.change;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     1. 问题
 *
 * 如果硬币的面值是{1, 1*c, 2*c, …, k*c}， 则贪婪算法总是用最少的硬币找零。
 *
 *
 *
 * 如《离散数学及其应用》书中贪婪算法的反例：
 *
 * 有面值1, 10, 25的硬币，找零30。
 *
 * 贪婪算法的解：5c0 + 0c1 + 1c2 =  5*1 + 0*10 + 1*25 = 30，共需6枚硬币
 *
 * 而最优解是：0c0 + 3c1 + 0c2 =  0*1 + 3*10 + 0*25 = 30，只需3枚硬币
 *
 * 但如果补齐中间缺失的硬币面值：{1, 5, 10, 15, 25},
 *
 * 那么贪婪算法的解是 ： 1*25 + 1*5 = 30，只需2枚硬币，依然是最优解之一（因为还有15*2也是最优解）
 *
 *
 *
 * 2. 规律
 *
 * 因为最大的25面值的硬币不再满足《贪婪算法最优解问题》问题中的条件，所以无法用该证明方法证明，思路不通时我们先找找满足问题条件的找零方案，看能不能找出一些规律，然后再证明这些规律找出一些推论来间接证明
 *
 * 假设有面值{1, 3, 6, 9}的硬币，当需找零S的最优贪婪算法找零方案：
 *
 *  	1	3	6	9
 * 9	 	 	 	1
 * 10	1	 	 	1
 * 11	2	 	 	1
 * 12	 	1	 	1
 * 13	1	1	 	1
 * 14	2	1	 	1
 * 15	 	 	1	1
 * 16	1	 	1	1
 * 17	2	 	1	1
 * 18	 	 	 	2
 *
 * 从表中可以看出：
 *
 * 1> 除了最大面值和1面值的硬币以外，其他面值的硬币最多只有1个
 *
 * 2> 1面值的硬币最多只有2个
 *
 *
 *
 * 3. 推论证明
 *
 * 3.1 除了最大面值和1面值的硬币以外，其他面值的硬币最多只有1个
 *
 * 最优找零公式：S = m01 + m11c + m22c + … + mkkc
 *
 * S = m01 + (m11 + m22 + … + mkk)c = m01 + gc  = m0 + gc (m0 < c, g >= 0)
 *
 * 从公式中，我们可以自觉感受到S中，一定有m0个面值为1的硬币，但从严谨的数学逻辑出发，我们还是证明一下
 *
 *
 *
 * 3.2 假设最优找零公式 S = m0 + gc (m0 < c, g >= 0)，有另一种最优找零公式 S1 = x + hc (x < c, x != m0, g >= 0)
 *
 * m0 + gc = x + hc
 *
 * (m0 - x) = (h-g)c 或(x -m0) = (g-h)c （2个形式的证明都是一样的）
 *
 * ∵x != m0
 *
 * ∴(h-g)c != 0
 *
 * ∴(m0 - x) = nc (n > 0)
 *
 * ∴m0 = nc + x > c (n > 0)
 *
 * ∵m0大于c时，一定可以将c个m0转化为一个面值为c的硬币，转化后的总数量必然小于m0
 *
 * ∴m0 = nc + x不成立，假设不成立
 *
 * ∴最优找零公式S中，一定有m0个面值为1的硬币
 *
 *
 *
 * 3.3 已知S中m0数量固定，所以只要找出S = gc 的最优找零数量，即是S的最优找零方案，我们先尝试找一找各种情况下的方案
 *
 * S = gc = (m11 + m22 + … + mkk)c
 *
 * 去掉c， g = m11 + m22 + … + mkk
 *
 * 3.3.1 当g <= k时，g = x (x∈{1, 2, …, k})，用一枚面值x的硬币即是最优解
 *
 * 3.3.2 当k< g <= 2k时，g = k + x (x∈{1, 2, …, k})，只需2枚硬币即可，因为1枚硬币最多只能表达到k的面值，而g > k，所以g = g = k + x即是最优解
 *
 * 3.3.3 当2k< g <= 3k时，g = 2k + x (x∈{1, 2, …, k})，只需3枚硬币即可，因为用2枚硬币最多只能表达到2k的面值，而g > 2k，所以g = 2k + x即是最优解
 *
 * 也就是说，对于任意一个g，总是可以表达为 g = nk + x 这种形式（如我们所有的数都可以用 (n10 + x)(0<= n, 0< x < 10)来表示），且这种形式的找零方案是最优的（使用n + 1枚硬币）
 *
 *
 *
 * 3.4 证明，g = m11 + m22 + … + mkk = nk + x (0 < x < k) 时，没有一种其他的找零方案使用的硬币数 <= n
 *
 * 反证，假设有一种找零方案g1 = m11 + m22 + … + mkk，使用的硬币数 <= n
 *
 * ∵n枚kc面值的的硬币总数总是大于或等于n枚由{1c, 2c, …, kc}组成的硬币总数
 *
 * ∴g1 <= nk < nk + x = g
 *
 * ∴g1 != g
 *
 * ∴不存在这一的找零方案g1
 *
 * ∴g = nk + x这样的找零方案总是最优的
 *
 * ∴g总是用最大数量的k，和一枚其他数量的x去找零，这完全满足贪婪算法的找零方案
 *
 * ∴S = gc 的最优找零数量是贪婪算法
 *
 * ∴S = m0 + gc 的最优找零方案是贪婪算法
 * </pre>
 */
public class ChangeCoins {


    /*
     *
     * @param coinsValues 可用来找零的硬币 coinsValues.length是硬币的种类
     * @param n 待找的零钱
     * @return 最少硬币数目
     */
    public static void charge(int[] coinsValues, int n) {
        int[][] c = new int[coinsValues.length + 1][n + 1];

        // 初始化边界条件
        for (int i = 0; i <= coinsValues.length; i++) {
            c[i][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            c[0][i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= coinsValues.length; i++) { //i表示参加找零的硬币的种类1~i种硬币
            for (int j = 1; j <= n; j++) {//j表示需要找零的钱数
                if (j < coinsValues[i - 1]) {
                    c[i][j] = c[i - 1][j];
                    continue;
                }

                //每个问题的选择数目---选其中较小的
                if (c[i - 1][j] < (c[i][j - coinsValues[i - 1]] + 1)) {
                    c[i][j] = c[i - 1][j];
                } else {
                    c[i][j] = c[i][j - coinsValues[i - 1]] + 1;
                }
            }
        }

        int minCount= c[coinsValues.length][n];
        System.out.println("面值为的最小硬币数 : " + minCount );
    }


    /**
     * <pre>
     *     动态规划的基本思想是将待求解问题分解成若干个子问题，先求解子问题，并将这些子问题的解保存起来，如果以后在求解较大子问题的时候需要用到这些子问题的解，就可以直接取出这些已经计算过的解而免去重复运算。保存子问题的解可以使用填表方式，例如保存在数组中。
     *
     *
     * 用一个实际例子来体现动态规划的算法思想——硬币找零问题。
     *
     * 问题描述:
     *
     * 假设有几种硬币，并且数量无限。请找出能够组成某个数目的找零所使用最少的硬币数。例如几种硬币为[1, 3, 5], 面值2的最少硬币数为2(1, 1), 面值4的最少硬币数为2(1, 3), 面值11的最少硬币数为3(5, 5, 1或者5, 3, 3).
     *
     * 问题分析:
     *
     * 假设不同的几组硬币为数组coin[0, ..., n-1]. 则求面值k的最少硬币数count(k), 那么count函数和硬币数组coin满足这样一个条件:
     *
     * count(k) = min(count(k - coin[0]), ..., count(k - coin[n - 1])) + 1;
     * 并且在符合条件k - coin[i] >= 0 && k - coin[i] < k的情况下, 前面的公式才成立.
     * 因为k - coin[i] < k的缘故, 那么在求count(k)时, 必须满足count(i)(i <- [0, k-1])已知, 所以这里又涉及到回溯的问题.
     *
     * 所以我们可以创建一个矩阵matrix[k + 1][coin.length + 1], 使matrix[0][j]全部初始化为0值, 而在matrix[i][coin.length]保存面值为i的最少硬币数.
     *
     * </pre>
     *
     * @param coins
     * @param k
     * @return
     */
    public static int backTrackingCoin(int[] coins, int k) {//回溯法+动态规划
        if (coins == null || coins.length == 0 || k < 1) {
            return 0;
        }
        int[][] matrix = new int[k + 1][coins.length + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < coins.length; j++) {
                int preK = i - coins[j];
                if (preK > -1) {//只有在不小于0时, preK才能存在于数组matrix中, 才能够进行回溯.
                    matrix[i][j] = matrix[preK][coins.length] + 1;//面值i在进行回溯
                    if (matrix[i][coins.length] == 0 || matrix[i][j] < matrix[i][coins.length]) {//如果当前的硬币数目是最少的, 更新min列的最少硬币数目
                        matrix[i][coins.length] = matrix[i][j];
                    }
                }
            }
        }
        return matrix[k][coins.length];
    }


    public static void changeCoins(int[] coins, int money) {
        /*保存面值为i的纸币找零所需的最小硬币数*/
        int[] coinsUsed = new int[money + 1];
        /*硬币种类数量*/
        int valueKinds = coins.length;
        /*0元的最优解*/
        coinsUsed[0] = 0;

        Map<Integer, HashMap<Integer, Integer>> coinChangeMap = new HashMap<Integer, HashMap<Integer, Integer>>();

        /*从1 - money先求子问题的最优解*/
        for (int cents = 1; cents <= money; cents++) {
            /*当用最小币值的硬币找零时，所需硬币数量最多*/
            int minCount = cents;
            /*保存各个面值的具体找零方案*/
            HashMap<Integer, Integer> minCoinMap = new HashMap<Integer, Integer>();
            /*遍历每一种面值的硬币，看是否可作为找零的其中之一*/
            for (int kind = 0; kind < valueKinds; kind++) {
                /*当前面值*/
                int coinVal = coins[kind];
                int oppCoinVal = cents - coinVal;
                /*若当前面值的硬币小于当前的cents则分解问题并查表*/
                if (coinVal <= cents) {
                    int tempCount = coinsUsed[oppCoinVal] + 1;
                    if (tempCount <= minCount) {
                        /*子问题的最优解*/
                        HashMap<Integer, Integer> subMap = coinChangeMap.get(oppCoinVal);
                        HashMap<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();
                        if (subMap != null) {
                            /*取到了子问题的最优解*/
                            tmpMap.putAll(subMap);
                        }
                        /*子问题的最优解+剩下面值 ->整个问题的最优解决*/
                        if (tmpMap.containsKey(coinVal)) {
                            tmpMap.put(coinVal, subMap.get(coinVal) + 1);
                        } else {
                            tmpMap.put(coinVal, 1);
                        }
                        minCount = tempCount;
                        minCoinMap = tmpMap;
                    }
                }
            }
            // 保存最小硬币数
            coinsUsed[cents] = minCount;
            coinChangeMap.put(cents, minCoinMap);
            //getArrayItem(coinsUsed);
            System.out.println("面值为 " + (cents) + " 的最小硬币数 : " + minCount + ",货币为" + minCoinMap);
        }
    }

    public static void main(String[] args) {
        // 硬币面值预先已经按降序排列
        int[] coinValue = new int[]{1, 2, 10};
        // 需要找零的面值
        int money = 23;
        // 保存每一个面值找零所需的最小硬币数，0号单元舍弃不用，所以要多加1
        charge(coinValue, money);
    }

}
