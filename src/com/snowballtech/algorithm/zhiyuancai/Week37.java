package com.snowballtech.algorithm.zhiyuancai;

import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/*
* 思路:
*   输入采用广度优先遍历，在输入的同时构造树
*   查找结果采用深度优先遍历
* */
public class Week37 {
    public static Integer count = 0;
    private static TreeNode head;

    public static void main(String[] args) {
        //记录要访问的节点队列
        Queue<TreeNode> treeNodes = new LinkedBlockingQueue<TreeNode>();

        System.out.println("请输入二叉搜索树，n代表空值，e为结束，回车为输入下一个值");
        Scanner input = new Scanner(System.in);
        //头节点赋值
        head = new TreeNode(input.nextInt());
        //游标
        TreeNode temp = head;
        //处理后续输入
        treeNodes.add(temp.left);
        treeNodes.add(temp.right);
        readValue(treeNodes);

        System.out.println("请输入\nk=");
        Scanner kScan = new Scanner(System.in);
        int k = kScan.nextInt();

        //获取第k小的值
        getMin(head, k);
    }

    //读取值,并且构造二叉搜索树
    public static void readValue(Queue<TreeNode> nodes){
        Scanner input = new Scanner(System.in);
        //循环节点队列
        for (TreeNode treeNode : nodes) {
            String next = input.next();

            /*
            * 不能直接new一个新的对象,会导致当前节点和队列中的节点指向不一致(不是同一个对象)
            * */
            //若该节点不为空,则new一个节点对象
            if (!next.equals("n")){
                //输入e则结束
                if (next.equals("e")){
                    break;
                }
                treeNode.setValue(Integer.parseInt(next));
                //该方法用于生成左右节点
                treeNode.buildTree();
                //将左右节点以此放入队列,然后该节点出队
                nodes.add(treeNode.left);
                nodes.add(treeNode.right);
                nodes.remove();
            } else {
                //若节点为空,则直接出队
                nodes.remove();
            }
        }
    }

    /*
    * 思路:
    *   采用中序遍历，先左节点，后中间节点，最后右节点
    *   向左递归，直到没有左节点，则当前为最小值，随后回溯，向右递归
    *   只输出中间节点的值（将叶子节点自身看做是中间节点）
    * */
    public static void getMin(TreeNode node, int k){
        //递归结束条件,count用于表示当前为第count小的数
        if (node == null || node.value == null || count >= k){
            return;
        }

        //若左节点不为空,则向左节点递归,直到最后没有左节点
        if (node.left.value != null){
            getMin(node.left, k);
        }

        //当没有左节点时,则当前为第count+1小的数字,
        count ++;
        if (count == k){
            System.out.println("输出:"+node.value);
        }

        //向右节点递归
        if (node.right.value != null){
            getMin(node.right, k);
        }
    }
}
