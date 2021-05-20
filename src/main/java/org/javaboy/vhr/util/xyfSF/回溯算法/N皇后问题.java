package org.javaboy.vhr.util.xyfSF.回溯算法;

import jdk.nashorn.internal.ir.CallNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * TODO 给你一个N*N的棋盘，让你放置N个皇后，使得它们不能互相攻击
 * 皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位
 * @author xyf
 * @version 1.0
 * @date 2021/4/26 9:06 上午
 */
public class N皇后问题 {


    static List<List<List<String>>> res=new LinkedList<>();

    static List<List<String>> board=new LinkedList<>();

    // 决策树
    // 每一层表示棋盘上的每一行
    // 每个节点可以做出的选择是，在该行的任意一列放置一个皇后
    public static List<List<List<String>>> solveNQueens(int n){
        //'.'表示空，'Q'表示皇后，初始化空棋盘
        for (int j = 0; j < n; j++) {
            List<String> row=new LinkedList<>();
            for (int i = 0; i < n; i++) {
                row.add(".");
            }
            board.add(row);
        }
        backTrack(board,0);   //从决策树第一层0开始

        return res;  //所有结果
    }

    private static void backTrack(List<List<String>> board, int row) {

        //结束条件
        if (row==board.size()){
            List<List<String>> boardCopy=new LinkedList<>();
            for (int j = 0; j < board.size(); j++) {
                List<String> row1=new LinkedList<>();
                for (int i = 0; i <  board.size(); i++) {
                    row1.add(".");
                }
                boardCopy.add(row1);
            }
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.get(i).size(); j++) {
                    boardCopy.get(i).set(j,board.get(i).get(j));
                }
            }
            res.add(boardCopy);
            return;
        }

        int n=board.get(row).size();
        for (int col = 0; col < n; col++) {  //决策树从第0行开始,col从 0 -> n

            //排除不合法选择
            if (!isvalid(board,row,col)){
                continue;
            }

            //做选择
            board.get(row).set(col,"Q");

            //进入下一行决策
            backTrack(board,row+1);

            //撤销选择
            board.get(row).set(col,".");
        }




    }

    //是否可以在board上放置皇后
    //皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位
    private static boolean isvalid(List<List<String>> board, int row, int col) {

        int n=board.size();

        //行不需要判断，不在同一行

        //检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if (Objects.equals(board.get(i).get(col), "Q")){
                return false;
            }
        }

        //检查右上方是否有冲突
        for (int i=row-1,j=col+1;i>=0&&j<n;i--,j++){
            if (Objects.equals(board.get(i).get(j), "Q")){
                return false;
            }
        }

        //检查左上方是否有冲突
        for (int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){
            if (board.get(i).get(j)=="Q"){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        solveNQueens(4);
        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j < res.get(i).size(); j++) {
                for (int i1 = 0; i1 < res.get(i).get(j).size(); i1++) {
                    System.out.print(res.get(i).get(j).get(i1).toString());
                }
                System.out.println();
            }
        }
    }
}
