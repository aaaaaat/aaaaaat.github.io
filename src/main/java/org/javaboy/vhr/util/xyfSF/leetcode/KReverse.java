package org.javaboy.vhr.util.xyfSF.leetcode;

import java.util.Stack;

/**
 * TODO 单链表的每k个节点之间逆序
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/12 8:00 下午
 */



public class KReverse {
    public class Node{
       public int value;
       public Node next;
       public Node(int data){
           this.value=data;
       }
    }
    //栈结构实现

    //第一组逆序后，需要记录新的头部节点
    //同时第一组的最后一个节点（原来是头节点）应该连接下一个节点

    public Node KReverse(Node head,int k){
        if (k<2){
            return head;
        }
        Stack<Node> stack = new Stack<>();
        //新头节点
        Node newHead=head;

        Node cur=head;
        Node next=null;
        Node pre=null;

        while (cur!=null){

            next=cur.next;
            stack.push(cur);

            if (stack.size()==k){

                //返回新头节点
                pre=reverseK(stack,pre,next);//pre上一组的尾节点  next下一组的头节点

                newHead=newHead==head?pre:newHead;//第一组的尾节点就是新的头节点
            }
            cur=next;
        }
        return newHead;
    }

    private Node reverseK(Stack<Node> stack, Node left, Node right) {
        Node cur = stack.pop();

        if (left!=null){
            left.next=cur;//与上一组拼接
        }

        Node next=null;
        while(!stack.isEmpty()){
            next=stack.pop();
            cur.next=next;
            cur=next;
        }
        cur.next=right;
        return cur;

    }


}
