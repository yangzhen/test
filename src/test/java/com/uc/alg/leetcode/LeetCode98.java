package com.uc.alg.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class LeetCode98 {

//  private static TreeNode root = new TreeNode(2);
//  private static TreeNode treeNode_2_l1 = new TreeNode(1);
//  private static TreeNode treeNode_2_r_3 = new TreeNode(3);
//  static {
//    root.left=treeNode_2_l1;
//    root.right=treeNode_2_r_3;
//  }

  private static TreeNode root = new TreeNode(5);
  private static TreeNode root_l1 = new TreeNode(1);
  private static TreeNode root_r4 = new TreeNode(4);
  private static TreeNode root_r4_l3 = new TreeNode(3);
  private static TreeNode root_r4_r6 = new TreeNode(6);
  static {
    root.left=root_l1;
    root.right=root_r4;
    root_r4.left=root_r4_l3;
    root_r4.right=root_r4_r6;
  }

  @Test
  public void test() {
    boolean flag = isValidBST(root);
    Assert.assertTrue(flag);
  }

  public boolean isValidBST(TreeNode root) {
    if(root == null) {
      return false;
    }
    if(root.left != null && root.right != null) {
      if(root.left.value < root.value && root.value<root.right.value) {
        return isValidBST(root.left) && isValidBST(root.right);
      } else {
        return false;
      }
    }else if(root.left != null ){
      if(root.left.value<root.value) {
        return isValidBST(root);
      } else {
        return false;
      }
    }else if(root.right != null){
      if(root.right.value>root.value) {
        return isValidBST(root);
      } else {
        return false;
      }
    } else {
      return true;
    }
  }



}
