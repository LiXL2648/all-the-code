package com.li.algorithm.sort;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/2/12
 */
public class BubbleSortDemo {

    public static void main(String[] args) {
        // int[] arr = {3, 9, -1, 10, -2};
        int max = 1000000;
        int[] arr = new int[max];
        for (int i = 0; i < max; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        // System.out.println("排序前：" + Arrays.toString(arr));
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间：" + (end - start));
        // System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {

        int temp;
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            // System.out.printf("第%d次排序后的数组：", i + 1);
            // System.out.println(Arrays.toString(arr));
            if (flag) {
                break;
            } else {
                flag = true;
            }
        }
    }
}
