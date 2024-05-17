package com.li.datastructures.tree;

/**
 * @author LiXL
 * @date 2023/3/1
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree binaryTree = getFullBinaryTree();
        binaryTree.prevOrder(); // 1 2 4 5 3 6 7
        System.out.println();
        binaryTree.infixOrder(); // 4 2 5 1 6 3 7
        System.out.println();
        binaryTree.afterOrder(); // 4 5 2 6 7 3 1

        Node node = binaryTree.prevOrderSearch(6);
        System.out.println(node);
        System.out.println();

        Node node1 = binaryTree.infixOrderSearch(6);
        System.out.println(node1);
        System.out.println();

        Node node2 = binaryTree.afterOrderSearch(6);
        System.out.println(node2);
        System.out.println();

        binaryTree.delNode(6);
        binaryTree.prevOrder();
        System.out.println();

        binaryTree.delNode(3);
        binaryTree.prevOrder();
    }

    public static BinaryTree getFullBinaryTree() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        return new BinaryTree(node1);
    }
}
