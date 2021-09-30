package com.snowballtech.algorithm.haibingtu;

import java.util.Scanner;
import java.util.Arrays;
public class Week35 {

    public  static int thb1(int[] nums,int target) {
        Arrays.sort(nums);//对一个数组的所有元素进行排序，并且是按从小到大的顺序。
        int closeNum = nums[0] + nums[1] + nums[2];//将前三个数相加赋给closeNum，表示初始化
        for(int i = 0;i<nums.length - 2;i++) {
            int j = i+1;
            int k = nums.length - 1;
            while(j<k) {
                int tmp = nums[i]+nums[j]+nums[k];
                if(Math.abs(tmp - target) < Math.abs(closeNum - target)) {
                    closeNum = tmp;
                }
                if(tmp < target) {
                    j++;
                }else if(tmp > target) {
                    k--;
                }else {
                    return tmp;
                }
            }
        }
        return closeNum;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入目标值 target：");
        int t=sc.nextInt();

        System.out.print("数组长度值:");
        int p=sc.nextInt();

        //1，输入的数组长度条件：2,输入的目标值target条件
        if (p<=Math.pow(10,3) && p>=3     && (-Math.pow(10, 4) <= t) && (t <= Math.pow(10, 4)) ) {
            //满足条件，创建数组
            int[] arr = new int[p];
            //给数组输入数
            for (int i = 0; i <= arr.length - 1; i++) {
                //数组中的数条件：
                if (arr[i] <= Math.pow(10, 3) && arr[i] >= (-Math.pow(10, 3))) {
                    System.out.print("请输入数组a[" + i + "]:");
                    arr[i] = sc.nextInt();
                } else {
                    System.out.println("输入数组的数不满足条件");
                }
            }
            System.out.println("数组中三个数的和与 target 最接近的为：" + thb1(arr, t));

        } else {
            System.out.println("输入数和数组长度值不满足条件");
        }
    }
}
