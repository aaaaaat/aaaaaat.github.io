package org.javaboy.vhr.util.xyfSF.二叉树;


import java.util.Map;

/**
 * TODO 二叉数框架
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/13 9:23 下午
 */

public class base {
    void traverse(TreeNode root){
        //二叉树遍历框架 -- 典型的非线性递归遍历结构

        //前序遍历
        traverse(root.left);
        //中序遍历
        traverse(root.right);
        //后序遍历

    }
    public class TreeNode{
          int value;
          TreeNode left,right;

          TreeNode(int data){
              this.value=data;
          }
    }

    /**
     * LeetCode123 求二叉树中最大路径和
     */
    //构造返回值
    public class ReturnType{
        int height;
        int maxDistance;

        public ReturnType(int height,int maxDistance){
            this.height=height;
            this.maxDistance=maxDistance;
        }
    }
    public ReturnType maxTreeDistance(TreeNode root){
        //后序遍历：左子树->右子树->根结点

        //分析可能性
        //情况一、左子树最大路径
        //情况二：右子树最大路径
        //情况三：左子树最大高度+右子树最大高度+1 （包括root节点）
        //  ->需要返回值 左右子树最大路径+左右子树高度

        //构造终止条件（叶子节点）返回值 base case
        if (root==null){
            return new ReturnType(0,0);
        }

        //针对传入的root节点
        //默认得到左树和右树的所有信息，整合所有的可能性，构造ReturnType
        ReturnType left=maxTreeDistance(root.left);
        ReturnType right=maxTreeDistance(root.right);

        //根据left和right信息
        int height=Math.max(left.height,right.height);
        int maxDistance=Math.max(left.height+right.height+1,Math.max(left.maxDistance,right.maxDistance));

        return new ReturnType(height,maxDistance);
    }

    //主函数，提取递归后的ReturnType中的maxDistance（需要信息）
    public int getMaxDistance(TreeNode root){
        return maxTreeDistance(root).maxDistance;
    }

    /**
     * 根据前序遍历和中序遍历的结果还原一棵二叉树
     */
    public TreeNode buildTree(int[] preOrder, int preStart, int preEnd,
                              int[] inOrder, int inStart, int inEnd,
                              Map<Integer,Integer> inMap){

        //baseCase
        if(preStart > preEnd || inStart > inEnd) return null;

        //本质上前序遍历：根结点->左子树->右子树

        TreeNode root = new TreeNode(preOrder[preStart]);
        int inRoot = inMap.get(root.value);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(preOrder, preStart + 1, preStart + numsLeft ,
                inOrder, inStart, inRoot - 1, inMap);
        root.right = buildTree(preOrder, preStart + numsLeft + 1, preEnd,
                inOrder, inRoot + 1, inEnd, inMap);

        return root;
    }

    /**
     * 恢复一棵BST搜索二叉树 BalancedSearchTree 通过有序数组生成平衡搜索二叉树
     */


}
