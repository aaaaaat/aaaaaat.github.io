package org.javaboy.vhr.util;

import java.util.Stack;

//两个栈实现 队列的特点：先进先出  栈的特点：先进后出
public class TwoStackQueue {
    //包含队列的基本操作：add、poll、peek
    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public TwoStackQueue(){
        this.stackPush=new Stack<Integer>();
        this.stackPop=new Stack<Integer>();
    }

    //包含stackPush向stackPop倒数据操作:add、poll、peek三种方法中任选一种发生该操作均可
    private void pushToPop(){
        //需要做到两点：1、stackPop栈不为空不能倒入数据
        //2、stackPush中数据必须全部倒入pop栈中，使stackPush栈为空，才可以继续往stackPush栈中继续添加元素
        if (stackPop.empty()){
            while(stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }
    }

    //add方法
    public void add(int x){
        stackPush.push(x);
        pushToPop();
    }

    //poll方法
    public int poll(){
     if (stackPush.empty()&&stackPop.empty()){
          throw new RuntimeException("stack is empty");
     }
     pushToPop();
     return stackPop.pop();
    }

    //peek方法
    public int peek(){
        if (stackPop.empty()&&stackPush.empty()){
            throw new RuntimeException("stack is empty");
        }
        pushToPop();
        return stackPop.peek();
    }
}
