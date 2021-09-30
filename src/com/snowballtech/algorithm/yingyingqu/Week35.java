package com.snowballtech.algorithm.yingyingqu;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Week35 {
    //nums数组的最大长度
    public static int MAXLEN = 30000;

    //去除重复的元素，并返回数组中有效的元素个数
    public static int eliminateDuplication(int[] nums, int len) {
        if (len <= 1) {
            return len;
        }

        //记录当前没有重复元素的位置，一开始是0
        int currentIndex = 0;
        for (int i = 1; i < len; i++) {
            //当这一位与currentIndex位元素不重复时，将这一位元素赋给currentIndex后一位，并且currentIndex向后移一位
            if (nums[currentIndex] != nums[i]) {
                currentIndex++;
                nums[currentIndex] = nums[i];
            }
        }

        return currentIndex + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    /*    System.out.print("请输入升序数组nums, 数字间按空格分隔：");
        int[] nums = new int[MAXLEN];
        String numsString = s.nextLine();
        String[] numsStringArray = numsString.trim().split(" ");

        //将字符串数组转为整形数组
        for (int i = 0; i < numsStringArray.length; i++) {
            nums[i] = Integer.parseInt(numsStringArray[i]);
        }
    */

       // 修改如下, Add by Max Yu 2021.08.31, 但还需加入前提条件验证
        System.out.println("请输入数组的长度length（0 <= length <= 3*10^4）");
        int length = sc.nextInt();

        int arr[] = new int[length];
        System.out.println("请输入数组的每一个元素(默认为升序)，长度为：" + length);
        for (int i = 0; i < length; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int resultNum = eliminateDuplication(arr, arr.length);

        System.out.println("去重后数组长度为：" + resultNum);
        System.out.print("数组内容为：");
        for (int i = 0; i < resultNum; i++) {
            System.out.print(arr[i] + " ");
        }
    }



}