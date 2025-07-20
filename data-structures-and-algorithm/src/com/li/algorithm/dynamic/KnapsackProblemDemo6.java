package com.li.algorithm.dynamic;

import java.util.Scanner;

public class KnapsackProblemDemo6 {

    public static void main(String[] args) {
        // 多重背包二进制优化
        /*
        5 1000
        80 20 4
        40 50 9
        30 50 7
        40 30 6
        20 20 1
         */
        Scanner sc = new Scanner(System.in);
        // 物品数量
        int n = sc.nextInt();
        // 总价格
        int m = sc.nextInt();
        // dp 数组
        int[] dp = new int[m + 1];
        int v, w, s;
        for (int i = 0; i < n; i++) {
            // 物品价格
            v = sc.nextInt();
            // 物品价值
            w = sc.nextInt();
            // 物品数量
            s = sc.nextInt();

            // 二进制拆分
            for (int k = 1; k <= s; k *= 2) {
                int kv = k * v;
                int kw = k * w;
                // 01背包dp数组逆推
                for (int j = m; j >= kv; j--) {
                    dp[j] = Math.max(dp[j], dp[j - kv] + kw);
                }
                s -= k;
            }
            // 处理剩余部分
            if (s > 0) {
                int sv = s * v;
                int sw = s * w;
                for (int j = m; j >= sv; j--) {
                    dp[j] = Math.max(dp[j], dp[j - sv] + sw);
                }
            }
        }
        System.out.println(dp[m]);
    }
}
