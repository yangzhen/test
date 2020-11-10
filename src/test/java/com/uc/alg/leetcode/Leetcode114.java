package com.uc.alg.leetcode;

import org.junit.Test;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 *  
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author yangxinyan
 * @date 2020/11/1 16:02
 */
public class Leetcode114 {

  public void flatten(TreeNode root) {
      if(root == null) {
        return;
      }

      flatten(root.left);
      flatten(root.right);

      TreeNode right = root.right;
      TreeNode left = root.left;

      root.left =null;
      root.right = left;


      TreeNode p = root;
      while (p.right != null) {
        p = p.right;
      }
      p.right = right;
  }

  @Test
  public void test() {

  }

}
