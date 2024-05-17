package com.li.algorithm.search;

/**
 * @author LiXL
 * @date 2023/12/11
 */
public class BinarySearchNoRecursionDemo {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = search(arr, 67);
        System.out.println(index);
    }

    public static int search(int[] arr, int value) {
        int l = 0, r = arr.length - 1, mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (value == arr[mid]) {
                return mid;
            } else if (value < arr[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }
}
