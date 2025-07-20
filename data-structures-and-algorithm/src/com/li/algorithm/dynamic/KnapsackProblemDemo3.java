package com.li.algorithm.dynamic;

import java.util.Scanner;

public class KnapsackProblemDemo3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        int[] w = new int[n], c = new int[n];
        int[] dp = new int[m + 1];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
            c[i] = sc.nextInt();
        }

        // 完全背包，使用dp一维数组
        /*
         10 4
         2 1
         3 3
         4 5
         7 9
         */
        for (int i = 1; i <= n; i++) {
            // 逆推，从大到小遍历
            for (int j = m; j >= 1; j--) {
                // 使用多一层循环，判断取多少件当前物品，达到背包的容量
                for (int k = 1; k <= j / w[i - 1]; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * w[i - 1]] + k * c[i - 1]);
                }
            }
            for (int j = 0; j <= m; j++) {
                System.out.print(dp[i] + "\t");
            }
            System.out.println();
        }
    }
}
