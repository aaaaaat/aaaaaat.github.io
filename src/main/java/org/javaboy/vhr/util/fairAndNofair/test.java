package org.javaboy.vhr.util.fairAndNofair;

import jdk.nashorn.internal.ir.CallNode;

import java.util.*;

/**
 * TODO
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/13 4:22 下午
 */
public class test {
    private static HashMap<String, LinkedList<Node>> m=new HashMap<>();
    private static LinkedList<Node> n=new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n.add(new Node(0,"0"));
        //调度操作数量
        int N = sc.nextInt();
        //接下来有N行
        for (int i = 0; i < N; i++) {
            int which=sc.nextInt();
            if (which==1){
                //增加车厢x，属于y系列(需要找到y系列的最后一个节点对应下标)
                int x=sc.nextInt();
                String y=sc.next();
                addCar(x,y);
            }else if (which==2){
                //替换x系列和y系列在原车厢的位置（需要找到x系列和y系列的第一个节点对应下标）
                String x=sc.next();
                String y=sc.next();
                swapCar(x,y);
            }
        }
        Node node = n.get(1);
        while(node.next!=null){
            System.out.print(node.x+" ");
            node=node.next;
        }
        System.out.print(node.x);
    }
    public static class Node{
        int x;
        String y;
        Node next;
        public Node(int x, String y) {
            this.x=x;
            this.y=y;
        }
    }
    private static void addCar(int x, String y) {
        Node node = new Node(x, y);
        if (!m.containsKey(y)){
            n.getLast().next=node;
            n.add(node);
            LinkedList<Node> JL = new LinkedList<>();
            JL.add(node);
            m.put(y,JL);
        }else{ //map中有该节点记录
            LinkedList<Node> list = m.get(y);
            Node last = list.getLast();
            for (Node e : n) {
                if (e.x==last.x){
                    Node tmp=e.next;
                    e.next=node;
                    node.next=tmp;
                }
            }
            list.add(node);
        }

    }
    private static void swapCar(String x, String y) {
       if (m.containsKey(x)&&m.containsKey(y)){
           LinkedList<Node> list1 = m.get(x);
           LinkedList<Node> list2 = m.get(y);
           Node first1 = list1.getFirst();
           Node last1 = list1.getLast();
           Node first2 = list2.getFirst();
           Node last2 = list2.getLast();
           if (first1.x==n.get(0).next.x){
               n.get(0).next=first2;
               Node cur=n.get(0).next;
               while (cur.x!=first2.x){
                   cur=cur.next;
               }
               Node next2 = last2.next;
               Node next1=last1.next;
               last2.next=next1;
               last1.next=next2;
               cur.next=first1;
           }else {

           }
       }
    }




}
