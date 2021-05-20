package org.javaboy.vhr.util.xyfSF.leetcode;

/**
 * TODO
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/12 6:43 下午
 */
public class 无重复最长子串 {
    public int UniqueLongestSubString(String str){
        if (str==null||str.length()==0){
            return 0;
        }
        char[] chas=str.toCharArray();
        //滑动窗口
        int[] map=new int[256];
        //建立一个256位大小的整型数组来代替哈希表
        // 这样做的原因是ASCII表共能表示256个字符，所以可以记录所有字符
        // 然后我们需要定义两个变量res和left，其中res用来记录最长无重复子串的长度
        // left指向该无重复子串左边的起始位置
        // 然后我们遍历整个字符串，对于每一个遍历到的字符
        int res=0;
        int left=0;

        for (int i = 0; i < chas.length; i++) {
              if (map[chas[i]]==0||map[chas[i]]<left){  //abbca 第一个a由于b被排除在外，（不在滑动窗口内可以加进来）

                  //右边界向右滑动的两种情况
                  res=Math.max(res,i-left+1);

              }else{
                  //左边界向右滑动
                  left=map[chas[i]];
              }
              map[chas[i]]=i+1;//左边最近重复字符右边第一个字符的索引
        }
        return res;
        // 如果哈希表中该字符串对应的值为0，说明没有遇到过该字符
        // 则此时计算最长无重复子串，i - left +１
        // 其中ｉ是最长无重复子串最右边的位置，left是最左边的位置

        // 还有一种情况也需要计算最长无重复子串，就是当哈希表中的值小于left
        // 这是由于此时出现过重复的字符，left的位置更新了
        // (去掉的方法并不需要将左边界left一位一位向右遍历查找
        //     由于我们的HashMap已经保存了该重复字符最后出现的位置
        //     所以直接移动left指针就可以了)
        // 如果又遇到了新的字符，就要重新计算最长无重复子串。
        // 最后每次都要在哈希表中将当前字符对应的值赋值为i+1。
    }
    public int LongestSubString(String s){
        if (s==null||s.length()==0){
            return 0;
        }
        char[] c = s.toCharArray();
        int[] map = new int[256];

        int left=0;
        int res=0;

        for (int i = 0; i < c.length; i++) {
            if (map[c[i]]==0||map[c[i]]<left){
                res=Math.max(res,i-left+1);
            }else{
                //左边界向右滑动
                left=map[c[i]];
            }
            map[c[i]]=i+1;
        }
        return res;
    }
}
