package com.li.algorithm.dynamic;

public class KnapsackProblemDemo1 {

    public static void main(String[] args) {

        // 物品重量
        int[] w = {1, 4, 3};
        // 物品价值
        int[] v = {1500, 3000, 2000};
        // 物品个数
        int n = w.length;
        // 背包重量
        int m = 4;

        int[][] val = new int[n + 1][m + 1];
        int[][] path = new int[n + 1][m + 1];
        // 初始化第一行
        for (int i = 0; i <= m; i++) {
            val[0][i] = 0;
        }
        // 初始化第一列
        for (int i = 0; i <= n; i++) {
            val[i][0] = 0;
        }

        // 01背包使用dp二维数组
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (w[i - 1] > j) {
                    val[i][j] = val[i - 1][j];
                } else {
                    if (val[i - 1][j] > v[i - 1] + val[i - 1][j - w[i - 1]]) {
                        val[i][j] = val[i - 1][j];
                    } else {
                        val[i][j] = v[i - 1] + val[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                System.out.print(val[i][j] + "\t");
            }
            System.out.println();
        }

        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入背包\n", i);
                j = j - w[i - 1];
            }
            i--;
        }
    }
}
