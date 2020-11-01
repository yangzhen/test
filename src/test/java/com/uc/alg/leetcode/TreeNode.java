package com.uc.alg.leetcode;

class TreeNode {

  public TreeNode left;
  public TreeNode right;
  public Integer value;

  public TreeNode(Integer value) {
    this.value = value;
  }

  public TreeNode(Integer value, TreeNode left, TreeNode right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    return "Tree{" +
        "left=" + left +
        ", right=" + right +
        ", value=" + value +
        '}';
  }
}