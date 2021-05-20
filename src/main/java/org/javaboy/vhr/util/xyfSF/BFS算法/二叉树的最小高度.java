package org.javaboy.vhr.util.xyfSF.BFS算法;

import java.util.LinkedList;

/**
 * TODO 在一幅图中找到起点start到终点target的最近距离（含各种最）
 * 最短距离、最少替换次数、最短连线、最短路径
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/26 11:05 上午
 */
public class 二叉树的最小高度 {

    public class TreeNode{
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int data){
            this.value=data;
        }
    }

    //最小深度是从根节点到最近叶子节点的最短路径上的节点数量
    //叶子节点是没有子节点的节点

    //1、明确一下起点start和终点target是什么，怎么判断到达了终点
    //起点就是root根结点，终点就是最靠近根结点的那个叶子节点
    //叶子节点就是两个子节点都是null的节点
    //if(cur.left==null&&cur.right==null)
    // 到达叶子节点

    int minDepth(TreeNode root){
        if (root==null) return 0;

        LinkedList<TreeNode> q = new LinkedList<>();

        //一般的二叉树，没有回头路
        //不需要visited

        q.offer(root);
        //root本身就是一层，所以初始化为1
        int depth=1;

        while (!q.isEmpty()){
            int sz=q.size();


            for (int i = 0; i < sz; i++) {

                TreeNode cur = q.poll();

                //判断是否到达终点
                if (cur.left==null&&cur.right==null){
                    return depth;
                }

                //将cur的相邻节点加入队列
                if (cur.left!=null){
                    q.offer(cur.left);
                }
                if (cur.right!=null){
                    q.offer(cur.right);
                }
            }

            depth++;//宽度遍历，哪一层的某个节点为叶子节点，说明为最小高度
        }
        return depth;
    }

    /**
     * 总结：BFS借助队列齐头并进，可以在不遍历完整树的条件下找到最短距离
     * DFS也是可以的，但是时间复杂度相对要高很多。
     * DFS实际上是靠递归的堆栈记录走过的路径
     * 你要找到最短路径，肯定的把二叉树中所有树杈都探索完才能对比出最短的路径有多长对不对
     */
}
