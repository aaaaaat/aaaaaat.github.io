package org.javaboy.vhr.util.xyfSF.股票买卖问题;

/**
 * TODO 给定一个数组，它的第i个元素是一支给定股票在第i天的价格
 * TODO 设计一个算法来计算你所能获取的最大利润
 * TODO 你 （最多）可以完成k笔交易
 *
 * @author xyf
 * @version 1.0
 * @date 2021/5/7 11:20 上午
 */
public class 股票买卖 {
    /**
     * 1、穷举框架
     * 如何穷举？ 和递归的思想不太一样
     *
     * 递归：一步步推进，遇到无法解决的就丢给递归
     *
     * 在这里，我们不用递归思想进行穷举
     * 利用状态进行穷举
     *
     * 我们具 体到每一天，看看总共有几种可能的「状态」，
     * 再找出每个「状态」对应的 「选择」。
     * 我们要穷举所有「状态」
     * 穷举的目的是根据对应的「选择」
     * 更新状态。
     *
     * for 状态1 in 状态1的所有取值:
     *    for 状态2 in 状态2的所有取值:
     *      for ...
     *        dp[状态1][状态2][...] = 择优(选择1，选择2...)
     *
     * 每天都有三种「选择」:买入、卖出、无操作
     * 我们用 buy, sell, rest 表示这三种选择
     *
     * 这个问题的「状态」有三个
     * 第一个是天数
     * 第二个是允许交易的最大次数
     * 第三个是当前的持有状态
     * (即之前说的 rest 的状态，我们不妨用 1 表示持有，0 表示没有持有)
     *
     * sell 必须在 buy 之后，buy 必须在 sell 之后。
     * 那么 rest 操作 还应该分两种状态
     * 一种是 buy 之后的 rest(持有了股票)
     * 一种是 sell 之 后的 rest(没有持有股票)。
     *
     * 还有交易次数 k 的限制
     * 就 是说你 buy 还只能在 k > 0 的前提下操作。
     *
     * dp[i][k][0 or 1]
     * 0 <= i <= n-1, 1 <= k <= K
     * n 为天数，大 K 为最多交易数
     * 此问题共 n × K × 2 种状态，全部穷举就能搞定。
     *  for 0 <= i < n: 天数
     *    for 1 <= k <= K: 最大交易数
     *      for s in {0, 1}: 持有状态（1持有，0没有持有）
     *         dp[i][k][s] = max(buy, sell, rest)
     *
     *    最终答案是 dp[n - 1][K][0]
     *    即最后一天，最多允许 K 次交易， 最多获得多少利润。
     *    读者可能问为什么不是 dp[n - 1][K][1]?
     *    因为 [1] 代表手 上还持有股票，
     *    [0] 表示手上的股票已经卖出去了，
     *    很显然后者得到的利润 一定大于前者。
     *
     */


    /**
     * 2、我们完成了「状态」的穷举
     * 我们开始思考每种「状态」有哪些「选择」，应该如何更新「状态」。
     * dp[i][k][0] = max(dp[i-1][k][0]
     * dp[i-1][k][1] + prices[i]) max( 选择rest , 选择sell )
     * 解释:今天我没有持有股票，有两种可能:
     * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有; 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
     * dp[i][k][1] = max(dp[i-1][k][1]
     * dp[i-1][k-1][0] - prices[i]) max( 选择rest , 选择buy )
     * 解释:今天我持有着股票，有两种可能:
     * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票; 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
     *
     * 这个解释应该很清楚了，如果 buy，就要从利润中减去 prices[i]，
     * 如果 sell， 就要给利润增加 prices[i]。
     *
     * 今天的最大利润就是这两种可能选择中较大的那 个。
     *
     * 而且注意 k 的限制，我们在选择 buy 的时候，把 k 减小了 1
     * 很好理解 吧，当然你也可以在 sell 的时候减 1，一样的。
     */

    /**
     * 3、定义base case
     *
     * dp[-1][k][0] = 0
     * 解释:因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
     * dp[-1][k][1] = -infinity
     * 解释:还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
     * dp[i][0][0] = 0
     * 解释:因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
     * dp[i][0][1] = -infinity
     * 解释:不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
     */


    /**
     * k=1，第一题
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
     * = max(dp[i-1][1][1], -prices[i])
     * 解释:k = 0 的 base case，所以 dp[i-1][0][0] = 0。
     * 现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。 可以进行进一步化简去掉所有 k:
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i]) dp[i][1] = max(dp[i-1][1], -prices[i])
     */

    public int MaxProfit(int[] prices){

        //k=1
        int n=prices.length;
        int[][] dp=new int[n][2];

        for (int i = 0; i < n; i++) {

            if (i-1==-1){
                //base case
                //处理
                //dp[0][0]=0;
                //未持有当日股票
                dp[i][0]=0;
                //持有当日股票
                dp[i][0]=-prices[i];

                //base case 处理完跳过下面状态方程
                continue;
            }
            //两种状态：dp[i][0] dp[i][1]
            //未持有
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);//两种可能：前一天未持有，今天仍然未持有；前一天持有，卖了，加钱
            //持有
            //dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            //优化，因为只交易一次
            dp[i][1]=Math.max(dp[i-1][1],-prices[i]);

        }

        return dp[n-1][0];

        /**
         * 空间优化版
         * // k == 1
         * int maxProfit_k_1(int[] prices) {
         *     int n = prices.length;
         *     // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
         *     int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
         *     for (int i = 0; i < n; i++) {
         *       // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
         *      dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
         *      // dp[i][1] = max(dp[i-1][1], -prices[i])
         *      dp_i_1 = Math.max(dp_i_1, -prices[i]);
         *      }
         *     return dp_i_0;
         * }
         */


    }

    /**
     * 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的
     * 无限制交易次数
     */
    int maxProfit_k_inf(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE; for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]); dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }

    /**
     * k = +infinity with cooldown（冷静期）
     *
     * 每次 sell 之后要等一天才能继续交易。只要把这个特点融入上一题的状态转移方程即可:
     * 翻译成代码:
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
     * 解释:第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
     */

    int maxProfit_with_cool(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE; int dp_pre_0 = 0; // 代表 dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp; //储存temp 进入下一次for循环就变成i-2的状态
        }
        return dp_i_0;
    }

}
