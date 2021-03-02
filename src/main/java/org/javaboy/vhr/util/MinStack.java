package org.javaboy.vhr.util;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> StackData;
    private Stack<Integer> StackMin;

    /** initialize your data structure here. */
    public MinStack() {
        this.StackData=new Stack<Integer>();
        this.StackMin=new Stack<Integer>();
    }
    //往栈中压入元素
    public void push(int x) {
      this.StackData.push(x);
       //如果最小栈为空
        if (this.StackMin.isEmpty()){
            this.StackMin.push(x);
        }else if(x <= this.getMin()){
            this.StackMin.push(x);  //注意不要想着把StackMin中原最小元素弹出，因为以后pop弹出元素后，仍然需要查询最小元素
        }
    }

    public int pop() {
      //StackData栈为空
        if (this.StackData.isEmpty()){
            throw new RuntimeException("栈为空");
        }
        //取StackData栈顶元素即为value与StackMin栈做比较（根据压入元素规则，StackData中元素一定大于等于StackMin中元素）
        int value=this.StackData.pop();

        //如果等于最小元素，则弹出StackMin中元素
        if (value == this.getMin()){
            StackMin.pop();
        }
        return value;
    }

    public int getMin() {
         if (this.StackMin.isEmpty()){
             throw new RuntimeException("栈为空");
         }
         return this.StackMin.peek();//仅返回值，无须对栈进行操作，peek（）
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
