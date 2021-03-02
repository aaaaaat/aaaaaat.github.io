package org.javaboy.vhr.util;

public class josephusKill1 {
    public class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value=data;
        }
    }
    //环形单链表约瑟夫问题
    public Node josephusKill1(Node head,int m){

        if( head == null || head.next == head || m<1 ){
            return head;
        }
        //环形链表：1->2->3->4->5  需要两个节点head节点1和last节点5
        //环形单链表中遍历每个节点，不断转圈，不断让每个节点报数

        //环形链表找到last节点
        Node last=head;
        while(last.next!=null){
            last=last.next;
        }
        int count=0;


        return head;
    }
}
