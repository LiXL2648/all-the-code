package com.li.algorithm.dynamic;

import java.util.Scanner;

public class KnapsackProblemDemo7 {

    public static void main(String[] args) {
        // 二维背包
        /*
        5 60
        5
        3 36 120
        10 25 129
        5 50 250
        1 45 130
        4 20 119
         */
        Scanner sc = new Scanner(System.in);
        // 所需要的氧容量
        int m = sc.nextInt();
        // 所需要的氮容量
        int n = sc.nextInt();
        // 气缸个数
        int k = sc.nextInt();

        int[] a = new int[k], b = new int[k], c = new int[k];
        for (int i = 0; i < k; i++) {
            // 氧容量
            a[i] = sc.nextInt();
            // 氮容量
            b[i] = sc.nextInt();
            // 气缸重量
            c[i] = sc.nextInt();
        }

        // 先通过一维背包实现
        int[][] dp = new int[k + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            // 第一行初始化为较大值，意思是不拿任何物品需要满足j氧容量所需要气缸重量
            dp[0][i] = 10000;
        }
        // 第一个元素初始化为0，意思是不拿任何物品需要满足0氧容量所需要气缸重量
        dp[0][0] = 0;

        // 使用dp二维数组实现
        for (int i = 1; i <= k; i++) {
            // 顺推，从小到大遍历
            for (int j = 1; j <= m; j++) {
                if (j <= a[i - 1]) {
                    dp[i][j] = Math.min(dp[i - 1][j], c[i - 1]);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - a[i - 1]] + c[i - 1]);
                }
            }
        }
        System.out.println(dp[k][m]);
    }
}
