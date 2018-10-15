package com.uc.j8;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yangzhen on 17/6/25.
 */


public class TreeEachTest {

    public static void main(String[] args) {
        Node root = new Node(3,null,null);
        Node root_l= new Node(9);
        Node root_r= new Node(20);
        root.leftNode=root_l;
        root.rigthNode=root_r;

        Node rl_left = new Node(8);
        Node rl_rigth = new Node(13);
        root_l.leftNode = rl_left;
        root_l.rigthNode = rl_rigth;

        Node rr_left = new Node(15);
        Node rr_rigth = new Node(7);
        root_r.leftNode=rr_left;
        root_r.rigthNode=rr_rigth;

        print(root);
    }

    public static void print(Node root) {

        LinkedList<Node> queue = new LinkedList<>();

        queue.offer(root);
        int current = 1;
        int next=0;

        while (current !=0){
            Node node = queue.poll();
            if(node != null){
                System.out.print(" " +node.value);
                current--;
            }
            Node left = node.leftNode;
            Node rigth = node.rigthNode;
            if(left!=null){
                next++;
                queue.offer(left);
            }
            if(rigth != null){
                next++;
                queue.offer(rigth);
            }
            if(current ==0){
                System.out.println("----- 一层遍历完毕 ");
                current=next;
                next=0;
            }
        }
    }

}


class Node {
    public int value = 0;
    public Node leftNode= null;
    public Node rigthNode = null;


    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node leftNode, Node rigthNode) {
        this.value = value;
        this.leftNode = leftNode;
        this.rigthNode = rigthNode;
    }


}