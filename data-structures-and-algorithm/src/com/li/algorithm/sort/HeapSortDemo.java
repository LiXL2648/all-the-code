package com.li.algorithm.sort;

import java.util.Arrays;

/**
 * @author LiXL
 * @data 2023/9/16
 */
public class HeapSortDemo {

    public static void main(String[] args) {
        // 将数组进行升序排序
        // int[] arr = {4, 6, 8, 5, 9};

        int max = 10000000;
        int[] arr = new int[max];
        for (int i = 0; i < max; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        // System.out.println("排序前：" + Arrays.toString(arr));
        long start = System.currentTimeMillis();
        heapSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间：" + (end - start));
        // System.out.println("排序后：" + Arrays.toString(arr));
    }

    /**
     * 堆排序
     */
    public static void heapSort(int[] arr) {
        // 将无序序列构建成大顶堆，
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        int temp;
        for (int i = arr.length - 1; i > 0; i--) {
            // 将堆顶元素与末尾元素进行交换
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            // 这里只需要从堆顶进行调整，是因为堆顶元素与末尾元素进行交换，而除堆顶外，剩余结点已然构成大顶堆
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 完成以 i 对应的非叶子结点的树调整成大顶堆
     *
     * @param arr    待调整的数组
     * @param i      非叶子结点在数组中的索引
     * @param length 对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 先取出当前元素的值，保存在临时变量
        int temp = arr[i];
        // j 是 i 的左子结点
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            // 左子结点的值小于右子节点的值
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                // j 指向右子节点
                j++;
            }
            // 如果子结点大于父结点，则进行交换
            if (arr[j] > temp) {
                arr[i] = arr[j];
                // i 指向 j，继续循环比较
                i = j;
            } else {
                break;
            }
        }
        // 当 for 循环结束后，已经将以 i 为父结点的树的最大值，放在了最顶
        // 将 temp 值放到调整后的位置
        arr[i] = temp;
    }
}
