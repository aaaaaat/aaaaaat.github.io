package org.javaboy.vhr.util.xyfSF.leetcode;

public class 根据后序数组重建搜索二叉树 {
    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    //给定一个整型数组arr，已知其中没有重复值
    //判断arr是否可能是节点值类型为整型的搜索二叉树后序遍历的结果
    public boolean isPostArray(int[] arr){
        //后序遍历：左-右-根

        //如果一个数组是二叉树后序遍历的结果，那么头节点的值一定是是数组的最后一个元素

        //根据搜索二叉树的性质：比后序数组最后一个元素值小的数组会在数组的左边，比数组最后一个元素打的数组会在数组右边
        //接下来数组分为左边数组和右边数组，相当于二叉树分出了左子树和右子树，只要递归的进行如上判断即可

        if (arr==null||arr.length==0){
            return false;
        }
        return idPost(arr,0,arr.length-1);// 0:start  arr.length-1:end
    }

    private boolean idPost(int[] arr, int start, int end) {

        //如果可以遍历到start=end，说明均符合条件返回true
        if (start==end){
            return true;
        }

        int less=-1;    //寻找左子树数组下标

        int more=end;   //寻找右子树数组下标

        //寻找左右子树分离下标
        for (int i = start; i < end; i++) {
            if (arr[end]>arr[i]){
                less=i; //左子树根结点下标

            } else if(arr[end]<=arr[i]){ //数组无重复值
                more=more==end?i:more;  //右子树数组第一个大于arr[end]的值的下标
                // 第一次变为i后，后续一直不等于end，一直等于more原值
            }
        }

        if (less!=more-1){
            return false;
        }
        //右子树为空或者左子树为空
        if (less==-1||more==end){
            return idPost(arr,start,end-1);
        }

        return idPost(arr,start,less)&&idPost(arr,more,end-1);
    }


    //进阶：如果arr中没有重复值，且已知是一棵搜索二叉树的后序遍历结果
    //通过数组arr重构二叉树
    public Node posArrayToBST(int[] posArr){
        //一棵树的后续数组中最后一个值为二叉树头节点的值
        //数组左部分都比头节点的值小，用来生成头节点的左子树，剩下的部分用来生成右子树
        if (posArr==null){
            return null;
        }
        return posToBst(posArr,0,posArr.length-1);
    }

    private Node posToBst(int[] posArr, int start, int end) {

        if (start>end){
            return null;
        }
        Node head = new Node(posArr[end]);

        int less=-1;
        int more=end;

        for (int i = start; i < end; i++) {
            if (posArr[i]<posArr[end]){
               less=i;//不断更新i至最后左子树数组根结点对应下标
            }else{
                more=more==end?i:more;
                // 第一次posArr[i]>=posArr[end]变为i后，后续一直不等于end，一直等于more原值
            }
        }
        head.left=posToBst(posArr,start,less);
        head.right=posToBst(posArr,more,end-1);

        return head;
    }

}
