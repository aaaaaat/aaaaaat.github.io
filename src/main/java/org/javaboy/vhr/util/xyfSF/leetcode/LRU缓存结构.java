package org.javaboy.vhr.util.xyfSF.leetcode;

/**
 * TODO 设计LRU缓存结构
 * 该结构在构造时确定大小，假设大小为K，并且有如下两个功能
 * set（key,value）将记录（key，value）插入该结构
 * get（key）返回key对应的value值
 * @author xyf
 * @version 1.0
 * @date 2021/4/12 10:39 下午
 */
public class LRU缓存结构 {
    //要求：
    //1、set和get方法的时间复杂度为O（1）     --双端队列+哈希表
    //2、某个key的set或者get操作一旦发生，认为这个key的记录成了最长使用的
    //3、当缓存大小超过k时，移除最不经常使用的记录，即set或get最久远的  --双端队列

    public class Node<V>{
        public V value;
        public Node<V> last;
        public Node<V> next;
    }

    //实现一种双向链表结构NodeDoubleLinkedList
    //在该结构中优先级最低的节点是head（头），优先级最高的节点是tail（尾）

    public class NodeDoubleLinkedList<V>{

        private Node head;
        private Node tail;

    //构造函数
    public NodeDoubleLinkedList(){
        this.head=null;
        this.tail=null;
    }

    //三种操作
    //1、set-当加入一个节点时，将新加入的节点放在这个链表的尾部（变成最新使用，优先级最高），并将这个节点设置为新的尾部--addNode
    public void addNode(Node<V> newNode){
        if (newNode==null){
            return;
        }
        if (this.head==null){  //包含尾指针为空
            this.head=newNode;
            this.tail=newNode;
        }else{
            this.tail.next=newNode;
            newNode.last=this.tail;//双向链表
            this.tail=newNode;
        }
    }
    //2、get-任意节点都可以分离出来，并放到整个链表的尾部(变成最常使用，优先级最高) --moveNodeToTail

       public void moveNodeToTail(Node<V> node){
             if (this.tail==node){
                 return;
             }
             if (this.head==node){
                 this.head=node.next;
                 this.head.last=null;
             }else{
                 node.last.next=node.next;
                 node.next.last=node.last;
                 this.tail.next=node;
                 this.tail=node;
             }

        }


    //3、remove-移除Node节点并返回这个节点，然后将head设置成老head节点的下一个--removeHead
        public Node<V> removeHead(){
             if (this.head==null){
                 return null;
             }
             Node<V> res=this.head;//返回值
             if(this.head==this.tail)//如果只剩下一个节点
             {
                 this.head=null;
                 this.tail=null;
             }else {
                 this.head=res.next;
                 //分离res
                 res.next=null;
                 this.head.last=null;
             }
             return res;
        }


    }
}
