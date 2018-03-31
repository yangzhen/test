package com.uc.alg;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yangxinyan
 * @date 2018/1/21
 */
public class TreeTest {

    @Test
    public void test() {

        Node left124 = new Node(4,null,null);
        Node right125 = new Node(5,null,null);
        Node left12 = new Node(2,left124,right125);

        Node left136 = new Node(6,null,null);
        Node right13 = new Node(3,left136,null);

        Node root = new Node(1,left12, right13);
        print(Arrays.asList(root));
    }

    public List<Node> print(List<Node> list) {
        if(list.isEmpty()) {
            return Collections.emptyList();
        }
        List<Node> nodeList = Lists.newArrayList();
        for(Node node : list) {
            System.out.print(node.value+",");
            if(node.left != null) {
                nodeList.add(node.left);
            }
            if(node.right != null){
                nodeList.add(node.right);
            }
        }
        System.out.println();
        return print(nodeList);
    }
}

class Node {
    public final Node left;
    public final Node right;
    public final Integer value;

    public Node(Integer value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
