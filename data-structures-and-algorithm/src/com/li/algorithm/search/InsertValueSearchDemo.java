package com.li.algorithm.search;

/**
 * @author LiXL
 * @date 2023/2/26
 */
public class InsertValueSearchDemo {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 1; i <= 100; i++) {
            arr[i - 1] = i;
        }

        int index = insertValueSearch(arr, 0, arr.length - 1, 26);
        System.out.println(index);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int value) {
        System.out.println("hello");
        if (left > right || value < arr[left] || value > arr[right]) {
            return -1;
        }

        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        if (value == arr[mid]) {
            return mid;
        } else if (value < arr[mid]) {
            return insertValueSearch(arr, left, mid - 1, value);
        } else {
            return insertValueSearch(arr, mid + 1, right, value);
        }
    }
}
