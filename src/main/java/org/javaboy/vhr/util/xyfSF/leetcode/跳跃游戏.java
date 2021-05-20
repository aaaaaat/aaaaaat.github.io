package org.javaboy.vhr.util.xyfSF.leetcode;

import java.util.Scanner;

/**
 * TODO
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/12 10:12 上午
 */
public class 跳跃游戏 {
    private static int[] data;

    public static int jump(int[] arr){
           if(arr.length==0||arr==null){
               return 0;
           }
           int jump=0;
           int cur=0;
           int next=0;
        for (int i = 0; i < arr.length; i++) {
            if (cur<i){
                jump++;
                cur=next;
            }
            next=Math.max(next,i+arr[i]);
        }
        return jump;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            int n=sc.nextInt();
            data = new int[n];

            for (int i = 0; i < n; i++) {
                data[i]=sc.nextInt();
            }

            jump(data);

            for (int i = 0; i < n; i++) {
                System.out.println(data[i]);
            }
        }



    }
}
