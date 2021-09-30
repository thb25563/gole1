package com.snowballtech.algorithm.chuanguocao;

import java.util.*;

/**
 * @author:CCGuo
 * @date:2021/8/31 12:40
 * ClassName:week35
 * Package:com.snowballtech.algorithm
 * Description:
 */
public class Week35 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int length = 0;
        int countZero = 0;
        Integer[] nums;

        System.out.println("请指定数组长度(0<=length<=3000)：");
        int len = scanner.nextInt();
        //限制数组长度在 0 ~ 3000，否则重新输入
        while (len < 0 || len > 3000) {
            len = scanner.nextInt();
        }
        length = len;
        nums = new Integer[length];

        //限制输入数据的范围 -10^5 ~ 10^5
        System.out.println("请输入数组数据(-10^5<=nums[i]<=10^5)：");
        for (int i = 0; i < length; i++) {
            nums[i] = scanner.nextInt();
            while (nums[i] < -10e5 || nums[i] > 10e5) {
                System.out.println("输入数据错误，请重新输入：");
                nums[i] = scanner.nextInt();
            }
            if (nums[i] == 0)
                countZero++;
        }

        System.out.println("您输入的数组为：");
        System.out.println(Arrays.asList(nums));

        selectList(length, nums, countZero);
    }

    /**
     *  将传入数组先进行去重 再将数组排序
     *  排序后按三层循环进行匹配 得到符合条件的三元组
     *  对于全由 0 组成的三元组结果 [0,0,0] 单独进行处理
     */
    public static void selectList(int length, Integer[] nums, int countZero) {

        //数组去重 Set
        Set<Integer> set = new HashSet();

        //去重后的数组
        Integer[] uniqueNums;

        //去重后的数组长度
        int uniqueNumsLength;

        //三元组暂存列表 tempList
        List<Integer> tempList;

        //结果列表 resultList
        List<List> resultList = new ArrayList<>();

        //将数组数据放入Set实现去重
        for (Integer i : nums) {
            set.add(i);
        }
        uniqueNums = new Integer[set.size()];
        uniqueNumsLength = set.size();

        //将去重后的数据由Set转为Integer数组
        set.toArray(uniqueNums);
        //System.out.println("去重后的数据：");
        //System.out.println(Arrays.asList(uniqueNums));

        //将数组进行排序，便于之后查找结果(默认升序)
        Arrays.sort(uniqueNums);
        //System.out.println("After Sort：");
        //System.out.println(Arrays.asList(uniqueNums));

        //处理 0的个数大于 2的情况 将[0,0,0]加入结果列表
        if (countZero >= 3) {
            tempList = new ArrayList<>();
            tempList.add(0);
            tempList.add(0);
            tempList.add(0);
            resultList.add(tempList);
        }
        //三层循环进行三元组匹配
        // 算法复杂度达到O(N^3), 应该优化 Add by Max Yu 2021.09.01
        for (int i = 0; i < uniqueNumsLength; i++) {
            // 因为之前数组已经按升序排列处理, 这里可以判断元素值是否大于0,如果大于0则退出循环,Add by Max Yu 2021.09.01
            if (uniqueNums[i] > 0) {
                break;
            }
            for (int j = i + 1; j < uniqueNumsLength; j++) {
                // 这里可以判断是否第2个元素大于0且第1,2元素相加大于等于0,是则退出第二层循环, Add by Max Yu 2021.09.01
                if (uniqueNums[j] > 0 && uniqueNums[i] + uniqueNums[j] >= 0){
                    break;
                }
                for (int k = j + 1; k < uniqueNumsLength; k++) {
                    // 这里可以判断是否三个元素都为0,是则忽略, Add by Max Yu 2021.09.01
                    if (uniqueNums[i] == 0 && uniqueNums[j] == 0 && uniqueNums[k] == 0) {
                        continue;
                    }
                    if (uniqueNums[i] + uniqueNums[j] + uniqueNums[k] == 0) {
                        tempList = new ArrayList<>();
                        tempList.add(uniqueNums[i]);
                        tempList.add(uniqueNums[j]);
                        tempList.add(uniqueNums[k]);
                        resultList.add(tempList);
                    }
                } //for
            } //for
        } //for


        System.out.println("符合条件的结果为：");
        System.out.println(resultList);
    }
}
