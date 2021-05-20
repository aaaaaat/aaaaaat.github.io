package org.javaboy.vhr.util.xyfSF.leetcode;

import java.util.LinkedList;

public class 判断一棵二叉树是否为搜索二叉树和完全二叉树 {
    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //给定二叉树的一个头节点head，已知其中没有重复节点
    //实现两个函数分别判断二叉树是否为搜索二叉树和完全二叉树
    public boolean isBst(Node head){

        //改写中序遍历，在遍历的过程中看节点值是否都是递增
        //Morris遍历：调整二叉树结构、恢复二叉树结构
        //因此当发现节点值是降序时，不能直接返回false，这么做可能会跳过恢复阶段，从而破坏二叉树的结构

        //改写Morris中序遍历
        if (head==null){
            return false;
        }
        boolean res=true; //返回值

        Node pre=null;//pre用于判断节点值

        Node cur1=head;
        Node cur2=null; //cur1和cur2用于Morris遍历

        while(cur1!=null){

            //左
            cur2=cur1.left;
            if (cur2!=null){
                while(cur2.right!=null&&cur2.right!=cur1){
                    cur2=cur2.right;   //左子树的最右节点
                }
                if (cur2.right==null){
                    cur2.right=cur1;
                    //cur1往左走
                    cur1=cur1.left;
                    continue;
                }else if (cur2.right==cur1){ //else
                    cur2.right=null;
                }
            }

            //中
            if (pre!=null&&pre.value>cur1.value){
                res=false;
            }

            //右
            pre=cur1;
            cur1=cur1.right;
        }

        return res;
    }

    //判断一棵二叉树是否完全二叉树
    public boolean isCBT(Node head){

        //有右无左 判断：非完全二叉树
        //如果当前节点不是左右孩子均有，则之后的节点都必须为叶子节点，否则返回false
        if(head == null){
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();

        //判断 何时开始判断剩余节点是否叶子节点
        boolean leaf=false;

        Node l=null;
        Node r=null;

        queue.offer(head);

        while(!queue.isEmpty()){

            head=queue.poll();
            l=head.left;
            r=head.right;
            //情况一：有右结点但没有左节点，返回false
            //情况二：如果当前节点不是左右孩子节点都有，那么之后的节点必须为叶节点，否则返回false
            if ((l==null&&r!=null)||(leaf && ( l!=null || r!=null ))){ //叶节点不含子结点，如果出现l！=null或者r！=null
                return false;
            } //leaf=true表示开启判断叶节点过程

            if (l!=null){
                queue.offer(l);
            }
            if (r!=null){
                queue.offer(r);
            }else{
                //不是左右节点都有
                leaf=true;
            }
        }
        return true;
    }
}
