package com.snowballtech.algorithm.huashi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Week35 {
    public static void main(String[] args) {
        // 缺少前提条件判断逻辑  Add by Max Yu 2021.08.31
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入数组的长度length（0 <= length <= 10^5）");
        int length = sc.nextInt();

        Integer arr[] = new Integer[length];
        System.out.println("请输入数组的每一个元素(默认为升序)，长度为：" + length);
        for (int i = 0; i < length; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("你输入的数组为" + Arrays.toString(arr));

        System.out.println("请输入您要查找的数字：");
        int target = sc.nextInt();

        select2(arr, target);
    }


    public static void select(Integer arr[], int target) {
        // 变量命名可读性不强，这里可以按照题意命名开始位置索引和结束位置索引变量,比如: int startIndex = -1 ,endIndex = -1;
        int index;
        int max = -1;
        List<Integer> list = Arrays.asList(arr);
        // 其实这里, 我更希望笔者用代码实现查找算法, 比如二分法查找，而不是用List.contains方法 Add by Max Yu 2021.08.31
        if (list.contains(target)) {
            index = list.indexOf(target);
            // 按逻辑,从下一个元素到数组结束位置的数组切片进行查找, 找不到则结束位置索引=开始位置索引, 找到了则结束位置索引为当前位置索引
            // 以下代码的逻辑需要调整 Add by Max Yu 2021.08.31
            for (int i = index; i < list.size(); i++) {
                if (list.get(i) > target) {
                    max = i - 1;
                    break;
                }
            }
        } else {
            index = max = -1;
        }
        System.out.println("[" + index + "," + max + "]");


    }

    /**
    优化后的逻辑 Add by Max Yu 2021.08.31
     */
    public static void select2(Integer arr[], int target) {
        int startIndex = -1, endIndex = -1;
        Arrays.sort(arr);

        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                startIndex = endIndex = i;
                for (int j = arr.length - 1; j > startIndex; j--) {
                    if (arr[j] == target) {
                        endIndex = j;
                        break;
                    }
                } // for
                break;
            } // if
        } // for

        System.out.println("[" + startIndex + "," + endIndex + "]");

    }

}

