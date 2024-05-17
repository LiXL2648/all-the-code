package com.li.algorithm.dynamic;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/12/12
 */
public class KnapsackProblemDemo {

    public static void main(String[] args) {
        // 物品的重量
        int[] w = {1, 4, 3};
        // 物品的价值
        int[] val = {1500, 3000, 2000};
        // 物品的个数
        int n = w.length;
        // 背包的容量
        int m = 4;
        // 保存物品的存放记录
        int[][] path = new int[n + 1][m + 1];
        // 创建二维数组，横坐标表示放置的物品，纵坐标表示背包的容量
        int[][] v = new int[n + 1][m + 1];

        // 初始化第一行
        for (int i = 0; i <= m; i++) {
            v[0][i] = 0;
        }
        // 初始化第一列
        for (int i = 0; i <= n; i++) {
            v[i][0] = 0;
        }

        // 动态规划
        for (int i = 1; i <= n; i++) {
            // 不处理第一行
            for (int j = 1; j <= m; j++) {
                // 不处理第一列
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    // v[i][j] = Math.max(v[i - 1][j], val[i -1 ] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] > val[i -1 ] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        v[i][j] = val[i -1 ] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    }
                }
            }
        }

        // 打印数组
        for (int i = 0; i <= n; i++) {
            System.out.println(Arrays.toString(v[i]));
        }

        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (path[i][j] > 0) {
                System.out.printf("第%d个产品放入背包\n", i);
                j = j - w[i - 1];
            }
            i--;
        }
    }
}
