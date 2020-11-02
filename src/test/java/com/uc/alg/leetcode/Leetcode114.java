package com.uc.alg.leetcode;

import com.uc.alg.TreeNode;

public class Leetcode114 {

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

        TreeNode pre = root;
        while(pre.right != null) {
            pre = pre.right;
        }
        pre.right = right;
    }


    public void flatten2(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if(curr.left != null) {
                TreeNode next = curr.left;
                TreeNode pre = next;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }

    }

}
