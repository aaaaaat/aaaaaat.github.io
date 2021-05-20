package org.javaboy.vhr.util.xyfSF.滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * TODO 维护一个窗口，不断滑动，更新答案
 *
 * @author xyf
 * @version 1.0
 * @date 2021/5/6 9:42 上午
 */
public class 滑动窗口 {
    /**
     * 大概逻辑-模板
     */
    public void 滑动窗口(int[] s){
        int left=0,right=0;
        ArrayList<Integer> window = new ArrayList<>();
        while (right<s.length){
            //增大窗口
            window.add(s[right]);
            right++;

           // while (window needs shrink){ //窗口需要收缩

                    //缩小窗口
                    window.remove(s[left]);
                    left++;

           // }


        }
    }

    /**
     * 细节：
     * 1、如何向窗口中添加新元素
     * 2、如何缩小窗口
     * 3、在滑动窗口的哪个阶段更新结果
     */
    public void slidingWindow(String s,String t){
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left=0,right=0;
        int valid=0;

        while(right<s.length()){
            //c是将移入窗口的字符
            char c=s.toCharArray()[right];
            //右移窗口
            right++;
            //进行窗口内数据的一系列更新
            //...

            /*** debug 输出的位置 ***/
            System.out.println("window: ["+left+", "+right+")" );
            /********************/

                //判断左侧窗口是否要收缩
               // while(window needs shrink){
                    //d是将要移出窗口的字符
                    char d =s.toCharArray()[left];
                    //左移窗口
                    left++;
                    //进行窗口内数据的一系列更新
                    //...
               // }
        }
    }

}
