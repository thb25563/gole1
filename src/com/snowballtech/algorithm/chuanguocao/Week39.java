package com.snowballtech.algorithm.chuanguocao;

import java.util.Scanner;

/**
 * @author:CCGuo
 * @date:2021/9/26 14:04
 */
public class Week39 {

    public static int MAX_NUMBER = 10000;
    public static int MIN_NUMBER = 1;

    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入正整数 1 < n < 10000:");
        n = scanner.nextInt();
        while (n < MIN_NUMBER || n > MAX_NUMBER) {
            System.out.println("输入错误，请重新输入：");
            n = scanner.nextInt();
        }

        System.out.println(squareNum2(n));
    }


    /**
     * 思路：首先判断该数字本身是否为完全平方数，是则返回 1；
     *      for循环：
     *          外层为0时，内层判断是否可以由两个完全平方数组成；
     *          外层不为0时，判断是否可以由三个完全平方数组成；
     *      均不满足则返回4；
     * 依据：四平方和定理，任意一个正整数均可由四个整数的平方和组成，即n=A^2+B^2+C^2+D^2；
     *
     * @param n
     * @return
     */
    private static int squareNum(int n) {

        //将数字n开方取整 判断本身是否为完全平方
        int sqrtNum = (int) Math.sqrt(n);
        if (sqrtNum * sqrtNum == n) {
            return 1;
        }

        //判断是否可以以由2个或3个完全平方数构成
        for (int i = 0; i * i <= n / 2; i++) {
            for (int j = 1; j * j <= n / 2; j++) {
                //外层初始i=0 此时判断2个完全平方数是否满足
                if (i == 0) {
                    //开方取整
                    int extra = (int) Math.sqrt(n - j * j);
                    if (extra * extra + j * j == n) {
                        return 2;
                    }
                    //i!=0 此时判断3个完全平方数是否满足
                } else {
                    int extra = (int) Math.sqrt(n - j * j - i * i);
                    if (extra * extra + j * j + i * i == n) {
                        return 3;
                    }
                }
            }
        }

        //1、2、3均不满足 结果为4
        return 4;
    }

    /**
     * Add by Max Yu 2021.09.28
     * 以上算法思路较死板且有局限性。双层循环的时间复杂度达到O(n^2), 推荐算法如下：
     * 思路：
     * 依据题目的要求写出状态表达式：f[i] 表示最少需要多少个数的平方来表示整数 i。
     * 这些数必然落在区间 [1,sqrt(n)]。
     * 我们可以枚举这些数，假设当前枚举到 j，那么我们还需要取若干数的平方，构成 i-j^2。此时我们发现该子问题和原问题类似，只是规模变小了。
     * 这符合了动态规划的要求，于是我们可以写出状态转移方程f[i]=1+min{j=1..sqrt(i)}f[i-j^2]
     * 计算 f[i]时所需要用到的状态仅有 f[i-j^2]，必然小于 i，因此我们只需要从小到大地枚举 i 来计算 f[i] 即可。     *
    */
    private static int squareNum2(int n) {
        int[] f = new int[n + 1];

        // 时间复杂度O(n*sqrt(n))
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }

    // 思考一下,如果题目要求输出完全平方数列表,而不仅是数量, 以上代码如何修改 Add by Max Yu 2021.09.28



}
