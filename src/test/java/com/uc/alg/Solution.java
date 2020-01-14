package com.uc.alg;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.junit.Test;


/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明： 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *    5
 *   / \
 *   3   6
 *  /\
 * 2  4
 * /
 * 1 输出: 3
 */
class TreeNode {

  int val = 0;
  TreeNode left = null;
  TreeNode right = null;

  public TreeNode(int val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return "TreeNode{" +
        "val=" + val +
        ", left=" + left +
        ", right=" + right +
        '}';
  }
}

public class Solution {

  int index = 0;

  TreeNode kthNode(TreeNode pRoot, int k) {
    if (pRoot != null) {
      TreeNode node = kthNode(pRoot.left, k);
      if (node != null) {
        return node;
      }
      index++;
      if (index == k) {
        return pRoot;
      }
      node = kthNode(pRoot.right, k);
      if (node != null) {
        return node;
      }
    }
    return null;
  }

  public TreeNode middlePrint(TreeNode node, int k) {
    if (node != null) {
      middlePrint(node.left, k);
      if (++index == k) {
        System.out.println("*******" + node);
        return node;
      }
      System.out.println(node.val);
      middlePrint(node.right, k);
    }
    return null;
  }

  /**
   * 中序遍历二叉树
   * @param root
   * @param result
   */
  public void inOrder(TreeNode root,List<Integer> result) {
    if(root == null) {
      return;
    }
    inOrder(root.left, result);
    result.add(root.val);
    inOrder(root.right, result);

  }

  /**
   * 中序非递归遍历二叉树
   * @param root
   * @param result
   */
  public void inorderTraversal(TreeNode root, List<Integer> result) {
    if(root==null) {
      return;
    }
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    while(!stack.isEmpty() || cur !=null) {
      if(cur!=null) {
        stack.push(cur);
        cur=cur.left;
      }else {
        cur=stack.pop();
        result.add(cur.val);
        cur=cur.right;
      }
    }
  }

  public void preOrder(TreeNode node, List<Integer> result) {
    if(node == null) {
      return;
    }
    result.add(node.val);
    preOrder(node.left,result);
    preOrder(node.right,result);
  }

  public void preOrderTraversal(TreeNode node,List<Integer> result) {
    if(node == null) {
      return;
    }
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = node;
    while( curr != null || !stack.isEmpty()) {
      if(curr !=null) {
        stack.add(curr);
        result.add(curr.val);
        curr = curr.left;
      }
      if(curr == null) {
        TreeNode node1 = stack.pop();
        curr = node1.right;
      }

    }
  }

  public void postOrder(TreeNode node, List<Integer> result) {
    if(node == null) {
      return;
    }
    postOrder(node.left,result);
    postOrder(node.right,result);
    result.add(node.val);
  }

  //【后序】,[1, 2, 4, 3, 6, 5]
  @Test
  public void testPortOrder() {
    TreeNode root = getTreeNode();
    List<Integer> list = new ArrayList<>();
    postOrder(root,list);
    System.out.println("【后序】," + list);
  }

  //【前序】,[5, 3, 2, 1, 4, 6]
  @Test
  public void testPreOrder() {
    TreeNode root = getTreeNode();
    List<Integer> list = new ArrayList<>();
    preOrder(root,list);
    System.out.println(list);
    System.out.println("【前序】," + list);
  }

  //[前序非递归]，[5, 3, 2, 1, 4, 6]
  @Test
  public void testPreOrderTraversal() {
    TreeNode root = getTreeNode();
    List<Integer> list = new ArrayList<>();
    preOrderTraversal(root,list);
    System.out.println("[前序非递归]，" + list);
  }

  @Test
  public void testInorder() {
    TreeNode root = getTreeNode();
    List<Integer> list = new ArrayList<>();
    inOrder(root,list);
    System.out.println(list);
  }

  @Test
  public void testInorderTraversal() {
    TreeNode root = getTreeNode();
    List<Integer> list = new ArrayList<>();
    inorderTraversal(root,list);
    System.out.println(list);
  }

  @Test
  public void test() {
    TreeNode root = getTreeNode();

    TreeNode node = kthNode(root,4);
    System.out.println(node.val);

  }

  private TreeNode getTreeNode() {
    TreeNode root = new TreeNode(5);
    TreeNode root_left = new TreeNode(3);
    TreeNode root_right = new TreeNode(6);
    root.left = root_left;
    root.right = root_right;

    TreeNode root_left_left = new TreeNode(2);
    TreeNode root_left_right = new TreeNode(4);
    root_left.left = root_left_left;
    root_left.right = root_left_right;

    TreeNode root_left_left_left = new TreeNode(1);
    root_left_left.left = root_left_left_left;
    return root;
  }
}