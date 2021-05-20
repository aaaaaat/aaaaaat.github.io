package org.javaboy.vhr.util.xyfSF.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * TODO
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/12 9:39 下午
 */
public class 二叉树的锯齿遍历 {
    //双端队列实现
    public class Node{
        public int value;
        public Node left;
        public Node right;
        public Node (int data){
            this.value=data;
        }
    }
    public void zigZag(Node head){
        if (head==null){
            return;
        }
        //双端队列
        Deque<Node> dq = new LinkedList<>();

        int level=1;
        boolean lr=true;

        Node last= head;
        Node nLast=null;

        dq.offerFirst(head);

        while(!dq.isEmpty()){
            if (lr){  //从左往右
                head=dq.pollFirst();
                if (head.left!=null){
                    nLast=nLast==null?head.left:nLast;
                    dq.offerLast(head.left);
                }
                if (head.right!=null){
                    nLast=nLast==null?head.right:nLast;
                    dq.offerLast(head.right);
                }
            }else{
                head=dq.pollLast();
                if (head.right!=null){
                    nLast=nLast==null?head.right:nLast;
                    dq.offerFirst(head.right);
                }
                if (head.left!=null){
                    nLast=nLast==null?head.left:nLast;
                    dq.offerFirst(head.left);
                }
            }
            System.out.println(head.value+" ");

            //切换原则 lr 在true和false中不断转化
            //最先进入队头或者最先进入队尾，最后打印
            if (head==last&&!dq.isEmpty()) //第一行（也就是根结点，所以多加一个last节点）也需要判断
            {
                lr=!lr;
                last=nLast;//last一行的最后一个节点（当打印完这一行的最后一个节点，下一行的最后一个节点肯定出来了（因为就是下一行最先进入双端队列的节点）），nLast下一行的最后一个节点
                nLast=null;//每打印一行清空，临时变量
            }
        }

        //
    }
}
