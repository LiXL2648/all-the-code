package com.li.algorithm.search;

import java.util.Arrays;
import java.util.List;

/**
 * @author LiXL
 * @date 2023/2/26
 */
public class BinarySearchDemo {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        int index = binarySearch(arr, 0, arr.length - 1, 1);
        System.out.println(index);

        int[] indexes = binarySearchAll(arr, 0, arr.length - 1, 1000);
        System.out.println(Arrays.toString(indexes));
    }

    public static int binarySearch(int[] arr, int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (value == arr[mid]) {
            return mid;
        } else if (value < arr[mid]) {
            return binarySearch(arr, left, mid - 1, value);
        } else {
            return binarySearch(arr, mid + 1, right, value);
        }
    }

    public static int[] binarySearchAll(int[] arr, int left, int right, int value) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        if (value == arr[mid]) {
            int[] indexes = new int[arr.length];
            int temp = mid - 1, index = 0;
            while (temp > 0 && arr[temp] == value) {
                indexes[index++] = temp--;
            }
            indexes[index++] = mid;
            temp = mid + 1;
            while (temp < arr.length && arr[temp] == value) {
                indexes[index++] = temp++;
            }
            return Arrays.copyOfRange(indexes, 0, index);
        } else if (value < arr[mid]) {
            return binarySearchAll(arr, left, mid - 1, value);
        } else {
            return binarySearchAll(arr, mid + 1, right, value);
        }
    }
}
