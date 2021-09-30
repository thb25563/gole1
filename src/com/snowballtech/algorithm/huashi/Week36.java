package com.snowballtech.algorithm.huashi;

import java.util.Scanner;

/**
 * @description:
 * @author:石华
 * @createTime:2021/9/7 11:58
 */
public class Week36 {

    public static void main(String[] args) {
        // 改成控制台输入参数, add by Max Yu 2021.09.08
        // boolean interleave = isMatch("aabcc", "dbbca", "aadbbcbcac");
        System.out.print("请输入字符串s1：");
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        System.out.print("请输入字符串s2：");
        String str2 = sc.next();
        System.out.print("请输入字符串s3：");
        String str3 = sc.next();
        boolean interleave = isMatch(str1, str2, str3);
        System.out.println(interleave);
    }

    public static boolean isMatch(String str1, String str2, String str3) {
        int n = str1.length(), m = str2.length(), t = str3.length();

        if (n + m != t) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];

        // 代码加上注释, 体现自己的思路; 建议即使抄袭网上题解, 也要理解每行代码的逻辑 Add by Max Yu 2021.09.08
        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] = f[i][j] || (f[i - 1][j] && str1.charAt(i - 1) == str3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && str2.charAt(j - 1) == str3.charAt(p));
                }
            }
        }

        return f[n][m];

    }

}
