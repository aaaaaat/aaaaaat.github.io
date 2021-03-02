package org.javaboy.vhr.util;

import java.util.Stack;

public class 调整搜索二叉树中两个错误的节点 {
    //一棵二叉树原本是搜索二叉树，但是其中有两个节点调换了位置，使得这棵二叉树不再是搜索二叉树
    //请找到这两个错误节点并返回
    //给定头结点head
    //返回errs[0] errs[1],二叉树节点型数组
    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //分析：对所有的节点值都不一样的搜索二叉树 中序遍历时，出现的节点会一直升序
    //因此如果有两个节点的位置错了，就一定会弧线降序
    public Node[] getTwoErrorNodes(Node head){

        //如果在中序遍历是节点值出现两次降序，第一个错误的节点为第一次降序时较大的节点，第二个错误的节点为第二次降序是较小的节点
        //如果出现一次降序，第一个错误的节点为这次降序时较大的节点，第二个错误的节点为这次降序时较小的节点
        //总结：第一个错误节点为第一次降序时较大的节点，第二个错误节点为最后一次降序时较小的节点
        Node[] errs = new Node[2];
        if (head==null){
            return errs;
        }
        Stack<Node> stack = new Stack<>();
        //中序遍历：左-根-右
        Node pre=null;//用于比较值的大小
        while (!stack.isEmpty()&&head!=null){
            if (head!=null){
                stack.push(head);
                head=head.left;
            } //到树的左下角
            else{
                head=stack.pop();

                if (pre!=null&&pre.value>head.value){
                    //总结：第一个错误节点为第一次降序时(通过null判断)较大的节点，第二个错误节点为最后一次降序时较小的节点
                    errs[0]=errs[0]==null?pre:errs[0];
                    errs[1]=head;
                }

                pre=head;
                head=head.right;
            }
        }
        return errs;
    }


    //进阶：问题1得到两个错误节点，不通过交换节点值方式恢复搜索二叉树
    //而是在结构上完全交换两个节点的位置，实现调整的函数

    //首先找到两个错误节点的父结点
    public Node[] getTwoErrParents(Node head,Node e1,Node e2){
        Node[] parents=new Node[2];
        if (head==null){
            return parents;
        }
        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty()||head!=null){
             if (head!=null){
                 stack.push(head);
                 head=head.left;
             }else {
                 head=stack.pop();

                 //左-根-右  的根操作，类似sout
                 if (head.left==e1||head.right==e1){
                     parents[0]=head;
                 }
                 if (head.left==e2||head.right==e2){
                     parents[1]=head;
                 }

                 head=head.right;
             }
        }
        return parents;
    }
    //错误节点e1和e2，需要分析e1的父结点e1p、及e1的左右孩子节点 以及 e2的父结点e2p、及e2的左右孩子节点

    public static void main(String[] args) {

    }
}
