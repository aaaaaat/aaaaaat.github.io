package org.javaboy.vhr.util;

public class 有序数组生成平衡搜索二叉树 {
    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //给定一个数组sortArr，一直其中没有重复值，用这个有序数生成一棵平衡搜索二叉树
    // 并且该搜索二叉树中序遍历的结果和sortArr一致

    public Node generateTree(int[] sortArr){
        if (sortArr==null){
            return null;
        }

        return generate(sortArr,0,sortArr.length-1);
    }

    private Node generate(int[] sortArr, int start, int end) {

        //递归过程：用有序数组最中间的数生成搜索二叉树的头节点，然后用这个数左边的数生成左子树，有右边的数生成右子树即可

        //终止条件
        if (start>end){
            return null;
        }

        //生成返回节点head： 三个条件：head、head.left和head.right
        int mid=(start+end)/2;
        Node head = new Node(sortArr[mid]);
        head.left=generate(sortArr,start,mid-1);
        head.right=generate(sortArr,mid+1,end);

        return head;
    }
}
