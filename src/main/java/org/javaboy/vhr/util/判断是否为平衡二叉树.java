package org.javaboy.vhr.util;

public class 判断是否为平衡二叉树 {
    //平衡二叉树的性质：要么是一棵空树，要么任何一个节点的左右子树高度差的绝对值不超过1。
    //给定一棵二叉树的头结点head，判断这棵二叉树是否为平衡二叉树
    //要求时间复杂度为O（N）
    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }



    //依次考察每个节点为头节点的子树，如果都是平衡二叉树，那么整体就是平衡二叉树
    //第一步：以某个节点X为头节点的子树中，分析答案有哪些可能性（X的左子树、X的右子树、X整棵树）
    //1、左子树不平衡，X树不平衡
    //2、右子树不平衡，X树不平衡
    //3、左右子树高度差超过1，X树不平衡
    //4、前3种都没中，X树平衡

    //第二步，分析所需要信息：左右子树是否平衡boolean，左右子树高度
    public class ReturnType{

        public boolean idBalanced;
        public int height;

        public ReturnType(boolean idBalanced, int height) {
            this.idBalanced = idBalanced;
            this.height = height;
        }
    }

    //第三步：设计递归函数：处理以X为头节点的情况下的答案，包括设计递归的base case
    //默认直接得到左树和右树所有的信息，以及把可能性做整合，并返回所需要信息结构
    public ReturnType process(Node head){
        if (head==null){
            return new ReturnType(true,0);
        }

        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.right);

        //构造所需要的信息结构 height isBalanced
        int height=Math.max(leftData.height,rightData.height)+1;

        //isBalanced需要包含三种情况
        Boolean isBalanced=leftData.idBalanced&&rightData.idBalanced
                &&Math.abs(leftData.height-rightData.height)<2;

        //返回值
        return new ReturnType(isBalanced,height);
    }


    public boolean isBalancedTree(Node head){
        return process(head).idBalanced;
    }
}
