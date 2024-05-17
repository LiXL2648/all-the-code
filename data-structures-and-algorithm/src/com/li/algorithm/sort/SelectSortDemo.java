package com.li.algorithm.sort;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/2/12
 */
public class SelectSortDemo {

    public static void main(String[] args) {
        // int[] arr = {101, 34, 119, 1};
        int max = 1000000;
        int[] arr = new int[max];
        for (int i = 0; i < max; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        // System.out.println("排序前：" + Arrays.toString(arr));
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间：" + (end - start));
        // System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {

        int temp, index;
        for (int i = 0; i < arr.length - 1; i++) {
            temp = arr[i];
            index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (temp > arr[j]) {
                    temp = arr[j];
                    index = j;
                }
            }
            if (i != index) {
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
    }
}