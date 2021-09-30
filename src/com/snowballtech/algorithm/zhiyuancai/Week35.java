package com.snowballtech.algorithm.zhiyuancai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 将所有组合都看为两两结合，然后采用递归的方式，围绕一个确定的数找出其所有组合
 * */
public class Week35 {

    public static final Integer n = 4;
    public static final Integer k = 2;

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();

        Week35 week35 = new Week35();
        List<Set<Integer>> meth = week35.meth(k, 1);

        long end = System.currentTimeMillis();
        long time = end - begin;

        System.out.println(meth);
        System.out.println("执行时间:"+ time + "ms");
    }

    /**
     * size：本次组合的实际长度
     * begin：初始值（围绕该值找出所有两两组合的结果）
     */
    public List<Set<Integer>> meth(int size, int begin) {
        //递归返回的结果
        List<Set<Integer>> lists = new ArrayList<>();
        //使用Set集合防止重复的问题
        Set<Integer> integers = new HashSet<>();

        //返回两位数的所有组合
        if (size <= 2) {
            if (begin < n){
                List<Set<Integer>> meth = meth(size, begin + 1);
                //合并递归结果
                lists.addAll(meth);
            }
            for (int i = begin; i <= n; i++) {
                integers.add(i);

                //在长度为2时触发保存数据
                if (integers.size() == size) {
                    //防止浅克隆导致的删除问题
                    HashSet<Integer> integers1 = new HashSet<>(integers);
                    lists.add(integers1);
                    integers.remove(i);
                }
            }
            return lists;
        }else {
            List<Set<Integer>> meth = meth(size - 1, 1);
            for (Set<Integer> integerSet : meth) {
                for (Integer i = 1; i <= n; i++) {
                    //将其看做两位数的组合，并选择使用Set集合，避免重复问题
                    integerSet.add(i);
                    //防止浅克隆导致的删除问题
                    HashSet<Integer> integers1 = new HashSet<>(integerSet);
                    //筛选出不符合长度的集合（当组合时出现重复问题则会导致长度少1位）
                    if(integers1.size() == size){
                        lists.add(integers1);
                    }
                    integerSet.remove(i);
                }
            }
            return lists;
        }
    }


    /**
     *  存放临时的整数列表变量
     */
    List<Integer> temp = new ArrayList<Integer>();

    /**
     * 存放结果列表
     */
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    /**
     * 本题其实是从n个数中取k个数的组合问题, 可以使用DFS(深度优先搜索)的算法思路来解决. 例子代码如下
     * Add by Max Yu 2021.09.01
     */
    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k);
        return ans;
    }

    public void dfs(int cur, int n, int k) {
        // 剪枝：temp的长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为k的temp,可以忽略
        if (temp.size() + (n - cur + 1) < k) {
            return;
        }
        // 记录满足题解的组合项
        if (temp.size() == k) {
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        // 考虑选择当前位置再递归计算
        temp.add(cur);
        dfs(cur + 1, n, k);

        // 移除临时列表最后一个元素再执行递归
        temp.remove(temp.size() - 1);
        // 考虑不选择当前位置再递归计算
        dfs(cur + 1, n, k);
    }


}