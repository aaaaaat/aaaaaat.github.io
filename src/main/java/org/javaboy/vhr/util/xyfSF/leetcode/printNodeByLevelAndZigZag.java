package org.javaboy.vhr.util.xyfSF.leetcode;

//二叉树的按层打印和ZigZag打印

import java.util.LinkedList;
import java.util.Queue;

public class printNodeByLevelAndZigZag {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //按层打印需要标记行号，关键问题：如何知道该换行？
    //使用两个node型的变量last和nLast
    //last变量表示正在打印的当前行的最右节点，nLast表示下一行的最优节点

    public void printByLevel(Node head){
        if (head==null){
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();

        int level=1;
        Node last=head;
        Node nLast=null;

        //按层遍历二叉树
        queue.offer(head);

        System.out.println("Level "+level+" : ");
        level++;

        while(!queue.isEmpty()){
            //弹出队列头结点
            head = queue.poll();

            if (head.left!=null){
                queue.offer(head.left);
                nLast=head.left;
            }
            if (head.right!=null){
                queue.offer(head.right);
                nLast=head.right;
            }

            if (head==nLast&&!queue.isEmpty()){  //如果只有head头结点
                //换行 更新
                System.out.println("\nLevel "+level+" : ");
                level++;
                last=nLast;

            }

        }
    }

    //ZigZag打印的实现：S型打印
    public void printByZigZag(Node head){
        //使用一个双端队列dq实现LinkedList                                                             1
        //                                                                                     2        3
        //重点：如何确定每一层最后一个节点（正序的最后一个以及逆序的最后一个），关系到原则的切换           4        5    6
        //ZigZag的打印过程中，下一层最后打印的节点 是 当前层有孩子节点中最先进入dq（双端队列）的节点              7 8
        if (head==null){
            return;
        }
        //双端队列dq:  1    3 2    4 5 6     8 7
        LinkedList<Node> dq = new LinkedList<>();

        int level=1;

        boolean lr=true;

        Node last=head;
        Node nLast=null;

        dq.offer(head);

        //输出第一行：左到右 true 右到左 false
        printByLevelAndOrientation(level,lr);

        while(!dq.isEmpty()){

            if (lr){
                //原则1：一律从dq头部弹出
                head=dq.pollFirst();
                if (head.left!=null){
                    //下一层最后打印的节点 是 当前层有孩子节点中最先进入dq的节点
                    //最先通过nLast==null？判断，赋值后就不是第一次，后面再清空，赋值null
                    nLast=nLast==null?head.left:nLast;
                    dq.offerLast(head.left);
                }
                if (head.right==null){
                    nLast=nLast==null?head.right:nLast;
                    dq.offerLast(head.right);
                }

            }else{
                //原则2：一律从dq尾部弹出
                head=dq.pollLast();
                if (head.right!=null){
                    nLast=nLast==null?head.right:nLast;
                    dq.offerFirst(head.right);
                }
                if (head.left!=null){
                    nLast=nLast==null? head.left:nLast;
                    dq.offerFirst(head.left);
                }
            }

            System.out.print(head.value+" ");
            if (head==last&&!dq.isEmpty()){
                last=nLast;
                lr=!lr;    //lr取反
                nLast=null;
                System.out.println();//换行
                printByLevelAndOrientation(level++,lr);
            }



        }

    }

    private void printByLevelAndOrientation(int level, boolean lr) {
        System.out.print("level "+level+" from ");
        System.out.print(lr?"left to right ":"right to left");
    }
}
