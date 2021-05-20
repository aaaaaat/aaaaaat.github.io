package org.javaboy.vhr.util.xyfSF.leetcode;

/**
 * TODO
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/12 9:05 下午
 */
public class 反转链表 {
    public class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value=data;
        }
    }
    public Node reverseNode(Node head){
        if (head==null||head.next==null){
            return head;
        }
        Node pre=null;
        Node next=null;
        while(head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return head;
    }
}
