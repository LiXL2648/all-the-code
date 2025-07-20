package com.li.algorithm.dynamic;

import java.util.Scanner;

public class KnapsackProblemDemo4 {

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
            // 正推，正序遍历，因为需要使用到当前dp数组的最新值
            for (int j = 1; j <= m; j++) {
                // 该判断可以省略，只需要让j的初始值为 w[i - 1]
                if (j >= w[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - w[i - 1]] + c[i - 1]);
                }
            }
            for (int k = 0; k <= m; k++) {
                System.out.print(dp[k] + "\t");
            }
            System.out.println();
        }
    }
}
