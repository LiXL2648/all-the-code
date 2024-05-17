package com.li.algorithm.sort;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/2/12
 */
public class ShellSortDemo {

    public static void main(String[] args) {
        // int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int max = 10000000;
        int[] arr = new int[max];
        for (int i = 0; i < max; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        // System.out.println("排序前：" + Arrays.toString(arr));
        long start = System.currentTimeMillis();
        // shellSortExchange(arr);
        shellSortInsert(arr);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间：" + (end - start));
        // System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void shellSortExchange(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                if (arr[i] < arr[i - gap]) {
                    for (int j = i - gap; j >= 0; j -= gap) {
                        if (arr[j] > arr[j + gap]) {
                            temp = arr[j];
                            arr[j] = arr[j + gap];
                            arr[j + gap] = temp;
                        }
                    }
                }
            }
        }
    }

    public static void shellSortInsert(int[] arr) {
        int index, temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                if (arr[i] < arr[i - gap]) {
                    index = i - gap;
                    temp = arr[i];
                    while (index >= 0 && temp < arr[index]) {
                        arr[index + gap] = arr[index];
                        index -= gap;
                    }
                    arr[index + gap] = temp;
                }
            }
        }
    }
}
