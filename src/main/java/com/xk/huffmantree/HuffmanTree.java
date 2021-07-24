package com.xk.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xk
 * @create 2021.07.22 18:55
 */
public class HuffmanTree {
    public static void main(String[] args) {

        int[] arr = {2,6,9,4,7,1};
        Node root = createHuffman(arr);

        preOrder(root);
    }

    public static void preOrder(Node root){

        if(root != null){
            root.preOrder();
        }

    }

    /**
     * 创建赫夫曼数
     * @param arr
     * @return
     */
    public static Node createHuffman(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            Collections.sort(nodes);
            System.out.println("nodes:" + nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}


/**
 * 结点
 */
class Node implements Comparable<Node>{
    private int value;

    public Node left;

    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node node) {

        return this.value-node.value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void preOrder() {
        System.out.println(this.value);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}
