package com.li.algorithm.sort;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/2/20
 */
public class QuickSortDemo {

    public static void main(String[] args) {
        // int[] arr = {-9, 78, 0, 23, -567, 70};
        int max = 10000000;
        int[] arr = new int[max];
        for (int i = 0; i < max; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        // System.out.println("排序前：" + Arrays.toString(arr));
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间：" + (end - start));
        // System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left, r = right, pivot = arr[(l + r) / 2], temp;
        // while 循环是让小于 pivot 的值放在 pivot 左边，大于 pivot 的值放在 pivot 右边
        while (l < r) {
            // 在 pivot 左边一直找，直到找到大于等于 pivot 的值为止
            while (arr[l] < pivot) {
                l++;
            }
            // 在 pivot 右边一直找，直到找到小于等于 pivot 的值为止
            while (arr[r] > pivot) {
                r--;
            }
            // 如果 l >= r，则数组 arr 已经是 pivot 的左边都小于 pivot，pivot 的右边都大于 pivot
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换后，arr[l] == pivot。则 r 前移
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换后，arr[r] == pivot。则 l 前移
            if (arr[r] == pivot) {
                l++;
            }
        }

        // 如果 l == r，必须 l++，r--
        if (l == r) {
            l++;
            r--;
        }

        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }

        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
