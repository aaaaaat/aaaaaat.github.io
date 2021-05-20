package org.javaboy.vhr.util.xyfSF.回溯算法;

/**
 * TODO 回溯算法--决策树--不存在重叠子问题
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/25 10:35 下午
 */

import jdk.nashorn.internal.ir.CallNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 与递归树的不同：路径同样是选择，但节点含义不同
 * 递归树的节点代表目标，叶子节点代表baseCase，先递后归
 *
 * 决策树的每条到叶子节点的路径组合起来（即结果），代表一次完整的决策，每个节点都是在做选择或决策
 */
public class 全排列问题 {

    //给三个数：「1 2 3」 --推导回溯树，路径为选择。从根遍历这棵树的底层，记录路径上的数字，就是所有的全排列
    //--把这棵树称为回溯算法的决策树

    public void traverse(TreeNode root){

        for (TreeNode child : root.nodes) {

            //前序遍历需要的操作--关键时间点--做选择--将选择从选择列表移除--路径.add(选择)

            traverse(child);

            //后序遍历需要的操作--关键时间点--撤销选择--路径.remove(选择)--将选择再加入选择列表

        }
    }

    static List<List<Integer>> res = new LinkedList<>();

    public static List<List<Integer>> permute(int[] nums) {


        //输入一组不重复的数字，返回它们的全排列

        //记录路径
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums,track);
        return res;
    }

    /**
     * 没有显示记录选择列表，而是通过nums和track推导出当前的选择列表
     */
    //路径记录在track中
    //选择列表，nums中不存在于track的那些元素
    //结束条件：nums中的元素全都在track中出现
    public static void backtrack(int[] nums, LinkedList<Integer> track) {
        // 结束条件
        if (track.size()==nums.length){
            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            //前序遍历，排除不合法的选择(track中已经存在的应该排除)
            if (track.contains(nums[i])){    //这一块需要O（N）的时间复杂度
                continue; //下一层for循环
            }

            track.add(nums[i]);

            //进入下一层决策树
            backtrack(nums,track);

            //取消选择
            track.removeLast();
        }
    }

}
