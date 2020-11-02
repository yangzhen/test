package com.uc.alg;

public class Main {

  class RandomListNode {

    public int label;
    public RandomListNode next = null;
    public RandomListNode random = null;

    public RandomListNode(int label) {
      this.label = label;
    }

    @Override
    public String toString() {
      return "RandomListNode{" +
          "label=" + label +
          '}';
    }
  }

  public RandomListNode Clone(RandomListNode pHead) {
    if (pHead == null) {
      return null;
    }
    RandomListNode temp = pHead;
    RandomListNode head = new RandomListNode(temp.label);
    RandomListNode p = head;
    if (temp.next != null) {
      temp = temp.next;
    }
    //首先把只复制基本的链表

    while (temp != null) {
      RandomListNode node = new RandomListNode(temp.label);
      p.next = node;
      node.next = null;
      p = p.next;
      temp = temp.next;
    }
    //然后复制random指针
    p = head;
    temp = pHead;
    while (p != null) {
      if (temp.random == null) {
        p.random = null;
        p = p.next;
        temp = temp.next;
        continue;
      }

      RandomListNode t = pHead;
      RandomListNode q = head;
      while (t != null) { //t和q只需要判断一个就行
        if (temp.random == t) {
          break;
        }
        t = t.next;
        q = q.next;
      }

      p.random = q;
      p = p.next;
      temp = temp.next;
    }
    return head;
  }

  public RandomListNode Clone2(RandomListNode pHead) {
    if (pHead == null) {
      return null;
    }
    RandomListNode currentNode = pHead;
    //1.复制每个结点，如复制结点A得到A1，将A1插到A后面
    while (currentNode != null) {
      RandomListNode copyNode = new RandomListNode(currentNode.label);
      RandomListNode p = currentNode.next;
      copyNode.next = p;
      currentNode.next = copyNode;
      currentNode = p;
    }

    //2.重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next
    currentNode = pHead;
    while (currentNode != null) {
      currentNode.next.random = (currentNode.random == null) ? null : currentNode.random.next;
      currentNode = currentNode.next.next;
    }

    //3.拆分链表，将原链表拆分成原链表和复制后的链表
    currentNode = pHead;
    RandomListNode copyHead = currentNode.next;
    while (currentNode != null) {
      RandomListNode p = currentNode.next;
      currentNode.next = p.next;
      if (p.next != null) {
        p.next = p.next.next;
      }
      currentNode = currentNode.next;
    }
    return copyHead;
  }

  public static void main(String[] args) {
    RandomListNode node1 = new Main().new RandomListNode(1);
    RandomListNode node2 = new Main().new RandomListNode(2);
    RandomListNode node3 = new Main().new RandomListNode(3);
    RandomListNode node4 = new Main().new RandomListNode(4);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = null;
    node1.random = null;
    node3.random = node1;
    RandomListNode head = new Main().Clone(node1);
    System.out.println(head);
    System.out.println(head.random);
    head = head.next;
    System.out.println(head.random);
    head = head.next;
    System.out.println(head.random);

    System.out.println("===========");
    RandomListNode head2 = new Main().Clone2(node1);
    System.out.println(head2);
    System.out.println(head2.random);
    head2 = head2.next;
    System.out.println(head2.random);
    head2 = head2.next;
    System.out.println(head2.random);
  }
}