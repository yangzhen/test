package com.uc.alg.repeate;


import com.uc.alg.leetcode.TreeNode;

/**
 * @author yangxinyan
 * @date 2020/11/2 19:19
 */
public class TreeToLink {

  public void flatten(TreeNode root) {
    if(root == null) {
      return;
    }

    flatten(root.left);
    flatten(root.right);

    TreeNode left = root.left;
    TreeNode right = root.right;
    root.right = left;
    root.left = null;

    TreeNode r = root;
    while(r.right != null) {
      r = r.right;
    }
    r.right = right;

    return;
  }

}
