package org.javaboy.vhr.util;

public class 在二叉树中找到一个节点的后继节点 {

    public class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    //只给出一个二叉树中的某个节点node，请实现返回node的后继节点的函数
    //在二叉树的中序遍历的序列中，node的下一个节点叫作node的后继节点
    //普通解法：根据parent寻找树的根结点，然后进行中序遍历，寻找该节点的后继节点
    //时间复杂度和空间复杂度均为O（N）
    public Node getNextNode(Node node){

        if (node == null){
            return node;
        }

        //最优解法不必遍历所有的节点
        //如果node节点和node后继节点之间的实际距离为L，则时间复杂度为O（L）
        if (node.right!=null){
            //情况一：如果node有右子树，那么后继节点就是右子树上最左边的节点
            return getLeftNode(node.right);
        }else{
            //情况二
            Node parent=node.parent;

            //如果node没有右子树，先看node是不是node父结点的左孩子节点，如果是左孩子节点，那么node父结点就是node的后继节点

            //如果是右孩子节点，向上寻找node的后继节点，假设向上移动的节点是s，s的父节点计为p，如果s是p的左孩子节点
            //那么节点p就是node的后继节点，否则就继续向上移动
            while(parent!=null&&parent.left!=node){ //循环到parent.left=node
                node=parent;
                parent=node.parent;
            }
            //parent.left=node
            //parent就是后继节点
            return parent;
        }

    }

    private Node getLeftNode(Node node) {
        if (node==null){
            return node;
        }
        while(node.left!=null){
            node=node.left;
        }
        return node;
    }
}
