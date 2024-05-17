package com.li.datastructures.tree;

/**
 * @author LiXL
 * @date 2023/12/5
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinaryTree binaryTree = new BinaryTree();
        for (int num : arr) {
            binaryTree.add(new Node(num));
        }

        binaryTree.infixOrder();
        System.out.println();

        binaryTree.del(2);
        binaryTree.del(5);
        binaryTree.del(9);
        binaryTree.del(12);
        binaryTree.del(7);
        binaryTree.del(3);
        binaryTree.del(10);
//        binaryTree.del(1);
        binaryTree.infixOrder();
    }
}
