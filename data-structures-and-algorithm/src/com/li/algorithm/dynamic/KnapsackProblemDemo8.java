package com.li.algorithm.dynamic;

import java.util.Scanner;

public class KnapsackProblemDemo8 {

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

        // 二维背包初始化
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // 将每个元素初始化为一个较大值
                dp[i][j] = 10000;
            }
        }
        // 第0个元素初始化为0
        dp[0][0] = 0;

        // 基于0/1背包dp一维数组实现二维背包
        for (int i = 1; i <= k; i++) {
            // 逆推，从大到小排序
            for (int j = m; j >= 0; j--) {
                for (int l = n; l >= 0; l--) {
                    // 当第i个气缸的氧和氮容量大于当前所需要氧容量时，取当前已知气缸重量与第i个气缸重量的最小值
                    if (j <= a[i - 1] && l <= b[i - 1]) {
                        dp[j][l] = Math.min(dp[j][l], c[i - 1]);
                    } else {
                        // 当第i个气缸的氧容量小于当前所需要氧容量时，剩余需要氧容量为j - a[i - 1]
                        int x = j > a[i - 1] ? j - a[i - 1] : 0;
                        // 当第i个气缸的氮容量小于当前所需要氮容量时，剩余需要氮容量为j - a[i - 1]
                        int y = l > b[i - 1] ? l - b[i - 1] : 0;

                        dp[j][l] = Math.min(dp[j][l], dp[x][y] + c[i - 1]);
                    }
                }
            }
        }
        System.out.println(dp[m][n]);
    }
}