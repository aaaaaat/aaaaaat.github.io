package org.javaboy.vhr.util;

public class t1树是否含有t2树拓扑结构完全相同的子树 {
    //子树完全相同，不能只含有t2
    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public boolean isSubTree(Node t1,Node t2){
        //先序遍历序列化
        String t1Str=serialByPre(t1);
        String t2Str=serialByPre(t2);


        //验证序列化后的t2str是否序列化后的t1str的子串，利用KMP算法
        return getIndexOf(t1Str,t2Str)!=-1; //!=-1说明true

    }

    //KMP
    private int getIndexOf(String s, String m) {
        if (s==null||m==null||s.length()<m.length()||m.length()<1){
            return -1;
        }
        return 0;
    }

    private String serialByPre(Node head) {
        if (head==null){
            return "#!";
        }
        //先序遍历
        //根
        String res=head.value+"!";
        //左
        res+=serialByPre(head.left);
        //右
        res+=serialByPre(head.right);
        return res;
    }
}
