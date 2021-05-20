package org.javaboy.vhr.util.xyfSF.BFS算法;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * TODO
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/26 11:05 上午
 */
public class 打开密码锁的最少步数 {
    //每个拨轮有十个数字：0-9
    //初始数字为0000
    //列表deadends包含一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转
    //字符串target代表可以解锁的数字，我们需要给出最小的旋转次数
    //如果无论如何不能解锁，返回-1

    /**
     * 穷举法：从0000开始，四个位置，每个位置可上可下，一共八种可能
     */

    //BFS框架，打印出所有可能的密码
    void BFS(String target){
        LinkedList<String> q = new LinkedList<>();
        q.offer("0000");
        while(!q.isEmpty()){
            int sz=q.size();

            for (int i = 0; i < sz; i++) {

                String cur = q.poll();

                //判断是否到达终点
                System.out.println(cur);

                //将一个节点的相邻节点加入队列
                for (int j = 0; j < 4; j++) {

                    //八种情况
                    String up=plusOne(cur,j);
                    String down=minusOne(cur,j);
                    q.offer(up);
                    q.offer(down);
                }

            }

            //在这里增加步数
        }
    }

    //将s[i]向下拨动一下
    private String minusOne(String cur, int j) {
        char[] ch = cur.toCharArray();
        if (ch[j]=='0'){
            ch[j]='9';
        }else{
            ch[j]-=1;
        }
        return new String(ch);
    }

    //将s[j]向上拨动一下
    private String plusOne(String cur, int j) {
        char[] ch = cur.toCharArray();
        if (ch[j]=='9'){ //字符'' 字符串""
            ch[j]='0';
        }else{
            ch[j]+=1;
        }

        return new String(ch);
    }

    /**
     * 优化上述的穷举算法
     * 问题1、会走回头路。
     * 问题2、没有终止条件
     * 问题3、没有对deadends的处理
     */
    int openLock(String[] deadends,String target){
        //记录需要跳过的死亡密码
        HashSet<String> deads = new HashSet<>();
        for (String s : deadends) {
            deads.add(s);
        }

        //记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();

        Queue<String> q = new LinkedList<>();

        //从起点开始开始启动广度优先搜索
        int step=0;
        q.offer("0000");
        visited.add("0000");

        while(!q.isEmpty()){
            int sz=q.size();

            /**
             * 将当前队列中所有节点向周围扩散
             */
            for (int i = 0; i < sz; i++) {

                String cur = q.poll();

                //判断是否到达终点
                if (deads.contains(cur))
                    continue;
                if (cur.equals(target))
                    return step;

                //将一个节点的未遍历相邻节点加入队列
                //八种情况
                for (int j = 0; j < 4; j++) {
                    String up=plusOne(cur,j);
                    if (!visited.contains(up)){
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)){
                        q.offer(down);
                        visited.add(down);
                    }
                }


            }
            //在这里增加步数
            step++;
        }

        //穷举完也没有找到目标密码，那就是找不到了
        return -1;
    }

    /**
     * 双向BFS优化
     * 传统的BFS框架是从起点开始向四周扩散，遇到终点时停止；
     * 双向BFS则是从起点和终点同时开始扩散，当两边有交集时停止
     * 局限性：你必须知道终点在哪
     */

}
