package org.javaboy.vhr.util.xyfSF.打家劫舍;

/**
 * TODO 动态规划问题：1、状态2、选择
 *
 * @author xyf
 * @version 1.0
 * @date 2021/5/13 11:21 上午
 */
public class rob {

    public int rob(int[] nums){

        //面前房子的索引就是状态
        //抢和不抢就是选择

        //rob[nums[2...]]= rob[nums[3...]] or nums[2]+rob[nums[4...]]
        //两个选择中，每次都选更大的结果
        //最后得到的就是最多能抢到的money

        return dp(nums,0);
    }

    private int dp(int[] nums, int i) {

        return 0;

    }
}
