package com.uc.alg.leetcode;

import com.uc.alg.bean.ListNode;
import org.junit.Test;

/**
 * @author yangxinyan
 * @date 2020/11/1 12:17
 */
public class Leetcode206 {

  static ListNode n1 = new ListNode(1);
  static ListNode n2= new ListNode(2);
  static ListNode n3= new ListNode(3);
  static ListNode n4= new ListNode(4);

  static {
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
  }

  public ListNode reverseList(ListNode head) {
    if(head == null || head.next == null) {
      return head;
    }
    ListNode  p= reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return p;
  }

  public ListNode reverseList2(ListNode head) {
    if(head == null || head.next == null) {
      return head;
    }
    ListNode pre = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode temp = curr.next;
      curr.next = pre;
      pre = curr;
      curr = temp;
    }
    return pre;
  }




  @Test
  public void test1() {
    ListNode p = reverseList(n1);
    while (p!=null) {
      System.out.print(p.val + " -> ");
      p=p.next;
    }
    System.out.println();
  }


  @Test
  public void test2() {
    ListNode p = reverseList2(n1);
    while (p!=null) {
      System.out.print(p.val + " -> ");
      p=p.next;
    }
    System.out.println();
  }
}
