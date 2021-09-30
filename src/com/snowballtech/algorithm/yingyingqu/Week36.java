package com.snowballtech.algorithm.yingyingqu;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Week36 {

    public static int getLongestDigitContinuousSequenceLength(int[] arri) {
        //先把无序数组放在哈希表中，使得访问元素的时间复杂度为O(1)，即本题中判断某元素是否在数列中
        Set<Integer> arriSet = new HashSet<>();
        for (int x : arri) {
            arriSet.add(x);
        }

        //不断以一个元素为头，向下找它的后一个元素，找到后面没有连续元素的时候判断长度是否是目前为止最长的，所以会需要两次循环
        //为了让时间复杂度为O(n),必须保证每个元素在循环中至多出现1次，可以发现两次循环是否有价值的判断依据就是这个元素的前一位是否存在
        //当该元素前一位已经存在时，这个元素不可能是连续元素序列的头，不用进行第一次循环，而它一定会在第二次循环中被找到
        //当该元素前一位不存在时，这个元素一定是连续元素序列的头，参与第一次循环，而第二次循环时一定不会找到
        int longestLen = 1;
        for (Integer x : arriSet) {
            if (!arriSet.contains(x - 1)) {
                int tempLongestLen = 1;
                int currentValue = x;
                while (arriSet.contains(++currentValue)) {
                    tempLongestLen++;
                }

                if (tempLongestLen > longestLen) {
                    longestLen = tempLongestLen;
                }
            }
        }

        return longestLen;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入数组的长度length（0 <= length <= 10^5）");
        int length = sc.nextInt();

        int arr[] = new int[length];
        System.out.println("请输入数组的每一个元素，长度为" + length);
        for (int i = 0; i < length; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("最长连续元素序列长度为：" + getLongestDigitContinuousSequenceLength(arr));
    }
}