package com.uc.alg;

import org.junit.Test;

//http://blog.csdn.net/ljiabin/article/details/41984511
public class NodeTest {


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


    private static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    public static ListNode rotateRight(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        int length = getLength(head);
        n = n % length;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        ListNode tail = dummy;
        for (int i = 0; i < n; i++) {
            head = head.next;
        }

        while (head.next != null) {
            tail = tail.next;
            head = head.next;
        }

        head.next = dummy.next;
        dummy.next = tail.next;
        tail.next = null;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode = rotateRight(listNode1,2);
        while(listNode != null) {
            System.out.print(listNode.val + "," );
            listNode = listNode.next;
        }

    }

    @Test
    public void testResver() {
        print(listNode1);
        System.out.println();
        ListNode listNode = resverNode(listNode1);
        print(listNode);
    }


    @Test
    public void testResver2() {
        ListNode node = resverNodeIter(listNode1);
        print(node);
    }

    public ListNode resverNodeIter(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode prev = resverNodeIter(head.next);
        head.next.next=head;
        head.next = null;
        return prev;
    }

    public ListNode resverNode(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while(head.next != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        head.next = pre;
        return head;
    }


    private void print(ListNode listNode) {
        while(listNode != null) {
            System.out.print(listNode.val + "->" );
            listNode = listNode.next;
        }
        System.out.println();
    }
}

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
