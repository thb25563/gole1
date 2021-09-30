package com.snowballtech.algorithm.huashi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @description:
 * @author:石华
 * @createTime:2021/9/9 16:16
 */
public class Week37 {
    public static void main(String[] args) {
        //从控制台输入数组长度
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数组的长度");

        //定义数组，并判断输入的长度是否在范围内
        int length = sc.nextInt();
        if (length < 1 || length > 100) {
            System.out.println("数组长度超出限制,请重新输入");
            length = sc.nextInt();
        }

        int[] arr = new int[length];

        //循环输入数组内的元素
        System.out.println("请输入数组的每个元素，以Enter结束");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("请输入第" + (i + 1) + "个元素");
            int num = sc.nextInt();
            //判断输入的数字是否符合，如果不符合的话，重新输入
            if (num < 0 || num > Math.pow(10, 9)) {
                System.out.println("您输入的数字不在范围内，请重新输入");
                num = sc.nextInt();
            }
            // 元素经过判断后，符合才赋值
            arr[i] = num;
        }
        System.out.println("您输入的数组为：" + Arrays.toString(arr));
        System.out.println();
        //调用方法进行排序
        System.out.print("最大的数为：" );

        // 对于题意的理解有误, 并不是简单地按照整数大小排列后升序,再组合成字符串的逻辑.比如34>9,但9应该排在34前面. Add by Max Yu 2021.09.15
        // sort(arr);
       System.out.println(largestNumber(arr));
    }


    //排序方法
    public static void sort(int[] arr) {
        //采用冒泡排序对数组进行排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        //将排序好的数组元素依次拼接成字符串形式并输出最终结果
        StringBuilder str = new StringBuilder();
        for (int x : arr) {
            str.append(x);
        }
        System.out.println(str);

    }

    /**
     * 示例代码如下
     * 思路：
     * 1. 要想组成最大的整数，一种直观的想法是把数值大的数放在高位。于是我们可以比较输入数组的每个元素的最高位，最高位相同的时候比较次高位，以此类推，完成排序，
     *    然后把它们拼接起来。这种排序方式对于输入数组 没有相同数字开头 的时候是有效的，例如 [45, 56, 81, 76, 123][45,56,81,76,123]。
     * 2. 考虑输入数组有相同数字开头的情况，例如 [4,42][4,42] 和 [4,45][4,45]。
     *    对于 [4,42][4,42]，比较 442 > 424442>424，需要把 44 放在前面；
     *    对于 [4,45][4,45]，比较 445 < 454445<454，需要把 4545 放在前面。
     *    因此我们需要比较两个数不同的拼接顺序的结果，进而决定它们在结果中的排列顺序。
     */
    public static String largestNumber(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <=  x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (numsArr[0] == 0) {
            return "0";
        }

        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }

        return ret.toString();
    }



}
