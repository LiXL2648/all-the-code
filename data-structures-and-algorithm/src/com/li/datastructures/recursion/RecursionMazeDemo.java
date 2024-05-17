package com.li.datastructures.recursion;

/**
 * @author LiXL
 * @date 2022/10/7
 * 递归-迷宫问题
 */
public class RecursionMazeDemo {
    public static void main(String[] args) {
        Maze maze = new Maze();
        int[][] map = maze.createMap();
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        maze.setWay(map, 1, 1);
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}

class Maze {

    /**
     * 迷宫寻找路径
     */
    public boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else if (map[i][j] == 0) {
            // 如果当前这个点还没走过，则标记为通路可以走，并按照策略下右上左的顺序进行尝试
            map[i][j] = 2;
            if (setWay(map, i + 1, j)) {
                // 向下
                return true;
            } else if (setWay(map, i, j + 1)) {
                // 向右
                return true;
            } else if (setWay(map, i - 1, j)) {
                // 向上
                return true;
            } else if (setWay(map, i, j - 1)) {
                // 向左
                return true;
            } else {
                // 如果都走不通，则标记该点已经走过，但是走不通
                map[i][j] = 3;
                return false;
            }
        } else {
            // 如果该点已经走过了，则直接返回
            return false;
        }
    }

    /**
     * 创建地图
     */
    public int[][] createMap() {
        // 使用二维数组模拟地图
        int[][] map = new int[8][7];
        // 设置地图障碍
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for (int i = 1; i < 7; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;
//        map[3][3] = 1;
//        map[3][4] = 1;
//        map[3][5] = 1;
        return map;
    }
}
