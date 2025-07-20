package com.li.algorithm.dynamic;

import java.util.Scanner;

public class KnapsackProblemDemo2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        int[] w = new int[n], c = new int[n];
        int[] dp = new int[m + 1];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
            c[i] = sc.nextInt();
        }

        /*
         10 4
         2 1
         3 3
         4 5
         7 9
         */
        // 01背包，使用dp一维数组
        for (int i = 1; i <= n; i++) {
            // 逆推，从大到小遍历
            for (int j = m; j >= 1; j--) {
                if (j >= w[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j -w[i - 1]] + c[i - 1]);
                }
            }
            for (int k = 0; k <= m; k++) {
                System.out.print(dp[k] + "\t");
            }
            System.out.println();
        }
    }
}
