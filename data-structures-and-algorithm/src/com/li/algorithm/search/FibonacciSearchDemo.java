package com.li.algorithm.search;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/2/26
 */
public class FibonacciSearchDemo {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibonacciSearch(arr, 1234));
    }

    public static int[] fibonacci(int len) {
        int[] fibonacci = new int[len];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        int i;
        for (i = 2; i < len; i++) {
            fibonacci[i] = fibonacci[i - 2] + fibonacci[i - 1];
            if (fibonacci[i] >= len) {
                break;
            }
        }
        return Arrays.copyOfRange(fibonacci, 0, i + 1);
    }

    public static int fibonacciSearch(int[] arr, int value) {
        int[] fibonacci = fibonacci(arr.length);
        int low = 0, high = arr.length - 1, k = fibonacci.length - 1, mid;
        int[] temp = Arrays.copyOf(arr, fibonacci[k]);
        for (int i = high + 1; i < fibonacci[k]; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            mid = fibonacci[k - 1] - 1 + low;
            if (value == temp[mid]) {
                return Math.min(mid, high);
            } else if (value < temp[mid]) {
                high = mid - 1;
                k -= 1;
            } else {
                low = mid + 1;
                k -= 2;
            }
        }

        return -1;
    }
}
