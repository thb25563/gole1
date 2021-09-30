package com.snowballtech.algorithm.yingyingqu;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Week37 {
    /** Add by Max Yu 2021.09.15
     * 可以将本题建模成一个求拓扑排序的问题：
     *     * 我们将每一门课看成一个节点；
     *     * 如果想要学习课程 AA 之前必须完成课程 BB，那么我们从 BB 到 AA 连接一条有向边。这样以来，在拓扑排序中，BB 一定出现在 AA 的前面。
     *     * 求出该图是否存在拓扑排序，就可以判断是否有一种符合要求的课程学习顺序。
     * @param numCourses
     * @param prerequisites
     * @return
     */

    //分析：当学习a课程的前提是学完b课程，而学习b课程的前提兜兜转转又回到了学完a课程，那么无法修完课程；
    // 也就是b到a的过程中形成了环路，于是想到了用有向图环路的判断方法来判断能否学完全部课程。
    public static boolean avaliable(int numCourses, int[][] prerequisites) {
        //将前提条件数组转为有向图（邻接矩阵）
        int[][] digraph = new int[numCourses][numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            digraph[prerequisites[i][1]][prerequisites[i][0]] = 1;
        }

        //思想：先找一个没有前驱节点的节点，然后删除这个节点以及这个节点的所有边，一直重复这个过程
        //如果在有向图中节点还没有删除完的时候，已经不存在“没有前驱的节点”了，那么就是有环路
        //可以用“队列”的思想来实现，找到“没有前驱的节点”时入队，删除操作时出队

        //放没有前驱的节点的队列
        Queue<Integer> withoutFrontNode = new LinkedList<>();

        //先遍历一遍有向图，统计刚开始时每个点的入度数
        //统计入度数可以方便统计删除一个节点所有边时对其后继节点入度的影响，也就更方便判断被影响节点是否变成了没有前驱的节点
        int[] inDegree = new int[numCourses];
        for (int j = 0; j < numCourses; j++) {
            for (int i = 0; i < numCourses; i++) {
                inDegree[j] += digraph[i][j];
            }

            //当入度数为0时，这个点入队
            if (inDegree[j] == 0) {
                withoutFrontNode.offer(j);
            }
        }

        //对队的操作
        int pollNums = 0;//记录出了队的节点个数
        while (!withoutFrontNode.isEmpty()) {
            int nodeIndex = withoutFrontNode.poll();
            pollNums++;

            //删除这个点指向后继的所有边,删除的时候还要判断一下，后继节点有没有因为这个操作而变成了没有前驱的节点，是的话需要入队
            for (int j = 0; j < numCourses; j++) {
                if (digraph[nodeIndex][j] == 1) {//找到了一个后继节点
                    inDegree[j]--;//后继节点入度-1
                    digraph[nodeIndex][j] = 0;//删除边

                    //判断这个后继节点的入度有没有因为这个操作变成了0，即成为了没有前驱的节点
                    if (inDegree[j] == 0) {
                        withoutFrontNode.offer(j);
                    }
                }
            }
        }

        //并不是所有节点都当过“没有前驱的节点”而出过队时，说明有环路（因为没有环路时必然存在一个节点是没有前驱的）
        if (pollNums < numCourses) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       // 注意前题题条件的判定,和上周一样,缺失判定逻辑 Add by Max Yu 2021.10.15
        System.out.println("请输入这学期必须选修的课程门数：");
        int numCourses = scanner.nextInt();

        System.out.println("请输入前提条件个数：");
        int numConditions = scanner.nextInt();

        System.out.println("请输入" + numConditions + "对前提条件：");
        int[][] prerequisites = new int[numConditions][2];
        for (int i = 0; i < numConditions; i++) {
            prerequisites[i][0] = scanner.nextInt();
            prerequisites[i][1] = scanner.nextInt();
        }

        System.out.println("能否修完全部课程：" + avaliable(numCourses, prerequisites));

    }
}
