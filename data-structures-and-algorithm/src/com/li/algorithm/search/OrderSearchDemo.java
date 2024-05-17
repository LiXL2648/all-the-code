package com.li.algorithm.search;

/**
 * @author LiXL
 * @date 2023/2/26
 */
public class OrderSearchDemo {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(orderSearch(arr, 1));
    }

    public static int orderSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
