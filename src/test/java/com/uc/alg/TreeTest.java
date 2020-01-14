package com.uc.alg;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.tree.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.omg.CORBA.INTERNAL;

/**
 * @author yangxinyan
 * @date 2018/1/21
 */
public class TreeTest {

  /**
   *         1
   *      2     3
   *    4  5  6
   *         7
   *
   */

  Node left124 = new Node(4, null, null);
  Node right125 = new Node(5, null, null);
  Node left12 = new Node(2, left124, right125);

  Node left1367 = new Node(7, null, null);
  Node left136 = new Node(6, left1367, null);
  Node right13 = new Node(3, left136, null);

  Node root = new Node(1, left12, right13);

  @Test
  public void testLeftSum() {
    int sum = leftSum(root);
    System.out.println("=======>"+sum);
  }

  public int leftSum(Node node) {
    if(node==null){
      return 0;
    }
    System.out.println(node.value);
    if(node.left != null && node.left.left == null && node.left.right ==null) {
      System.out.println(node.value+"->"+node.left.value);
      return node.left.value;
    }
    return leftSum(node.left) + leftSum(node.right);
  }

  @Test
  public void test() {

    print(Arrays.asList(root));

    List<List<Integer>> list = levelOrder(root);
    list.stream().forEach(t -> System.out.println(t));

    reservse(root);

    List<List<Integer>> list2 = levelOrder(root);
    list2.stream().forEach(t -> System.out.println(t));
  }

  /**
   * 翻转二叉树
   */
  public void reservse(Node node) {
    if (node == null) {
      return;
    }

    reservse(node.left);
    reservse(node.right);

    Node tmp = node.left;
    node.left = node.right;
    node.right = tmp;
  }

  /**
   * 二叉树按层遍历
   */
  public List<List<Integer>> levelOrder(Node node) {

    if (node == null) {
      return Collections.emptyList();
    }

    List<List<Integer>> list = new ArrayList<>();
    Queue<Node> queue = new LinkedList<>();
    queue.add(node);

    while (!queue.isEmpty()) {
      List<Integer> childList = new ArrayList<>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Node node1 = queue.poll();
        if (node1 == null) {
          continue;
        }
        childList.add(node1.value);
        if (node1.left != null) {
          queue.add(node1.left);
        }
        if (node1.right != null) {
          queue.add(node1.right);
        }
      }
      list.add(childList);
    }

    return list;
  }

  /**
   * 按层遍历打印二叉树
   */
  public List<Node> print(List<Node> list) {
    if (list.isEmpty()) {
      return Collections.emptyList();
    }
    List<Node> nodeList = Lists.newArrayList();
    for (Node node : list) {
      System.out.print(node.value + ",");
      if (node.left != null) {
        nodeList.add(node.left);
      }
      if (node.right != null) {
        nodeList.add(node.right);
      }
    }
    System.out.println();
    return print(nodeList);
  }


  @Test
  public void testpreorderTraversal() {
    List<Integer> list = preorderTraversal(root);
    System.out.println(list);
  }

  public List<Integer> preorderTraversal(Node root) {
    LinkedList<Integer> res = new LinkedList<>();
    if (root == null) {
      return res;
    }
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      res.add(node.value);
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }
    return res;
  }
}


class Node {

  public Node left;
  public Node right;
  public Integer value;

  public Node(Integer value, Node left, Node right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    return "Node{" +
        "left=" + left +
        ", right=" + right +
        ", value=" + value +
        '}';
  }
}
