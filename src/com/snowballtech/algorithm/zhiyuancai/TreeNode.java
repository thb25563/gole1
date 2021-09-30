package com.snowballtech.algorithm.zhiyuancai;

public class TreeNode {
    Integer value;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(Integer value) {
        this.value = value;
        left = new TreeNode();
        right = new TreeNode();
    }

    public TreeNode(Integer value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void buildTree(){
        left = new TreeNode();
        right = new TreeNode();
    }
}
