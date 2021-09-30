package com.snowballtech.algorithm.chuanguocao;

import java.util.*;

/**
 * @Author: CCGuo
 * @Date: 2021-09-12
 * @Description:
 */
public class Week37 {
    public static void main(String[] args) {
        //前序遍历
        int[] preorder;
        //中序遍历
        int[] inorder;
        //遍历序列长度
        int length;
        //二叉树根节点
        Node treeRoot;
        Scanner scanner = new Scanner(System.in);

        //序列长度输入限制
        System.out.println("请输入序列长度 [0,3000]：");
        length = scanner.nextInt();
        while (length < 0 || length > 3000) {
            System.out.println("输入错误，请重新输入：");
            length = scanner.nextInt();
        }
        preorder = new int[length];
        inorder = new int[length];

        System.out.println("请输入前序遍历：");
        orderInput(preorder, length, scanner);
        System.out.println("请输入中序遍历：");
        orderInput(inorder, length, scanner);

        treeRoot = rebuildTree2(preorder, inorder);
        System.out.println("根节点：" + Arrays.asList(treeRoot.getData()));
        printTree(treeRoot);
    }


    /**
     * @param preorder 前序遍历序列
     * @param inorder  中序遍历序列
     * @return root 二叉树根节点
     * @Description 重构二叉树
     * <p>
     * 1、前序遍历首位为根节点 以此确定中序遍历根节点位置
     * 2、从根节点将中序遍历分为左右子树两部分
     * 3、递归调用重构函数 完成重构
     */
    public static Node rebuildTree(int[] preorder, int[] inorder) {
        //根节点
        Node root = new Node();
        //索引位置
        int index = 0;

        // 不建议用while(true)循环,改成循环遍历数组下标即可 Add by Max Yu 2021.09.15
        /* while (true) {
            //终止条件 传入序列为空时 结束
            //TODO
            if (preorder.length == 0) {
                return root;
            }

            // 如果inorder数组中找不到匹配的元素, 会触发数组坐标溢出和死循环, 不建议在while(true)中使用. Add by Max Yu 2021.09.15
            //查找中序遍历序列根节点索引位置
            if (preorder[0] == inorder[index]) {
                break;
            }
            index++;
        }*/

        // 如下为修正后的代码
        if (preorder.length == 0) {
            return root;
        }
        while (index < preorder.length) {
            if (preorder[0] == inorder[index]) {
                break;
            }
            index++;
        }

        root.setData(preorder[0]);

        // 其中,同一颗子树的前序遍历和中序遍历的长度相同, 因此才能根据左子树的前序遍历和中序遍历结果，以及右子树的前序遍历和中序遍历结果，递归地对构造出左子树和右子树 Add by Max Yu 2021.09.15
        //index=0 无左子节点
        if (index == 0) {
            root.setLeftChild(null);
        } else {
            //递归调用重构函数确定左子节点
            //preorder: 截取preorder中的左子树部分作为参数
            //inorder : 截取inorder中的左子树部分作为参数
            root.setLeftChild(rebuildTree(Arrays.copyOfRange(preorder, 1, index + 1), Arrays.copyOfRange(inorder, 0, index)));
        }
        //index=length-1  无右子节点
        if (index == inorder.length - 1) {
            root.setRightChild(null);
        } else {
            //递归调用重构函数确定右子节点
            //preorder: 截取preorder中的右子树部分作为参数
            //inorder : 截取inorder中的右子树部分作为参数
            root.setRightChild(rebuildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length), Arrays.copyOfRange(inorder, index + 1, inorder.length)));
        }
        return root;
    }


    // 方法二: 迭代,如下所示; 递归代码可读性较好, 但通常实际性能弱于迭代, 即使估算的时间复杂度一致。
    public static Node rebuildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Node root = new Node(preorder[0]);
        Deque<Node> stack = new LinkedList<Node>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            Node node = stack.peek();
            if (node.getData() != inorder[inorderIndex]) {
                node.setLeftChild(new Node(preorderVal));
                stack.push(node.getLeftChild());
            } else {
                while (!stack.isEmpty() && stack.peek().getData() == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.setRightChild(new Node(preorderVal));
                stack.push(node.getRightChild());
            }
        }
        return root;
    }

   
    /**
     * @param order   遍历序列
     * @param length  序列长度
     * @param scanner Scanner
     * @Description 抽取输入控制逻辑单独作为方法
     */
    private static void orderInput(int[] order, int length, Scanner scanner) {
        int currInput;

        // 根据题意,最好加上重复元素判定 Add by Max Yu 2021.09.15
        for (int i = 0; i < length; i++) {
            currInput = scanner.nextInt();
            while (currInput < -3000 || currInput > 3000) {
                System.out.println("输入错误，请重新输入：");
                currInput = scanner.nextInt();
            }
            order[i] = currInput;
        }
    }


    /**
     * @param root 二叉树根节点
     * @Description 打印重构二叉树结果以List格式输出
     * <p>
     * 1、利用队列FIFO特点 节点先入队 输出首位节点数据后 首位出队
     * 2、判断首位节点有无左、右子节点  有则将其按顺序先左后右的顺序入队
     * 3、再重复步骤 1-2 直至队列中为空
     *    TODO：输出格式改为完全二叉树格式
     */
    // 根据题意,要求转为数组输出, 左右节点为空时输出null, 以下代码尚未满足需求 Add by Max Yu 2021.09.15
    public static void printTree(Node root) {

        //结果List
        List<Integer> result = new ArrayList<>();
        //队列 存放二叉树节点
        LinkedList<Node> nodeQueue = new LinkedList<>();
        //暂存当前出队节点
        Node currNode = null;

        //二叉树判空
        if (root == null) {
            result.add(null);
            return;
        }

        //根节点入队
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            //根节点出队并取出数据
            currNode = nodeQueue.poll();
            result.add(currNode.getData());
            if (currNode.getLeftChild() != null) {
                //左子节点入队
                nodeQueue.offer(currNode.getLeftChild());
            }
            if (currNode.getRightChild() != null) {
                //右子节点入队
                nodeQueue.offer(currNode.getRightChild());
            }
         }
        System.out.println(result);
    }
}



/**
 * 节点类
 */
class Node {

    private Node leftChild;

    private Node rightChild;

    private Integer data;

    Node() {

    }

    Node(Integer data) {
        this.data = data;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public Integer getData() {
        return data;
    }
}







