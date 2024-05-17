package com.li.algorithm.sort;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/2/25
 */
public class RadixSortDemo {

    public static void main(String[] args) {
        // int[] arr = {53, 3, 542, 748, 14, 214};

        int max = 10000000;
        int[] arr = new int[max];
        for (int i = 0; i < max; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        // System.out.println("排序前：" + Arrays.toString(arr));
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间：" + (end - start));
        // System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        // 定义一个二位数组，表示有 10 个桶，为了防止数组溢出，每个一维数组大小需要定义为 arr.length
        int[][] buckets = new int[10][arr.length];
        // 定义一个一维数组记录各个桶中每次放入数据的个数
        int[] bucketEleCount = new int[10];
        int digitOfEle, index, max = arr[0];
        // 得到数组中最大的数的位数
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int maxLen = String.valueOf(max).length();
        for (int k = 0, n = 1; k < maxLen; k++, n *= 10) {
            for (int value : arr) {
                // 计算每个元素的位数
                digitOfEle = (value / n) % 10;
                // 将元素放入对应的桶中
                buckets[digitOfEle][bucketEleCount[digitOfEle]] = value;
                bucketEleCount[digitOfEle]++;
            }
            index = 0;
            for (int i = 0; i < bucketEleCount.length; i++) {
                for (int j = 0; j < bucketEleCount[i]; j++) {
                    // 顺序将每个桶中的元素放回原数组中
                    arr[index++] = buckets[i][j];
                }
                bucketEleCount[i] = 0;
            }
        }
    }
}
