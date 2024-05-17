package com.li.datastructures.tree;

/**
 * @author LiXL
 * @data 2023/9/12
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node1 = new Node(3);
        Node node2 = new Node(6);
        Node node3 = new Node(8);
        Node node4 = new Node(10);
        Node node5 = new Node(14);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;

        BinaryTree threadedBinaryTree = new BinaryTree(root);
        threadedBinaryTree.threadedNode();
        System.out.println(node4.left);
        System.out.println(node4.right);
        System.out.println();

        threadedBinaryTree.orderThreadedBinaryTree();
    }
}
