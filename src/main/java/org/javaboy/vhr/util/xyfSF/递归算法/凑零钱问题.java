package org.javaboy.vhr.util.xyfSF.递归算法;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO   动态规划的另一个重要特性--最优子结构（剪枝+重复子问题）
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/23 9:42 上午
 */
public class 凑零钱问题 {
    //给你 k 种面值的硬币，面值分别为 c1, c2 ... ck ，
    // 每种硬 币的数量无限，再给一个总金额 amount ，
    // 问你最少需要几枚硬币凑出这个 金额，如果不可能凑出，算法返回 -1

    //coins 可选择硬币值  amount 目标金额
    //返回最少需要几枚 int
    int coinChange(int[] coins,int amount){
        //想求 amount =11 时的最少硬币数(原问题)，
        //如果你知道凑出 amount = 10 的最少硬币 数(子问题)，
        //你只需要把子问题的答案加一(再选一枚面值为 1 的硬币) 就是原问题的答案，
        //因为硬币的数量是没有限制的，子问题之间没有相互 制，是互相独立的。

        //--状态转移方程
        //分析：硬币数量无限，目标金额位移amount
        //遍历coins中的coin
        //1、最少硬币数(需要与原res做比较)：res=min（res,1+dp(n-coin)）
        //2、明确baseCase 当金额为0时，所需要的硬币数量为0；当目标金额小于0时，无解，返回-1
        int res=0;

        return res;
//        int[] dp = new int[amount+1];
//        //初始化
//        dp[0]=1;
//        //自底向上
//        for (int coin:coins) {
//             for (int j=coin;j<amount+1;j++){
//                 dp[j] += dp[j-coin];
//             }
//        }
//        return dp[amount];
    }

    /**
     * 递归求解
     */

    public static int coinChange(int n ,int[] coins){

        if (n==0){

            return 0;
        }

        if (n<0){
            return -1;
        }

        //求最小值，所以初始化为无穷大
        // （对于每个子树都是求最少硬币数）：比如输入n=5，对于n的每个子树，第一个子树比较（max，返回值1）得到res=1，第二哥子树（1，返回值3）得到res=1
        int res = Integer.MAX_VALUE;

        for (int coin:coins){

            int subProblem=coinChange(n-coin,coins);

            if (subProblem==-1) continue; //减coin减多了，跳过此次for循环

            res=Math.min(res,1+subProblem);//因为只隔一层，必定只相差一枚硬币，面值不一定

        }

        return (res==Integer.MAX_VALUE)?-1:res;

    }

    /**
     * 带备忘录的递归
     */
    public static int coinChange1(int n, int[] coins, Map<Integer,Integer> subResult){
        if (subResult.containsKey(n)){
            return subResult.get(n);
        }else if (n==0){
            return 0;
        }else if (n<0){
            return -1;
        }else{
            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                //择优--选择最优子结构
                int subProblem=coinChange1(n-coin,coins,subResult);
                if (subProblem==-1){
                    continue;
                }
                res=Math.min(res,1+subProblem);

                subResult.put(n,res);
            }
        }
        return subResult.get(n);
    }
    /**
     * dp数组迭代解法 自底向上用dp table来消除重叠子问题
     */
     public static int coinChange3(int[] coins,int amount){
         //dp[i]=x表示，当目标金额为i时， 至少 需要x枚硬币
         //最少需要几枚硬币，最多情况，➡由多个一枚硬币构成
         int[] dp = new int[amount + 1];
         for (int i = 0; i < dp.length; i++) {
             dp[i]=i;//初始化为amount+1
         }

         //base case
         dp[0]=0;

         for (int i = 0; i < dp.length; i++) {

             //内层for在求所有子问题的最小值
             for (int coin : coins) {
                 //子问题无解，跳过
                 if (i-coin<0){
                     continue;
                 }
                 dp[i]=Math.min(dp[i],1+dp[i-coin]);

             }
         }

         //如果没有找到还是初始值
         return (dp[amount]==amount+1)?-1:dp[amount];
     }

    public static void main(String[] args) {
        int n=11;
        int[] coins = {1, 2, 5};
        int i = coinChange(n, coins);
        System.out.println(i);
        Map<Integer,Integer> subresult=new HashMap<>();
        int i1 = coinChange1(n, coins, subresult);
        System.out.println(i1);
    }
}
