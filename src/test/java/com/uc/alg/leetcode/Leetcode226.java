package com.uc.alg.leetcode;

/**
 * @author yangxinyan
 * @date 2020/11/1 15:56
 */
public class Leetcode226 {

  public TreeNode invertTree(TreeNode root) {
      if(root == null) {
        return null;
      }
      TreeNode temp = root.left;
      TreeNode right = root.right;
      root.left = right;
      root.right=temp;

      invertTree(root.left);
      invertTree(root.left);

      return root;
  }
}
