package com.li.algorithm.dynamic;

import java.util.Scanner;

public class KnapsackProblemDemo5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[] v = new int[n], w = new int[n], s = new int[n];
        int[] dp = new int[m + 1];
        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            s[i] = sc.nextInt();
        }

        // 多重背包
        /*
        5 1000
        80 20 4
        40 50 9
        30 50 7
        40 30 6
        20 20 1
         */
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                // 从01背包的状态转移方程式，去增加第i个物品拿k个数量的循环
                for (int k = 0; k <= s[i - 1] && k <= j / v[i - 1]; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * v[i - 1]] + k * w[i - 1]);
                }
            }
        }
        System.out.println(dp[m]);
    }
}
