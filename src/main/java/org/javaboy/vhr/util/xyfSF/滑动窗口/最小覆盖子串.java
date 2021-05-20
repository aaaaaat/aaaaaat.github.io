package org.javaboy.vhr.util.xyfSF.滑动窗口;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.util.HashMap;
import java.util.Objects;

/**
 * TODO 给一个字符串s、一个字符串t，请在s里面找出：包含t所有字母的最小子串
 * 子串连续，子序列不连续
 * @author xyf
 * @version 1.0
 * @date 2021/5/6 3:25 下午
 */
public class 最小覆盖子串 {

    //1、在s中找到包含t中全部字母的一个子串
    //2、该子串一定是所有可能子串中最短的

    /**
     * 暴力解法
     * for (int i = 0; i < s.size(); i++)
     *     for (int j = i + 1; j < s.size(); j++)
     *
     *         if s[i:j] 包含 t 的所有字母:
     *             更新答案
     */
    public String minWindow(String s,String t){

        //needs和window相当于计数器，分别记录t中字符出现次数和窗口中的相应字符出现次数
        HashMap<Character, Integer> needs = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        // 字符串t：A、B、C  --》 needs={A:1,B:1,C:1}  window={A:0,B:0,C:0}
        // right右移每遇到一个A或者B或者C，window对应+1;left左移时，每遇到一个A或者B或者C，window对应-1

        char[] S = s.toCharArray();
        char[] T = t.toCharArray();

        for (char c : T) { //初始化
            needs.put(c, needs.getOrDefault(c, 0) + 1);
            //window.put(c, window.getOrDefault(c, 0));
        }

        int left=0,right=0;
        int valid=0;//三个字符满足要求时，valid = 3 = T.length();

        int start=0,len=Integer.MAX_VALUE;

        while(right<s.length()){

            char c=S[right];
            right++;

            //进行窗口内数据的一系列更新
            if (needs.containsKey(c)){ //如果遇到t中的字符
                window.put(c,window.getOrDefault(c,0)+1);

                //判断窗口中的单个字符是否满足要求，当全满足要求时，valid=3
                if (Objects.equals(window.get(c), needs.get(c))){
                    valid++;
                }
            }

            //判断左侧窗口是否需要收缩
            //直到窗口中搜索的字符串满足要求
            while(valid==needs.size()){

                //在这里更新最小覆盖子串 -- 小于len才更新
                if (right-left<len){
                    start=left;
                    len=right-left;
                }

                //每次增加left，我们都要更新一轮结果（第二步中的[left,right)不一定是最小）
                //跳出while循环时，表示窗口中字符串已经不满足要求

                //d是将移出窗口的字符
                //左移窗口
                char d=S[left];
                left++;

                //进行窗口内数据的一系列更新
                if (needs.containsKey(d)){ //如果移除的是t中字符
                    if (Objects.equals(window.get(d), needs.get(d))){
                        valid--;
                    }
                    window.put(d,window.get(d)-1);
                }

            }


        }

        //如果还是Integer.MAX_VALUE说明没找到
        return len==Integer.MAX_VALUE?"":s.substring(start,len);
        //1、双指针中的左右指针，初始化left=right=0，把索引左闭右开区间[left,right)成为一个窗口

        //2、不断增加right指针扩大窗口[left,right)，直到窗口中搜索的字符串符合要求（包含了t中所有的字符）

        //3、此时停止增加right，转而不断增加left指针缩小窗口[left,right)，直到窗口中的字符串不再符合要求
        //   同时，每次增加left，我们都要更新一轮结果（第二步中的[left,right)不一定是最小）

        //4、重复2、3步，直到right到达字符串s的尽头

    }



}
