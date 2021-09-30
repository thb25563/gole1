package com.snowballtech.algorithm.haibingtu;

import java.util.*;

public class week37 {
    public static void main(String[] args) {
        // 根据题意,应加上没有重复元素的判定 Add by Max Yu 2021.09.15
        System.out.println("请输入一串中序遍历整数，并用逗号隔开，以回车结束");
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(",");
        int inorder[] = new int[str.length];
        for (int i = 0; i < inorder.length; i++) {
            inorder[i] = Integer.parseInt(str[i]);
        }
        System.out.println("请输入一串后续遍历整数，并用逗号隔开，以回车结束");
        Scanner sc1 = new Scanner(System.in);
        String[] str1 = sc1.nextLine().split(",");
        int postorder[] = new int[str1.length];
        for (int i = 0; i < postorder.length; i++) {
            postorder[i] = Integer.parseInt(str1[i]);
        }

        System.out.println("输入的中序遍历为:" + Arrays.toString(inorder));
        System.out.println("输入的后序遍历为:" + Arrays.toString(postorder));

        week37 thb = new week37();
        System.out.println("根节点为" + buildTree(inorder, postorder).val);

        // 根据题意,输出树的数组描述, 左右子节点为空则用null表示 Add by Max Yu 2021.09.15

    }

    private static Map<Integer, Integer> map;

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        int size = postorder.length;
        map = new HashMap<Integer, Integer>(inorder.length);
        // 将中序遍历结果的值和index建立映射关系，方便寻找各节点中序下标
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(postorder, 0, size - 1, inorder, 0, size - 1);
    }

    private static TreeNode build(int[] postorder, int postorderStart, int postorderEnd, int[] inorder, int inorderStart, int inorderEnd) {
        // 1 判断是否没有左子树或右子树
        if (postorderStart > postorderEnd) {
            return null;
        }

        // 2 构造当前节点，后序遍历区间最后一个值就是当前节点的值
        TreeNode node = new TreeNode(postorder[postorderEnd]);
        int inorderIndex = map.get(postorder[postorderEnd]);
        // 3 找到左子树区间(后序遍历区间;[postStart,postorderEnd-右子树长度-1],中序遍历区间;[inorder,根节点inorderIndex-1]) 构建左子树
        int rightcount = inorderEnd - inorderIndex;
        node.left = build(postorder, postorderStart, postorderEnd - rightcount - 1, inorder, inorderStart, inorderIndex - 1);
        // 4 找到右子树区间(后序遍历区间;[postorderStart+左子树长度,postorderEnd-1],中序遍历区间;[根节点inorderIndex+1，inordere])  构建右子树
        int leftcount = inorderIndex - inorderStart;
        node.right = build(postorder, postorderStart + leftcount, postorderEnd - 1, inorder, inorderIndex + 1, inorderEnd);
        // 5 返回
        return node;
    }


    /**
     *  以上为递归的实现方式, 以下提供一种迭代算法的实现 Add by Max Yu 2021.09.15
     * 迭代法的实现基于以下两点发现。
     * 1.如果将中序遍历反序，则得到反向的中序遍历，即每次遍历右孩子，再遍历根节点，最后遍历左孩子。
     * 2.如果将后序遍历反序，则得到反向的前序遍历，即每次遍历根节点，再遍历右孩子，最后遍历左孩子。
     * 「反向」的意思是交换遍历左孩子和右孩子的顺序，即反向的遍历中，右孩子在左孩子之前被遍历。
    */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }


}

//构造节点
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
