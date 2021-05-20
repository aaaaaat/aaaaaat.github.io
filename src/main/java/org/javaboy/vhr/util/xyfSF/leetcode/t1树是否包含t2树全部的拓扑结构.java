package org.javaboy.vhr.util.xyfSF.leetcode;

public class t1树是否包含t2树全部的拓扑结构 {
    //给定彼此独立的两棵树头节点分别为t1和t2，判断t1树是否包含t2树全部的拓扑结构
    //返回boolean
    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public static boolean contains(Node t1,Node t2){

        if(t2==null) {
            return true;
        }
        if (t1==null){
            return false;
        }

        //遍历t1树 O(N*M)
        return check(t1,t2)||contains(t1.left,t2)||contains(t1.right,t2);
    }

    private static boolean check(Node h, Node t2) {

        if (t2==null){
            return true;
        }
        //先序遍历
        if (h.value!=t2.value||h==null){ //值不相等或者h已经遍历完成，返回false
            return false;
        }
        return check(h.left,t2.left)&&check(h.right,t2.right);
    }
}
