package com.uc.alg.leetcode;


import com.uc.alg.TreeNode;
import org.junit.Test;

public class Leetcode124 {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(com.uc.alg.TreeNode root) {
        if(root == null) {
            return 0;
        }
        count(root);
        return max;
    }

    public int count(com.uc.alg.TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = Math.max(0,count(root.left));
        int right = Math.max(0,count(root.right));
        int ans = left + right + root.val;
        max = Math.max(ans, max);
        return Math.max(left,right)+root.val;
    }

    @Test
    public void test() {
        com.uc.alg.TreeNode node = TreeNode.getNode();
        int ma = maxPathSum(node);
        System.out.println(ma);
    }



}
