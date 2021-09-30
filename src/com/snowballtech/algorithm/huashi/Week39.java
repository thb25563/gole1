package com.snowballtech.algorithm.huashi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @description:
 * @author:石华
 * @createTime:2021/9/23 13:58
 */
public class Week39 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //前置条件，输入数组长度和数组内容，验证是否合法
        System.out.println("请输入整数数组的长度：（1 <= length <= 2500）");
        int numLength = scanner.nextInt();
        if (numLength < 1 || numLength > 2500) {
            System.out.println("您输入的长度不在1-2500范围内,请重新输入");
            numLength = scanner.nextInt();
        }
        //从控制台输入数组元素，并加以判断
        int[] num = new int[numLength];
        System.out.println("请输入数组的每个元素，以Enter结束");
        for (int i = 0; i < num.length; i++) {
            System.out.println("请输入第" + (i + 1) + "个元素");
            int temp = scanner.nextInt();
            //判断输入的数字是否符合，如果不符合的话，重新输入
           // 这里有个小错误, 应该是 temp < 0-Math.pow(10,4) MDF by Max Yu 2021.09.28
            if (temp < - Math.pow(10, 4) || temp > Math.pow(10, 4)) {
//            if (temp < Math.pow(10, -4) || temp > Math.pow(10, 4)) {
                System.out.println("您输入的数字不在范围内，请重新输入");
                temp = scanner.nextInt();
            }
            // 元素经过判断后，符合才赋值
            num[i] = temp;
        }

        maxLength2(num);

    }

    /**
     * description :采用动态规划，将数组分割成若干个循环的数值长度的小数组，从小数组中判断当前数组的最长递增序列，找出局部最优，根据局部最优的情况下
     * 延伸全局最优解
     * param [num]
     * return void
     * author 石华
     * createTime 2021/9/27 10:47
     **/
    public static void maxLength(int num[]) {
        //定义一个维护局部最优解的数组
        int[] dp = new int[num.length];
        //局部最优情况下，局部的最优解
        int maxValue = 1;
        //最坏的情况，数组完全降序，根据题意最长子串应该为一，所以这里将维护局部最优的数组全部初始化为1
        Arrays.fill(dp, 1);
        //外层循环控制每一个最优解数组的长度，
        for (int i = 0; i < num.length; i++) {
            //内层循环从该数组中找出最优解
            for (int j = 0; j < i; j++) {
                //判断此元素是否小于数组中最后一个元素
                if (num[i] > num[j]) {
                    //每一次符合条件的循环都判断是当前dp[i]的数值大，还是经过此次符合循环之后的dp[j]大，因为此时dp[j]符合条件，那么dp[j]应该加一在去比较
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //经过一次循环，比较是前一次的子序列长度大，还是此次循环生成的子序列长度大；
            maxValue = Math.max(maxValue, dp[i]);
        }
        System.out.println(maxValue);

    }

    // 请思考下如果要输出子序列而不是子序列长度，以上代码如何改造 Add by Max Yu 2021.09.28
    /**
     * 推荐一个贪心+二分查找算法 Add by Max Yu 2021.09.28
     * 贪心算法思路：如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小
     */
    public static int maxLength2(int[] nums) {
        //len表示上升子序列长度
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        //维护一个数组 d[i]，表示长度为 i 的最长上升子序列的末尾元素的最小值
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        System.out.println("最大长度: " + len);
        for(int i = 1;i <= len; i ++) {
            System.out.println(d[i]);
        }

        return len;
    }



}


