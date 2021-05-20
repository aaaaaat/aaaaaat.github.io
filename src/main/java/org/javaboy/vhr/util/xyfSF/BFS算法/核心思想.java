package org.javaboy.vhr.util.xyfSF.BFS算法;



import org.javaboy.vhr.util.xyfSF.回溯算法.TreeNode;

import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * TODO 把一些问题抽象成图，从一个点开始，向四周扩散（在一幅图中找到起点start到终点target的最近距离（含各种最））
 * 一般来说，写BFS算法都是用队列这种数据结构
 * 每次将一个节点周围的所有节点加入队列
 *
 * BFS（宽度优先搜索）和DFS（深度优先搜索-决策树）的最主要区别：BFS找到的路径一定是最短的
 * 但代价就是空间复杂度比DFS大很多
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/26 11:01 上午
 */

public class 核心思想 {
    public class TreeNode {
        public int value;
        public List<TreeNode> nodes;
        public TreeNode(int data){
            this.value=data;
        }
    }
    //计算从起点start到终点target的最近距离

    int BFS(TreeNode start, TreeNode target){

        Queue<TreeNode> q = null;  //核心数据结构
        Set<TreeNode> visited=null; //避免走回头路

        q.offer(start);  //将起点加入队列

        visited.add(start);
        int step=0;      //记录扩散的步数

        while(!q.isEmpty()){
            int sz=q.size();

            //将当前队列中的所有节点向四周扩散
            for (int i = 0; i < sz; i++) {

                TreeNode cur=q.poll();

                /**
                 * 重点：这里判断是否到达终点
                 */
                if (cur==target){
                    return step;
                }

                //将cur的相邻节点加入队列
                //cur.nodes泛指cue相邻的节点，比如说二维数组中
                //cur上下左右四面的位置就是相邻节点
                for (TreeNode x : cur.nodes) {

                    //避免走回头路--根据visited中的记录
                    //但是向一般的二叉树结构，没有子节点到父节点的指针，不会走回头路就不需要visited
                  //  if (x not in visited){

                         q.offer(x);//如果节点x没有遍历过
                         visited.add(x);

                  //  }

                }

                //下一层(宽度优先搜索)
                step++;

            }
        }

        return step;
    }
}
