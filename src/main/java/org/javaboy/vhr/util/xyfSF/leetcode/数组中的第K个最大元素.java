package org.javaboy.vhr.util.xyfSF.leetcode;

/**
 * TODO
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/12 9:09 下午
 */
public class 数组中的第K个最大元素 {
     //快排求解
     public static void main(String[] args) {

     }
     public int findKthLargest(int[] nums,int k){
         return quickSort(nums,0,nums.length-1,k);
     }

    private int quickSort(int[] nums, int left, int right, int k) {

         int i=left;
         int j=right;
         int pivot=nums[right];//选取并保存基准值
         int len=right-left+1;
         while(left<right){
             //左右横跳，向中收缩
             while(i<j&&nums[i]>=pivot){  //左往右找第一个比pivot小的数
                 i++;
             }
             if (i<j){
                 nums[j--]=nums[i];    //左往右找第一个比pivot小的数放在j处（则原i处可以放置新的元素，值已经保留）
             }

             while (i<j&&nums[j]<=pivot){  //右往左找第一个比pivot大的数
                 j--;
             }
             if (i<j){
                 nums[i++]=nums[j];
             }
         }
         //i=j时
        nums[i]=pivot;

         //基本快速排序
//        quickSort(nums,left,i-1,k);
//        quickSort(nums,i+1,right,k);


         //此时pivot索引为i，i-left+1表示pivot是第几大的数
        int which_max=i-left+1;
        //如果i-left+1=k 找到
        if (which_max==k){
            return pivot;
        }

        //如果i-left+1>k 左找
        else if (which_max>k){
            return quickSort(nums,left,i-1,k);
        }
        //如果i-left+1<k 右找
        else{
            return quickSort(nums,i+1,right,k-which_max);
        }

    }
}
