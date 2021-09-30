package com.snowballtech.algorithm.haibingtu;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class week38 {

    public static void main(String[] args) {

        week38 tree = new week38();
        //输入层次遍历
        System.out.println("请输入一串二叉搜索树层次遍历，并用逗号隔开，以回车结束,‘0’表示null,且不重复");
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(",");
        int levelOrder[] = new int[str.length];
        for (int i = 0; i < levelOrder.length; i++) {
            levelOrder[i] = Integer.parseInt(str[i]);
        }
        //输入整数k
        System.out.print("整数k为:");
        int k = sc.nextInt();

        //调用buildTree1（）创建二叉树
        TreeNode1 root = tree.buildTree1(levelOrder, 0);

        System.out.println("二叉搜索树的中序遍历以及k");
        tree.inOrder(root);
        System.out.println(" k=" + k);

        //输入k值条件,为true进入查找第k个最小元素
        if (k >= -1 && k <= 104) {
            //对于二叉搜索树来说，根节点比左子树大，比右子树小，用中序遍历,刚好可以从小到大排序,按照顺序找到第 k 个最小元素。
            System.out.println("第 " + k + " 个最小元素为" + tree.kmin(root, k));
        } else {
            System.out.println("输入k值不满足");
        }


    }

    //层次构造二叉树
    public TreeNode1 buildTree1(int[] nums, int i) {
        if (nums.length == 0)
            return null;
        if (i >= nums.length)
            return null;
        TreeNode1 root = new TreeNode1(nums[i]);
        //左右孩子结点的位置分别为2i+1和2i+2
        root.left = buildTree1(nums, 2 * i + 1);
        root.right = buildTree1(nums, 2 * i + 2);
        return root;
    }

    //层次遍历二叉搜索树
    // 没有用到的方法移除,Add by Max Yu 2021.09.23
//    public void levelOrder(TreeNode1 root) {
//        if (root == null) {
//            return;
//        }
//        //采用队列Queue，父结点出队列后，子结点才进队列，而且是保持先左后右，先上后下的顺序
//        Queue<TreeNode1> queue = new LinkedList<>();
//        //offer()向队列添加一个元素 方法只会返回 false
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            TreeNode1 node = queue.poll();
//            System.out.print(node.val + " ");
//            //队列不空，就从队列中取出一个结点，再依次将其左右结点放入队列
//            if (node.left != null)
//                queue.offer(node.left);
//            if (node.right != null)
//                queue.offer(node.right);
//        }
//    }

    //中序遍历二叉搜索树
    public void inOrder(TreeNode1 root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    //调用inOrder（TreeNode1 root,int k）得到k值
    public int kmin(TreeNode1 root, int k) {
        inOrder(root, k);
        return res;
    }

    public int res = 0;
    public int i = 0;

    //中序遍历查找k（递归方式）
    public void inOrder(TreeNode1 root, int k) {
        if (root == null)
            return;
        inOrder(root.left, k);
        i++;
        if (i == k) {
            res = root.val;
            return;
        }
        inOrder(root.right, k);
        return;
    }

    /**
     * 除了中序遍历外,还可以使用迭代算法, 见如下实现 Add by Max Yu 2021.09.23
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        //创建一个栈,可以将方法一的递归转换为迭代，这样可以加快速度，因为这样可以不用遍历整个树，可以在找到答案后停止
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }

}



//节点
class TreeNode1 {
    int val;//该结点的值
    TreeNode1 left;//结点的左孩子结点
    TreeNode1 right;//结点的右孩子结点

    TreeNode1(int x) {//构造方法
        val = x;
    }
}
