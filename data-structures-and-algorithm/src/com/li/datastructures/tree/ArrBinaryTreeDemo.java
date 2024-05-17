package com.li.datastructures.tree;

/**
 * @author LiXL
 * @data 2023/9/2
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        prevOrder(arr, 0); // 1 2 4 5 3 6 7
        System.out.println();
        infixOrder(arr, 0); // 4 2 5 1 6 3 7
        System.out.println();
        afterOrder(arr, 0); // 4 5 2 6 7 3 1
    }

    public static void prevOrder(int[] arr, int index) {
        if (arr == null || arr.length == 0 || index > arr.length - 1) {
            return;
        }
        System.out.print(arr[index]);
        prevOrder(arr, 2 * index + 1);
        prevOrder(arr, 2 * index + 2);
    }

    public static void infixOrder(int[] arr, int index) {
        if (arr == null || arr.length == 0 || index > arr.length - 1) {
            return;
        }
        infixOrder(arr, 2 * index + 1);
        System.out.print(arr[index]);
        infixOrder(arr, 2 * index + 2);
    }

    public static void afterOrder(int[] arr, int index) {
        if (arr == null || arr.length == 0 || index > arr.length - 1) {
            return;
        }
        afterOrder(arr, 2 * index + 1);
        afterOrder(arr, 2 * index + 2);
        System.out.print(arr[index]);
    }
}
