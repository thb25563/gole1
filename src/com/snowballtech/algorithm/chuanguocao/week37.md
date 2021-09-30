**题目描述**

给定一棵树的前序遍历 `preorder` 与中序遍历 `inorder`。请构造二叉树并返回其根节点。

注意：假设树中没有重复元素。

**示例**

示例1：

![tree](.\img\tree.jpg)

输入:   preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
输出:   [3,9,20,null,null,15,7]

示例2：

输入:  preorder = [-1], inorder = [-1]
输出:  [-1]

**前提条件**

- 1 <= preorder.length <= 3000
- inorder.length == preorder.length
- -3000 <= preorder[i], inorder[i] <= 3000
- preorder 和 inorder 均无重复元素
- inorder 均出现在 preorder
- preorder 保证为二叉树的前序遍历序列
- inorder 保证为二叉树的中序遍历序列

