package com.snowballtech.algorithm.chuanguocao;

import java.util.Random;
import java.util.Scanner;

/**
 * @author:CCGuo
 * @date:2021/9/22 13:05
 * @Description:
 */
public class Week38 {
    private static Random random = new Random();

    public static void main(String[] args) {
        /**
         * 输入数组
         */
        int[] nums;
        /**
         * 数组长度
         */
        int length;
        /**
         * 第k大的元素
         */
        int k;
        /**
         * 输入暂存
         */
        int inputNum;
        int maxLength = 10000;
        int minLength = 1;
        int minNum = -10000;
        int maxNum = 10000;

        Scanner scanner = new Scanner(System.in);
        //数组长度校验
        System.out.println("请输入数组长度1≤length≤10000:");
        length = scanner.nextInt();
        while (length < minLength || length > maxLength) {
            System.out.println("输入错误，请重新输入：");
            length = scanner.nextInt();
        }

        //数组元素校验
        nums = new int[length];
        System.out.println("请输入数组元素-10000≤nums[i]≤10000:");
        for (int i = 0; i < length; i++) {
            inputNum = scanner.nextInt();
            while (inputNum < minNum || inputNum > maxNum) {
                System.out.println("输入错误，请重新输入：");
                inputNum = scanner.nextInt();
            }
            nums[i] = inputNum;
        }

        // k取值校验
        System.out.println("请输入k：");
        k = scanner.nextInt();
        while (k < 1 || k > length) {
            System.out.println("输入错误，请重新输入：");
            k = scanner.nextInt();
        }

        sortNums(nums,k);

    }


    /**
     * @param array 需要排序的数组
     * @param k 所求第k大的数字
     * @Description 升序冒泡排序 排序后下标为 k-1的元素即为结果
     * 应该是降序，冒泡排序的时间复杂度为O(n平方)，建议使用效率更高的快速排序,时间复杂度O(nlogn) Add by Max Yu 2021.09.23
     */
    public static void sortNums(int[] array, int k) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j]) {
                    array[i] = array[i] + array[j];
                    array[j] = array[i] - array[j];
                    array[i] = array[i] - array[j];
                }
            }
        }
        System.out.println("第" + k + "大的是：" + array[k - 1]);
    }

    /**
     * 快速排序的java实现 Add by Max Yu 2021.09.23
     * @param a  待排序数组
     * @param l  数组的下限坐标
     * @param r  数组的上限坐标
     * @param index 待查找元素的数组下标
     * @return
     */
    public static int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public static int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public static int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
