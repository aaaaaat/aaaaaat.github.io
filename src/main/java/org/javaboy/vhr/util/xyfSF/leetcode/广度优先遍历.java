package org.javaboy.vhr.util.xyfSF.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author xyf
 * @version 1.0
 * @date 2021/3/8 8:55 上午
 */
public class 广度优先遍历 {
    //自己编写节点类
    //结合队列数据结构
    public List<String> generateParenthesis(int n){
        List<String> res=new ArrayList<>();
        if (n==0){
            return res;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(new Node("",n,n));
        
        while (!queue.isEmpty()){
            //取队列头部
            Node curNode = queue.poll();

            //left和right均为0时，生成合格的左右括号字符串
            if (curNode.left==0&&curNode.right==0){
                res.add(curNode.res);
            }

            //左括号放入时，没有限制，只需要左括号剩余数量大于0
            if (curNode.left>0){
                queue.offer(new Node(curNode.res+"(",curNode.left-1,curNode.right));
            }
            //右括号放入时，需要左括号剩余数量小于右括号剩余数量
            if (curNode.right>0&&curNode.left<curNode.right){
                queue.offer(new Node(curNode.res+")",curNode.left,curNode.right-1));
            }

        }
        return res;
    }
}
class Node{
    String res;
    int left;
    int right;

    public Node(String res, int left, int right) {
        this.res = res;
        this.left = left;
        this.right = right;
    }
}