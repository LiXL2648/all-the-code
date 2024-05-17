package com.li.algorithm.sort;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/2/12
 */
public class InsertSortDemo {

    public static void main(String[] args) {
        // int[] arr = {101, 34, 119, 1};
        int max = 10000000;
        int[] arr = new int[max];
        for (int i = 0; i < max; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        // System.out.println("排序前：" + Arrays.toString(arr));
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间：" + (end - start));
        // System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        // 有序序列的最后一个元素下标
        int index;
        // 无序序列的第一个元素
        int temp;
        for (int i = 1; i < arr.length; i++) {
            index = i - 1;
            temp = arr[i];
            while (index >= 0 && temp < arr[index]) {
                // 如果没找到位置，则元素向后移动
                arr[index + 1] = arr[index];
                index--;
            }
            if (index != i) {
                arr[index + 1] = temp;
            }
        }
    }
}
