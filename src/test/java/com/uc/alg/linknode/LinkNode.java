package com.uc.alg.linknode;

import com.uc.alg.bean.ListNode;
import java.util.List;
import org.junit.Test;

/**
 * @author yangxinyan
 * @date 2020/1/6 10:24
 */
public class LinkNode {

  private static ListNode listNode1 = new ListNode(1);
  private static ListNode listNode2 = new ListNode(2);
  private static ListNode listNode3 = new ListNode(3);
  private static ListNode listNode4 = new ListNode(4);
  private static ListNode listNode5 = new ListNode(5);

  static {
    listNode1.next = listNode2;
    listNode2.next = listNode3;
    listNode3.next=listNode4;
    listNode4.next=listNode5;
  }

  @Test
  public void testResver() {
    ListNode node = resver(listNode1);
    printNode(node);
  }

  public void printNode(ListNode node) {
    System.out.println();
    while(node!=null) {
      System.out.print(node.val+" -> ");
      node=node.next;
    }
    System.out.println();
  }

  public ListNode resver(ListNode head) {
    ListNode next = null;
    ListNode current = head;

    while(current != null) {
      ListNode tmp = current.next;
      current.next=next;
      next=current;
      current = tmp;
    }
    return next;
  }
}
