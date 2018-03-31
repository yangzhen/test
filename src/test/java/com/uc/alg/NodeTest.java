package com.uc.alg;

//http://blog.csdn.net/ljiabin/article/details/41984511
public class NodeTest {

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
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next=listNode4;
        listNode4.next=listNode5;

        ListNode listNode = rotateRight(listNode1,2);

        while(listNode != null) {
            System.out.print(listNode.val + "," );
            listNode = listNode.next;
        }
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
