package com.snowballtech.algorithm.weiqilei;

import java.util.*;

public class Week35 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入区间个数");
        //初始化输入区间内存空间;1 <= intervals.length <= 10^4
        int length;
        do {
            length = sc.nextInt();
        } while (length < 0 || length > 10000);

        //锁死intervals[i].length == 2
        int[][] intervals = new int[length][2];
        for (int i = 0; i < length; i++) {
            System.out.println("intervals[" + i + "][0]=");
            intervals[i][0] = sc.nextInt();

            System.out.println("intervals[" + i + "][1]=");
            intervals[i][1] = sc.nextInt();

            //前提条件判断 0 <= start_i <= end_i <= 10^4
            if (intervals[i][1] < intervals[i][0] || intervals[i][1] < 0 || intervals[i][1] > 10000 || intervals[i][0] < 0 || intervals[i][0] > 10000) {
                i--;
            }
        }

        ArrayList<Integer[]> result = new ArrayList<Integer[]>();


        //遍历整个数组
        for (int i = 0, j; i < intervals.length; i++) {
            //合并有交集数组
            for (j = i + 1; j < intervals.length; j++) {
                // 这里的判断不严密,如果第1个区间上限大于第2个区间上限就不对了, 修改如下 Add by Max Yu 2021.09.01
                if (intervals[i][1] >= intervals[j][0]) {
                    intervals[i][1] = intervals[j][1];
                }
                else {
                    break;
                }
            } // for
            Integer[] intergert = new Integer[2];
            intergert[0] = intervals[i][0];
            intergert[1] = intervals[i][1];
            result.add(intergert);
            i = j - 1;
        }

        //输出结果
        System.out.print("[");
        for (Integer temp[] : result) {
            System.out.print("[" + temp[0] + "," + temp[1] + "]");
        }
        System.out.println("]");

    }

    /**
     * 算法核心代码封装成函数,和main()分开, 这里提供一个正确的思路和代码例子如下 Add by Max Yu 2021.09.01
     * 此函数返回合并后的区间数组
     */
    private int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 1. 按照区间的下限即左端点升序排列，这一步很重要
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        // 2. 构建存放结果的数组列表
        List<int[]> merged = new ArrayList<int[]>();

        // 3. 遍历区间列表,依次合并到结果列表中
        for (int i = 0; i < intervals.length; ++i) {
            // 记录每个区间的左右端点
            int L = intervals[i][0], R = intervals[i][1];
            // 结果列表为空或者列表中最后一个区间不与当前区间重叠(右端点小于当前区间的左端点),则加入当前列表
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            }
            // 结果列表中最后一个区间与当前区间重叠,则合并(取最后一个区间的右端点和当前区间右端点的较大值作为最后一个区间的右端点)
            else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }



}
