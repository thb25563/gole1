package com.snowballtech.algorithm.huashi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description:
 * @author:石华
 * @createTime:2021/9/22 9:24
 */
public class Week38 {

    public static void main(String[] args) {
        int nodeCount;
        System.out.println("请输入树的节点个数：（1《=nodeCount《=100）");
        Scanner sc = new Scanner(System.in);
        nodeCount = sc.nextInt();
        if (!(1 <= nodeCount && nodeCount <= 100)) {
            System.out.println("您输入的节点个数不合法，请重新输入：（1《=nodeCount《=100）");
            nodeCount = sc.nextInt();
        }
        String[] array = new String[nodeCount];

        for (int index = 0; index < nodeCount; index++) {
            System.out.println("请输入第" + (index + 1) + "节点的值：值范围在（1-100，若该节点为空，请用null表示）");
            array[index] = sc.next();
        }

        //传入数组，构建二叉树
        Tree tree = createTree(new Tree(), array, 1);

        //先序遍历测试树是否创建成功
        preOrder(tree);
        System.out.println();

        //调用查询路径的方法，将生成的路径存到集合中
        List<StringBuilder> result = new ArrayList<>();
        StringBuilder path = new StringBuilder("");
        findPath(tree, path, result);

        //遍历集合，打印路径
        for (StringBuilder value : result) {
            System.out.println(value);
        }
    }

    //树的实体类
    public static class Tree {
        String data;
        Tree leftChild;
        Tree rightChild;

        Tree() {

        }

        Tree(String data) {
            this.data = data;
            leftChild = null;
            rightChild = null;
        }
    }

    public static Tree createTree(Tree tree, String[] array, int index) {
        //判断此节点下是否还有节点
        if (index > array.length / 2) {
            return tree;
        }
        //给根节点赋值
        if (index == 1) {
            tree.data = array[0];
        }
        //根据节点和左右孩子之间的关系，找到数组中各自的左右孩子节点  左孩子等于2*i，右孩子等于2*i+1 数组下标从0开始，所以此处的左右孩子关系如下，根据此关系，递归调用createTree（）构建二叉树
        tree.leftChild = new Tree(array[index * 2 - 1]);
        tree.rightChild = new Tree(array[index * 2]);
        //递归添加，一直到叶子节点
        createTree(tree.leftChild, array, index + 1);
        //递归结束，进行回溯添加
        createTree(tree.rightChild, array, index + 2);
        return tree;
    }

    //使用先序遍历测试二叉树是否创建成功
    public static void preOrder(Tree tree) {
        if (tree == null) {
            return;
        }
        System.out.print(tree.data + " ");
        preOrder(tree.leftChild);
        preOrder(tree.rightChild);
    }



    //采用先序遍历的思想，一直递归，直接找到最左边的一个叶子节点，判断该节点为叶子节点后，第一条路径存入集合之中，如果不是叶子节点，不会执行存入集合的动作，找到第一条路径之后，此时该节点的度为0树为空，结束一次循环，回溯到上一个节点，path存储的路径随之回溯，继续找下一个叶子节点，重复此步骤，直到找到最后一个叶子节点，回溯结束，程序结束
    public static void findPath(Tree tree, StringBuilder path, List<StringBuilder> res) {
        //判断树是否为空，树为空回溯开始
        if (tree == null)
            return;

        //经过上面判断此时的树不为空，直接拼接data
        path.append(tree.data);

        //如果该节点为叶子节点就不用拼接->，将输入直接存到list中去，返回，因为输入的格式有个null，增加个过滤条件判断节点的值是否为string类型的null
        if (tree.leftChild == null && tree.rightChild == null && !"null".equals(tree.data)) {
            res.add(path);
            return;
        }
        //递归加回溯，在后面追加->随后进行递归，递归到叶子节点最后进行回溯
        // 应该在递归方法体内新建一个StringBuilder对象, 如下代码创建的是两个不同的StringBuilder对象, 会造成逻辑错误 Add by Max Yu 2021.09.23
        findPath(tree.leftChild, new StringBuilder(path).append("->"), res);
        findPath(tree.rightChild, new StringBuilder(path).append("->"), res);
        /*
        同样是对stringBuild进行赋值我下面的写法不知道为什么会出错
        path = path.append("->");
        findPath(tree.leftChild, path, res);
        findPath(tree.rightChild, path, res);
        * */
    }

    /**
     *  以上DFS方法签名修改如下,供参考  Add By Max Yu 2021.09.23
     * @param root
     * @param path
     * @param paths
     */
    public void findPath(Tree root, String path, List<String> paths) {
        if (root != null) {
            StringBuffer pathSB = new StringBuffer(path);
            pathSB.append(root.data);
            if (root.leftChild == null && root.rightChild == null) {  // 当前节点是叶子节点
                paths.add(pathSB.toString());  // 把路径加入到答案中
            } else {
                pathSB.append("->");  // 当前节点不是叶子节点，继续递归遍历
                findPath(root.leftChild, pathSB.toString(), paths);
                findPath(root.rightChild, pathSB.toString(), paths);
            }
        }
    }



}
