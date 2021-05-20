package org.javaboy.vhr.util.xyfSF.滑动窗口;

import java.util.HashMap;

/**
 * TODO
 *
 * @author xyf
 * @version 1.0
 * @date 2021/5/7 9:40 上午
 */
public class 字符串排列 {
    /**
     * 字符串排列
     *
     * 给定两个字符串s1和s2，写一个函数来判断s2是否包含s1的排列
     * 第一个字符串的排列之一是第二个字符串的子串
     * 输入的s1可以包含重复字符
     *
     */
    //判断s中是否存在t的排列
    public boolean checkInclusion(String t,String s){
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        char[] T = t.toCharArray();
        char[] S = s.toCharArray();

        //初始化need
        for (char c : T) {
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int left=0,right=0;
        int valid=0;
        //遍历字符串S
        while(right<S.length){

            char c=S[right];
            //窗口右指针右移
            right++;

            //进行窗口数据的一系列更新
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                //如果窗口window里字符的value达到要求
                if (window.get(c)==need.get(c)){
                    valid++;
                }
            }

            //移动left缩小窗口的时机：当窗口大小大于T.size()
            //因为排列嘛，显然长度应该是一样的
            while(right-left>=t.length()){

                //在这里判断是否找到合法的子串
                //valid==need.size()说明窗口内是一个合法的排列，立即返回true
                if (valid==need.size()){
                    return true;
                }

                //左移left
                char d=S[left];
                left++;

                //左移窗口时的状态更新
                if (need.containsKey(d)){

                    if (window.get(d)==need.get(d)){
                        //如果need中需要的字符串被移走了
                        valid--;
                    }

                    window.put(d,window.get(d)-1);

                }

            }

        }
        return false;
    }
}
