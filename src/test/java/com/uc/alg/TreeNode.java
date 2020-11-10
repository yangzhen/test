package com.uc.alg;

/**
 *       1
 *    2   3
 *  4 5  6
 */
public class TreeNode {

      public int val;
      public TreeNode left;
      public TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

      public static TreeNode getNode() {
       TreeNode left124 = new TreeNode(4,null,null);
       TreeNode right125 = new TreeNode(5,null,null);
       TreeNode left12 = new TreeNode(2,left124,right125);

       TreeNode left136 = new TreeNode(6,null,null);
       TreeNode right13 = new TreeNode(3,left136,null);

       TreeNode root = new TreeNode(1,left12, right13);
       return root;
      }
  }