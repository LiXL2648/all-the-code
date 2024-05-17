package com.li.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiXL
 * @date 2023/12/21
 */
public class HorseChessboard {

    // 棋盘行
    private final int X;
    // 棋盘列
    private final int Y;
    // 棋盘
    private final int[][] chessboard;
    // 棋盘相应的位置是否已经访问
    private final boolean[] isVisited;

    private boolean isFinished;

    public HorseChessboard(int x, int y) {
        this.X = x;
        this.Y = y;
        this.chessboard = new int[this.X][this.Y];
        this.isVisited = new boolean[this.X * this.Y];
        this.isFinished = false;
    }

    public static void main(String[] args) {
        HorseChessboard horseChessboard = new HorseChessboard(8, 8);
        long start = System.currentTimeMillis();
        horseChessboard.traversalChessboard(0, 0, 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        System.out.println();
        horseChessboard.show();
    }

    private void traversalChessboard(int row, int col, int step) {
        // 记录当前位置的步数
        chessboard[row][col] = step;
        // 记录当前位置为已访问
        isVisited[row * X + col] = true;
        // 获取当前位置的马儿可以走的位置集合
        List<Point> nextPoints = nextPoints(new Point(col, row));
        // 对下一步要走的位置集合进行非递减排序，排序的规则是，按照下一步要走的位置的下一步的位置集合元素个数进行排序
        // 贪心算法优化
        nextPoints.sort((p1, p2) -> nextPoints(p1).size() - nextPoints(p2).size());
        while (!nextPoints.isEmpty()) {
            Point point = nextPoints.remove(0);
            // 判断下一个位置是否已访问
            if (!isVisited[point.y * X + point.x]) {
                traversalChessboard(point.y, point.x, step + 1);
            }
        }
        // 判断马儿是否已完成任务，使用 step 和应该走的步数比较
        // 如果没有达到，则表示没有完成任务，进行回溯
        // step < X * Y 成立的情况有两种：
        // 1. 棋盘到目前位置仍然未走完
        // 2. 棋盘已经结束，但未完成，需要进行回溯
        if (step < X * Y && !isFinished) {
            // 将当前位置的步数设置为 0
            chessboard[row][col] = 0;
            // 将当前位置设置为未访问
            isVisited[row * X + col] = false;
        } else {
            isFinished = true;
        }
    }

    private List<Point> nextPoints(Point point) {
        List<Point> nextPoints = new ArrayList<>();
        Point next = new Point();
        // 判断马儿是否能走5的位置
        if ((next.x = point.x - 2) >= 0 && (next.y = point.y - 1) >= 0) {
            nextPoints.add(new Point(next));
        }
        // 判断马儿是否能走6的位置
        if ((next.x = point.x - 1) >= 0 && (next.y = point.y - 2) >= 0) {
            nextPoints.add(new Point(next));
        }
        // 判断马儿是否能走7的位置
        if ((next.x = point.x + 1) < X && (next.y = point.y - 2) >= 0) {
            nextPoints.add(new Point(next));
        }
        // 判断马儿是否能走0的位置
        if ((next.x = point.x + 2) < X && (next.y = point.y - 1) >= 0) {
            nextPoints.add(new Point(next));
        }
        // 判断马儿是否能走1的位置
        if ((next.x = point.x + 2) < X && (next.y = point.y + 1) < Y) {
            nextPoints.add(new Point(next));
        }
        // 判断马儿是否能走2的位置
        if ((next.x = point.x + 1) < X && (next.y = point.y + 2) < Y) {
            nextPoints.add(new Point(next));
        }
        // 判断马儿是否能走3的位置
        if ((next.x = point.x - 1) >= 0 && (next.y = point.y + 2) < Y) {
            nextPoints.add(new Point(next));
        }
        // 判断马儿是否能走4的位置
        if ((next.x = point.x - 2) >= 0 && (next.y = point.y + 1) < Y) {
            nextPoints.add(new Point(next));
        }
        return nextPoints;
    }

    public void show() {
        for (int[] rows : chessboard) {
            System.out.println(Arrays.toString(rows));
        }
    }
}
