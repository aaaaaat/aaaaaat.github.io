package org.javaboy.vhr.util.xyfSF.滑动窗口;

import java.util.HashMap;

/**
 * TODO 给定一个字符串，找出其中不含有重复字符的最长子串的长度
 *
 * @author xyf
 * @version 1.0
 * @date 2021/5/7 10:57 上午
 */
public class 最长无重复子串 {

    //答案必须是子串（连续）的长度
    // --子序列（不连续）

    public int lengthOfLongestSubstring(String s){

        HashMap<Character, Integer> window = new HashMap<>();

        int left=0,right=0;
        int res=0;       //返回不含重复字符的最长子串的长度

        char[] S = s.toCharArray();

        while(right<S.length){

            char c=S[right];
            //窗口右指针右移
            right++;

            //右指针滑动时,对窗口内数据的一系列更新
            window.put(c,window.getOrDefault(c,0)+1);

            //判断左侧窗口是否需要收缩(>1时，说明窗口内存在重复子串)
            while(window.get(c)>1){

                //读取窗口左指针指向的字符，用于后续判断更新
                char d=S[left];
                left++;

                //进行窗口左指针滑动时，窗口内数据的一系列更新
                window.put(d,window.get(d)-1);
            }

            //在收缩窗口完成后更新res
            // 因为窗口收缩的 while 条件是存在重复元素
            // 换句话说收缩完成后一定保证窗口中没有重复嘛。
            res=Math.max(res,right-left);
        }
        return res;
    }
}
