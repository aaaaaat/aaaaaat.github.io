package org.javaboy.vhr.util;

import java.util.Stack;

public class reverse {
    //递归函数实现栈中元素的逆序
    //递归函数一：将栈stack的栈底元素返回并移除---1、2、3顺序压入栈，返回1，顺序将2、3压入栈。
    public static int moveStackBottom(Stack<Integer> stack){

        int result=stack.pop();

        if (stack.empty()){
            return result;    // 返回 1    不压入
        }else{
            // 返回 2、3    压入
            int last=moveStackBottom(stack); //递归调用moveStackBottom，该函数的传入值依次为[3,2,1]、[2,1]、[1]，stack返回值为1（即last=1），该行程序已执行完成，接下来执行下面两行程序
            stack.push(result);
            return last;      //return last的值一直为1
        }
    }

    //递归函数二：逆序一个栈---递归调用递归函数一，依次返回1、2、3（3最后返回，此时栈为空），依次将3、2、1重新压入栈
    public static void reverse(Stack<Integer> stack){
        if (stack.empty()){
            return;
        }
        int i=reverse.moveStackBottom(stack);//i的值依次为1、2、3，stack依次变化为[3,2,1]、[3、2]、[3]、[]
        reverse(stack);
        stack.push(i);
    }

    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int i=reverse.moveStackBottom(stack);
    }
}
