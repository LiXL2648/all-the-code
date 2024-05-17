package com.li.datastructures.tree;

/**
 * @author LiXL
 * @date 2023/12/5
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        // int[] arr = {4, 3, 6, 5, 7, 8};
        // int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {2, 1, 6, 5, 7, 3};
        for (int i : arr) {
            binaryTree.add(new Node(i));
        }
        binaryTree.infixOrder();

        System.out.println(binaryTree.root.height());
        System.out.println(binaryTree.root.left.height());
        System.out.println(binaryTree.root.right.height());
    }
}
