package com.snowballtech.algorithm.yingyingqu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Week39 {
    /**
     * 动态规划，可以将“求解amount金额的最少硬币数”转移成求“amount金额-组成中最后一枚硬币金额后的最少硬币数+1“，然后以此类推
     * 这里从小到大来看，先把小金额的最少硬币数找出，然后大金额利用找好的小金额最少硬币数+1，以此类推
     * 组成中的最后一枚硬币需要枚举硬币面额而确定
     *
     * @param coins
     * @param amount
     * @return
     */
    // 思考一下,如果要求输出每个硬币的面值和其数量, 以下代码如何修改 Add by Max Yu 2021.09.28
    public static int minimumCoins(int[] coins, int amount) {
        // 用一个数组表示“组成0,1,2,3,...,amount-1,amount中各种金额”的最少硬币数
        int[] minCoinsNumArray = new int[amount + 1];

        // 因为要动态生成这个数组，所以数组元素刚开始设置为一个大于最大硬币数的数，比如amount+1
        Arrays.fill(minCoinsNumArray, amount + 1);

        // 但是当金额是0时，最少硬币数肯定也是0
        minCoinsNumArray[0] = 0;

        // 遍历1~amount的每个金额，去找它对应的 minimumCoinsNumber
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            // 遍历每种硬币面额，比较找出组成当前金额的最小硬币数
            for (int i = 0; i < coins.length; i++) {
                // 当当前硬币面额小于等于当前金额时，当前硬币才有可能是组成当前金额的最后一枚硬币
                if (coins[i] <= currentAmount) {
                    minCoinsNumArray[currentAmount] = Math.min(minCoinsNumArray[currentAmount], minCoinsNumArray[currentAmount - coins[i]] + 1);
                }
            }
        }

        // 要判断amount是否有解，无解的时候数组里面记载的应该还是那个amount+1
        return minCoinsNumArray[amount] > amount ? -1 : minCoinsNumArray[amount];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int coinsLen, amount;
        do {
            System.out.print("请输入硬币种类个数coinsLen（1<=coinsLen<=12）：");
            coinsLen = scanner.nextInt();
        } while (coinsLen < 1 || coinsLen > 12);

        int[] coins = new int[coinsLen];
        Set<Long> coinsSet;// 用于判断面额是否重复的集合
        boolean flag;
        do {
            scanner.nextLine();// 清空控制台输入的缓冲区
            coinsSet = new HashSet<>();
            flag = false;
            System.out.print("请输入" + coinsLen + "个不同面额的硬币，硬币面额范围为1~2^32-1：");
            for (int i = 0; i < coinsLen; i++) {
                long temp = scanner.nextLong();

                // 判断硬币面额是否合法
                if (temp < 1 || temp > Math.pow(2, 31) - 1) {
                    flag = true;
                    break;
                }

                // 判断是否该面额是否重复
                if (coinsSet.contains(temp)) {
                    flag = true;
                    break;
                }

                coinsSet.add(temp);
                coins[i] = Math.toIntExact(temp);
            }

            if (!flag) {
                scanner.nextLine();// 清空控制台输入的缓冲区
            }
        } while (flag);

        do {
            System.out.print("请输入硬币组成总金额amount（0<=amount<=10^4）：");
            amount = scanner.nextInt();
        } while (amount < 0 || amount > Math.pow(10, 4));

        System.out.println("最少的硬币数为：" + minimumCoins(coins, amount));
    }
}
