package com.snowballtech.algorithm.yingyingqu;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Week38 {
    /**
     * 数组长度是n+1则数组下标范围是0~n，元素的范围又是1~n，那么这个数组就可以表示一个头结点下标是0的链表（元素可以作为下一个元素的下标）
     * 因为本题中一定有重复的元素，所以链表中一定会有环回到这个元素，这个元素就是环的切入点
     * 先用快指针和慢指针的方法找到两个指针的相遇点，这个也是判定链表是否有环的方式
     * 然后画图根据两个指针所走的路程关系写数学公式可以发现：起点到切入点的距离是此时慢指针到切入点距离的整数倍
     * 那么此时就可以用一个新的指针和慢指针一起跑，相遇的地方就是切入点
     * 注：这里两个指针相遇是指两个指针指向的值是一样的，而不是说这两个指针本身值是一样的
     *
     * @param nums
     * @return
     */
    public static int findRepeatedNumber(int[] nums) {
        int quickIndex = 0, slowIndex = 0, resultIndex = 0;

        //先找到快慢指针相遇的地方(快指针走两步，慢指针走一步，所以在环内相当于快指针在追慢指针，所以一定会相遇)
        do {
            quickIndex = nums[nums[quickIndex]];
            slowIndex = nums[slowIndex];
        } while (nums[quickIndex] != nums[slowIndex]);

        //然后让慢指针和一个从头开始的指针同时开始走，他们相遇的地方，就是环的入口，就是重复的数据（因为起点到切入点的距离是此时慢指针到切入点距离的整数倍）
        while (nums[slowIndex] != nums[resultIndex]) {
            slowIndex = nums[slowIndex];
            resultIndex = nums[resultIndex];
        }

        return nums[resultIndex];
    }

    /**
     * 推荐二分查找算法，时间复杂度O(nlogn)
     * Add by Max Yu 2020.09.23
     * @param nums
     */
    public static int findRepeatedNumber2(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n;
        do {
            System.out.print("请输入n（1 <= n <= 10^5）：");
            n = scanner.nextInt();
        } while (n < 1 || n > Math.pow(10, 5));

        int[] nums = new int[n + 1];
        Set<Integer> numsSet;//用户快速判断数组中是否有重复数字的hash表
        int repeatedNumber;//计算数组中第一个重复的数字
        boolean flag;//判断用户第一次输入是否有误的标记
        do {
            System.out.println("请输入" + (n + 1) + "个整数，整数范围1~" + n + "（包含1和" + n + "）：");
            numsSet = new HashSet<>();
            repeatedNumber = 0;
            flag = false;
            for (int i = 0; i < n + 1; i++) {
                nums[i] = scanner.nextInt();
                if (nums[i] < 1 || nums[i] > n) {
                    flag = true;//用户输入有误时
                    break;
                }

                //判断数组中是否只有一个重复的数字
                if (numsSet.contains(nums[i])) {
                    if (repeatedNumber == 0) {
                        repeatedNumber = nums[i];
                    } else if (repeatedNumber != nums[i]) {
                        flag = true;
                        break;
                    }
                } else {
                    numsSet.add(nums[i]);
                }
            }
        } while (flag);

        System.out.println("重复数是" + findRepeatedNumber(nums));
        System.out.println("重复数是" + findRepeatedNumber2(nums));

    }
}
