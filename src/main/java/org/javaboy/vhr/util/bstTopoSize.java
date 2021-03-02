package org.javaboy.vhr.util;

import java.util.HashMap;

//找到二叉树中符合搜索二叉树条件的最大拓扑结构
public class bstTopoSize {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public int bstTopoSize(Node head){
        if (head==null){
            return 0;
        }
        //时间复杂度O（N*N）
        int max=maxTopo(head,head);
        max=Math.max(bstTopoSize(head.left),max);
        max=Math.max(bstTopoSize(head.right),max);
        return max;
    }

    private int maxTopo(Node h, Node n) {   //h:以h为头结点 n：从n节点开始遍历寻找最大拓扑结构
        //以节点h为头结点的树中，在拓扑结构中也必须以h为头结点的情况下
        //怎么找到符合搜索二叉树条件的最大结构
        if (h!=null&&n!=null&&isBSTNode(h,n,n.value)){
            return maxTopo(h,n.left)+maxTopo(h,n.right)+1;//最大拓扑结构树的大小=左最大+右最大+1
        }
        return 0;
    }

    private boolean isBSTNode(Node h, Node n, int value) {

        //base case 终止条件
        if (h==null){
            return false;
        }
        if (n==null){
            return false;
        }

        //h向下走，判断是否能走n
        return isBSTNode(h.value>value?h.left:h.right,n,value);
    }

    public class Record{

        public int l;
        public int r;

        public Record(int left, int right) {
            this.l = left;
            this.r = right;
        }
    }
    public int bstTopoSize1(Node head){
        //时间复杂度为O（N）的方法
        //拓扑贡献记录：（a,b），a：节点的左子树可以为当前头结点的拓扑贡献几个节点，b：节点的右子树可以为当前头节点的拓扑贡献几个节点
        //小树的记录整合成大树的记录，从而求出整棵树中符合搜索二叉树条件的最大拓扑的大小
        //利用后序遍历：先生成其左孩子节点的记录，然后是右孩子节点的记录
        //接着把两组记录修改成为这个节点为头的拓扑贡献记录

        //每个头结点都有其对应的拓扑贡献记录，并可以进行更新
        HashMap<Node, Record> map = new HashMap<>();

        //后序遍历二叉树，小树整合成大树的记录
        return posOrder(head,map);
    }

    //后序遍历函数
    private int posOrder(Node h, HashMap<Node, Record> map) {

        if (h==null){
            return 0;
        }

        int ls=posOrder(h.left,map);
        int rs=posOrder(h.right,map);

        //更新节点拓扑贡献记录，执行下一步的时候，已经来到树的左下角（null）

        //左子树更新
        modifyMap(h.left,h.value,map,true);
        //右子树更新
        modifyMap(h.right,h.value,map,false);

        Record lr = map.get(h.left);
        Record rr = map.get(h.right);

        //对lr和rr进行判空 节点h的左右子树均为null，未放进map中
        int lbst=lr==null?0:lr.l+lr.r+1;
        int rbst=rr==null?0:rr.l+rr.r+1;

        map.put(h,new Record(lbst,rbst));//叶节点放入记录（0，0）

        return Math.max(lbst+rbst+1,Math.max(ls,rs));
    }

    private int modifyMap(Node n, int v, HashMap<Node, Record> m, boolean s) {
        //终止条件
        if (n==null||(!m.containsKey(n))){

            //如果map中不包含n记录，例如：
            //         4
            //     2       5
            // null null
            //后序遍历从节点2到节点4，再到节点4的右结点节点5不为空，但节点5在map中并未存储，执行93行94行代码，生成节点5record
            return 0;
        }

        Record r = m.get(n);

        //左右子树两种情况：通过boolean区分

        //左子树的值大于父结点，右子树的值小于父结点，移除，并返回父结点的初始值+1
        if ( ( s && n.value>v ) || (!s && n.value<v)){
            //移除该子树
            m.remove(n);
            return r.l+r.r+1;

        }else{
            //n是头结点的左结点或者右结点
            //左子树遍历右边界，右子树遍历左边界
            //返回值为以遍历右边界，直到需要删除的头结点大小
            int minus=modifyMap(s?n.right:n.left,v,m,s);

            //如果是左子树，则所有右边界均减去该返回值
            if (s){
                r.r=r.r-minus;
            }else{
                r.l=r.l-minus;
            }

            //更新至n，n是头结点的左节点或者右结点
            m.put(n,r);

            return minus;
        }

    }

    //更新节点拓扑贡献记录函数


}
