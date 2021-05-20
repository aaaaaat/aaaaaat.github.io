package org.javaboy.vhr.util.xyfSF.股票买卖问题;

import static java.lang.Integer.max;

/**
 * TODO k = 2 和前面题目的情况稍微不同，因为上面的情况都和 k 的关系不太大。
 * 这道题 k = 2 和后面要讲的 k 是任意正整数的情况中，对 k 的处理就凸显出 来了。
 * @author xyf
 * @version 1.0
 * @date 2021/5/7 2:27 下午
 */
public class k2股票交易三维数组 {

    //按照之前的代码，我们可能想当然这样写代码(错误的):
    //原始的动态转移方程，没有可化简的地方
    //dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    //dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

    //需要对k进行穷举
    // 一维   for (int i = 0; i < n; i++)
    // 二维      for (int k = max_k; k >= 1; k--)
    //               if (i - 1 == -1) { /*处理 base case */ }
    // 三维              两种状态

    //k取值范围比较小。所以可以不用for循环，直接把k=1和2的情况全部列举出来
    // dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
    // dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])--1、前一天保持原状 2、前一天未持有的情况下买入（交易次数-1）
    // dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])--1、前一天保持原状 2、前一天持有的情况下卖掉
    // dp[i][1][1] = max(dp[i-1][1][1], -prices[i]) --dp[i-1][0][0]最大交易次数为0的情况下，也没有持有股票，利润为0

    int maxProfit_k_2(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);  // 先 2 后 1 -- 交易次数逐渐减少
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }


    /**
     * k = any integer
     * 有了上一题 k = 2 的铺垫，这题应该和上一题的第一个解法没啥区别。
     * 但是 出现了一个超内存的错误，原来是传入的 k 值会非常大，dp 数组太大了。
     * 现在想想，交易次数 k 最多有多大呢?
     * 一次交易由买入和卖出构成，至少需要两天。
     * 所以说有效的限制 k 应该不超 过 n/2，
     * 如果超过，就没有约束作用了，相当于 k = +infinity。这种情况是之 前解决过的。
     */
    int maxProfit_k_any(int max_k, int[] prices) { int n = prices.length;
        if (max_k > n / 2)
            return maxProfit_k_inf(prices);
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++){
        for (int k = max_k; k >= 1; k--) {
            if (i - 1 == -1) { /* 处理 base case */ }
            dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
            dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
        }
        }
        return dp[n - 1][max_k][0];
    }

    private int maxProfit_k_inf(int[] prices) {
        return 0;
    }


}
