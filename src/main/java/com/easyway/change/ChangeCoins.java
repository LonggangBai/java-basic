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
    public static void changeCoins(int[] coins,int money) {
        /*保存面值为i的纸币找零所需的最小硬币数*/
        int [] coinsUsed = new int [money+1];
        /*硬币种类数量*/
        int valueKinds = coins.length;
        /*0元的最优解*/
        coinsUsed[0] = 0;

        Map<Integer, HashMap<Integer,Integer>> coinChangeMap = new HashMap<Integer,HashMap<Integer,Integer>>();

        /*从1 - money先求子问题的最优解*/
        for(int cents = 1;cents<=money;cents++) {
            /*当用最小币值的硬币找零时，所需硬币数量最多*/
            int minCount = cents;
            /*保存各个面值的具体找零方案*/
            HashMap<Integer,Integer> minCoinMap = new HashMap<Integer,Integer>();
            /*遍历每一种面值的硬币，看是否可作为找零的其中之一*/
            for(int kind =0;kind<valueKinds;kind++) {
                /*当前面值*/
                int coinVal = coins[kind];
                int oppCoinVal = cents - coinVal;
                /*若当前面值的硬币小于当前的cents则分解问题并查表*/
                if(coinVal <=cents) {
                    int tempCount = coinsUsed[oppCoinVal]+1;
                    if(tempCount<=minCount) {
                        /*子问题的最优解*/
                        HashMap<Integer,Integer> subMap = coinChangeMap.get(oppCoinVal);
                        HashMap<Integer,Integer> tmpMap = new HashMap<Integer,Integer>();
                        if(subMap!=null) {
                            /*取到了子问题的最优解*/
                            tmpMap.putAll(subMap);
                        }
                        /*子问题的最优解+剩下面值 ->整个问题的最优解决*/
                        if(tmpMap.containsKey(coinVal)) {
                            tmpMap.put(coinVal, subMap.get(coinVal)+1);
                        }else {
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
            System.out.println("面值为 " + (cents) + " 的最小硬币数 : "   + minCount+",货币为"+ minCoinMap);
        }
    }

    public static void main(String[] args) {
        // 硬币面值预先已经按降序排列
        int[] coinValue = new int[] { 50, 20, 11, 5, 2,1 };
        // 需要找零的面值
        int money = 23;
        // 保存每一个面值找零所需的最小硬币数，0号单元舍弃不用，所以要多加1
        changeCoins(coinValue,  money);
    }

}
