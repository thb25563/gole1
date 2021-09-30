package com.snowballtech.algorithm.haibingtu;

import java.util.Scanner;


public class week36 {
    public static void main(String[] args) {
        System.out.print("请输入numRows：");
        Scanner sc = new Scanner(System.in);
        int numRows = sc.nextInt();
        // 调用thb（）函数进行杨辉三角处理，返回二维数组
        int[][] arr = thb(numRows);
        // 打印杨辉三角
        out(numRows, arr);

    }

    /**
     * 杨辉三角处理
     * 这道题的难度简单，下道题会增加些难度 Add by Max Yu 2021.09.08
     */
    public static int[][] thb(int num) {
        // 采用二维数组储存
        int[][] arr = new int[num][num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j <= i; j++) {
                //杨辉三角一行中，第一个和最后一个为1
                if (j == 0 || i == j) {
                    arr[i][j] = 1;
                }
                else {
                    //中间数为上一行临近两位数的和
                    arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
                }
            }
        }

        return arr;
    }

    public static void out(int numRows, int[][] arr) {
        if (numRows <= 30 && numRows >= -1) {//输入是有条件的
            //对杨辉三角并进行输出格式处理
            //输出左大括号
            System.out.print("[");
            for (int i = 0; i < numRows; i++) {
                System.out.print("\n[");
                for (int j = 0; j <= i; j++) {
                    System.out.print(arr[i][j]);
                    if (j >= 0 && j < i) {//对每一小项加上顿号“，”，第一项和最后一项没有不加
                        System.out.print(",");
                    }
                }
                if (i <= arr.length - 2) {//每大项加括号和顿号“】，”，最后一项没有为“】”
                    System.out.print("],");
                } else {
                    System.out.print("]");
                }
            }
            System.out.print("\n]");//右大括号

        } else {
            System.out.println("输入不满足");
        }
    }
}