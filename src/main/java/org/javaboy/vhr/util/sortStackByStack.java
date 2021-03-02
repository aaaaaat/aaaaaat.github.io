package org.javaboy.vhr.util;

import java.util.Stack;

public class sortStackByStack {
    //将stack栈从顶到底从大到小排序，help栈从顶到底从小到大排序
    public static void sortStackByStack(Stack<Integer> stack){

        Stack<Integer> help = new Stack<>();
        //排序栈stack 辅助栈help
        while(!stack.empty()){
            //如果help栈为空则直接压入
            //如果cur小于等于help栈顶元素，则直接压入栈
            //如果cur大于help栈顶元素，则弹出help栈元素直至cur小于等于help栈顶元素(不能申请新的数据结构)
            int cur=stack.pop();
            while(!help.empty()&&help.peek()<cur){
                //将help弹出元素寄存在stack栈中（不能申请新的数据结构）
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.empty()){
            stack.push(help.pop());
        }
    }
}
