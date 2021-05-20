package org.javaboy.vhr.util;

import java.util.*;


public class TestCode {

    private char[] getNumToChar(char ch){
        char[] chars = new char[2];
        switch (ch){
            case '2':
                chars[0]='a';
                chars[1]='b';
                chars[2]='c';
                return chars;
            default: return chars;
        }
    }
    //数组索引对应字符串
    String[] letter_map = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    List<String> res=new ArrayList<>();

  public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  //链表问题在头结点前面添加一个哑节点（dummy node）
  // 它的next指针指向链表的头节点，形成通用情况
  // 不需要再对头节点（头节点为空，或仅有一个头节点）进行特殊的判断
  //获取链表长度
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        //删除链表的倒数第n个节点，并且返回链表的头结点
      ListNode dummy = new ListNode(0, head);
      int length=getLengthList(head);
      ListNode cur=dummy;
      for (int i = 0; i < length - n + 1; i++) {
          cur=cur.next;
      }
      cur.next=cur.next.next;
      ListNode ans=dummy.next;
      return ans;
    }

    private int getLengthList(ListNode head) {
      int length=0;
      while(head==null){
          ++length;
          head=head.next;
      }
      return length;
    }
    //倒数第n个，利用栈的先进后出原则实现
    public ListNode removeNthFromEnd2(ListNode head,int n){
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(0, head);
        ListNode cur=dummy;
        while(cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next=prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
    //进阶:双指针(倒数第二个，first指针与second指针间隔n-1个节点)
    // d->1->2->3->4->null  1->2->3->4->null  (需要的是节点2(被删除节点的前一个节点))
    // |        |              |        |
    public ListNode removeNthFromEnd3(ListNode head,int n){
        ListNode dummy = new ListNode(0, head);
        ListNode first =head;
        ListNode second=dummy;
        for (int i = 0; i < n; i++) {
            first=first.next;
        }
        while(first!=null){
            first=first.next;
            second=second.next;
        }
        second.next=second.next.next;
        ListNode ans=dummy.next;
        return ans;
    }

    public static boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        stack.push("");
        while (!stack.isEmpty()){
            for (int i = 0; i < s.length(); i++) {

                if (s.charAt(i)=='}'){
                    if (!stack.peek().equals("{")){
                        return false;
                    }else{
                        stack.pop();
                    }
                }
                else if (s.charAt(i)==')'){
                    String peek = stack.peek();
                    if (!peek.equals("(")){
                        return false;
                    }else{
                        stack.pop();
                    }
                }
                else if (s.charAt(i)==']'){
                    if (!stack.peek().equals("[")){
                        return false;
                    }else{
                        stack.pop();
                    }
                }
                else {
                    stack.push(String.valueOf(s.charAt(i)));
                }
            }
            if (stack.pop().equals("")){
                break;
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * @param s
     * @return a
     * author a
     * @date 2021/3/4 3:21 下午
     */
   public static boolean isValid2(String s){
      int n=s.length();
      if (n%2==1){
          return false;
      }
        HashMap<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');

        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (pairs.containsKey(c)){
                if (stack.isEmpty()||stack.peek()!=pairs.get(c)){
                    return false;
                }
                stack.pop();
            }else{
                stack.push(c);
            }
        }
        //如果队列为空，说明对称
        return stack.isEmpty();
    }

   /**
    *
    */
   public static void main(String[] args) {
//        String s="()";
//        boolean valid = isValid(s);
//        System.out.println(valid);

        ListNode listNode = new ListNode();
        ListNode pre=listNode;
        System.out.println(listNode==pre);
        pre.next=new ListNode(2);
        System.out.println(listNode.next.toString());
    }
}
