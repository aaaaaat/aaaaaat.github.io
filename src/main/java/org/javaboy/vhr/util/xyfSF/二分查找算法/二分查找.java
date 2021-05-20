package org.javaboy.vhr.util.xyfSF.二分查找算法;

/**
 * TODO 二分查找场景：寻找一个数、寻找左侧边界、寻找右侧边界
 * 细节：不等号是否应该带等号、mid是否应该加一等等
 * @author xyf
 * @version 1.0
 * @date 2021/4/26 4:06 下午
 */
public class 二分查找 {
    /**
     * 二分查找框架
     */
    int binarySearch(int[] nums,int target){
//        int left=0,right=...;
//
//        while(...){
//
//            int mid=left+(left+right)/2;
//
//            if (nums[mid]==target){
//                ...
//            }else if (nums[mid]<target){
//                left=..;
//            }else if (nums[mid]>target){
//                right=..;
//            }
//
//        }
//        return ...;
        return -1;
    }

    /**
     * 寻找一个数
     */
     public int binarySearchOne(int[] nums,int target){
         int left=0;
         int right=nums.length-1;
         // while(left <= right) 的终止条件是 left == right + 1
         // 写成区间的形式 就是 [right + 1, right]
         // 或者带个具体的数字进去 [3, 2]
         // 可⻅这时候 区间为空，因为没有数字既大于等于 3 又小于等于 2 的吧
         // while(left<right)的终止条件left=right
         while(left<=right){
             int mid=left+(right-left)/2;

             if (nums[mid]==target){
                 return mid;
             }else if(nums[mid]<target){
                 left=mid+1;
             }else if (nums[mid]>target){
                 right=mid-1;
             }
         }
         return -1;//循环结束没有找到 return-1
     }

     /**
      * 寻找target的左侧边界
      * 有序数组 nums = [1,2,2,2,3] ， target 为 2
      */
      public int left_bound(int[] nums,int target){
          if (nums.length==0) return -1;
          int left=0;
          int right=nums.length;
          //1、注意点--相当于左闭右开区间 [left, right)

          while( left < right ){ //2、注意点

              //终止条件left==right，每次循环的搜索空间是左闭右开区间 [left, right)
              //此时搜索空间[left, left) 为空，所以可以正确终止。

              int mid=(left+right)/2;

              if (nums[mid]==target){
                  right=mid;
                  //找到 target 时不要立即返回，而是缩小「搜索区间」的上界right
                  //在区间 [left, mid) 中继续搜索，即不断向左收缩，达到锁定左
                  //侧边界的目的。
              }

              else if (nums[mid]<target){
                  left=mid+1;
              } else if (nums[mid]>target){
                  right=mid;//3、注意点，左闭右开区间
              }
          }

          if (left == nums.length) return -1;
          return nums[left] == target ? left : -1;
          //return left;

          //4、注意点 left的含义（左侧边界）
          //数组[1 2 2 2 3]
          //索引 0 1 2 3 4  --2左边界返回1，即2对应的索引下标
          // --nums中小于target的元素个数
          // 函数的返回值(即 left 变量的值)取值区间是闭区间 [0, nums.length]
          //while (left < right) {
          // ...
          //}
          // target 比所有数都大
          //if (left == nums.length) return -1;
          //类似之前算法的处理方式
          //return nums[left] == target ? left : -1;
      }


      /**
       * 明白搜索区间这个概念，避免漏掉元素--模板
       */
      public int left_bound1(int[] nums,int target){
          //搜素区间为[left,right]
          int left=0,right=nums.length-1;
          while(left<=right){
              int mid=left+(left+right)/2;
              //if else...
          }
          return left;
      }

      /**
       * 搜索左侧边界，搜索区间是两端都闭的
       */
       public int searchLeftBound(int[] nums,int target){
           int left=0,right=nums.length-1;
           while(left<=right){
               //退出循环条件：left=right+1
               int mid=left+(left+right)/2;
               if (nums[mid]<target){
                   // 搜索区间变为 [mid+1, right]
                   left=mid+1;
               }else if (nums[mid]>target){
                   // 搜索区间变为 [left, mid-1]
                   right=mid-1;
               }else if (nums[mid]==target){
                   // 收缩右侧区间
                   right=mid-1;
               }

           }

           //由于 while 的退出条件是 left == right + 1
           // 所以当 target 比 nums 中 所有元素都大时
           // 会存在以下情况使得索引越界
           // nums[left]不等于target说明数组里面没有该数
           if (left>=nums.length       //这个判断放前面放置越界
                   ||
                   nums[left]!=target){
               return -1;
           }

           return left;
       }

       /**
        * 两端闭，寻找右边界
        */
       int right_bound(int[] nums, int target) { int left = 0, right = nums.length - 1; while (left <= right) {
           int mid = left + (right - left) / 2; if (nums[mid] < target) {
               left = mid + 1;
           } else if (nums[mid] > target) {
               right = mid - 1;
           } else if (nums[mid] == target) {
               // 这里改成收缩左侧边界即可
               left = mid + 1;
           }
       }
               // 这里改为检查 right 越界的情况，⻅下图
           if (right < 0 || nums[right] != target)
               return -1; return right;
       }





}
