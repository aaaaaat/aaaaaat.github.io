package org.javaboy.vhr.util.xyfSF.递归算法;

/**
 * TODO 斐波那契数列--简单的递归，中间不带择优
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/23 9:20 上午
 */
public class 带备忘录的递归解法 {

    //暴力递归--斐波那契数列
    int fib(int N){
        if (N==1||N==2){
            return 1;
        }
        return fib(fib(N-1)+fib(N-2));
    }


    //数组充当备忘录
    int fib1(int N){
        if (N<1){
            return 0;
        }
        //备忘录初始化为0
        int[] meno = new int[N + 1];
        return helper(meno,N);
    }

    private int helper(int[] meno, int n) {
        if (n==1||n==2){
            return 1;
        }
        if (meno[n]!=0){
            return meno[n];
        }
        meno[n]=helper(meno,n-1)+helper(meno,n-2);//自顶向下
        return meno[n];
    }


    //自底向上--动态规划：从最底下，最简单的往上推，直到我们想要的答案
    int fib2(int N){
        int[] dp= new int[N + 1];
        //base case
        dp[1]=dp[2]=1;            //状态转移方程一

        for (int i=3;i<=N;i++){
            dp[i]=dp[i-1]+dp[i-2];//状态转移方程二
        }
        return dp[N];
    }


    //优化空间复杂度--dp[i]=dp[i-1]+dp[i-2]只依赖前两个状态
    int fib4(int N){
        if (N==1||N==2){
            return 1;
        }
        int prev=1;
        int curr=1;
        for (int i=3;i<=N;i++){
            int sum=prev+curr;
            prev=curr;
            curr=sum;
        }
        return curr;
    }
}
