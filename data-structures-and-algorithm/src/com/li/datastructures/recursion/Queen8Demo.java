package com.li.datastructures.recursion;

/**
 * @author LiXL
 * @date 2022/10/18
 */
public class Queen8Demo {

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d次解法\n", queen8.printCount);
        System.out.printf("一共有%d次判断\n", queen8.judgeCount);
    }
}

class Queen8 {

    // 定义一个max表示共有多少个皇后
    int maxQueue = 8;
    // 定义数组，保存皇后放置的结果
    int[] arr = new int[maxQueue];

    int printCount = 0;
    int judgeCount = 0;

    public void check(int n) {
        if (n == maxQueue) {
            print();
            return;
        }

        // 在当前行每一列依次放入皇后，并判断是否冲突，并再该循环产生回溯
        for (int i = 0; i < maxQueue; i++) {
            arr[n] = i;
            if (judge(n)) {
                // 如果不冲突，开始递归，放置 n + 1 个皇后
                check(n + 1);
            }
        }
    }

    // 当摆放第 n 个皇后后，检测该位置是否和前面的皇后冲突
    public boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // arr[i] == arr[n]：表示同一列
            // Math.abs(n - i) == Math.abs(arr[n] - arr[i])：表示同一斜线
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    // 输出皇后摆放的位置
    public void print() {
        printCount++;
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
