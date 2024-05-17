package com.li.datastructures.array;

/**
 * @author LiXL
 * @date 2022/9/27
 * 二维数组与稀疏数组相互转换
 */
public class SparseArrayDemo {

    public static void main(String[] args) {
        // 创建二维数组
        int[][] twoDimensionalArray = createTwoDimensionalArray();
        // 遍历二维数组
        for (int[] ints : twoDimensionalArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
        System.out.println("--------------------------");

        // 将二维数组转换为稀疏数组
        int[][] sparseArray = toSparseArray(twoDimensionalArray);
        // 遍历稀疏数组
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
        System.out.println("--------------------------");

        // 将稀疏数组转换为二维数组
        int[][] twoDimensionalArray2 = toTwoDimensionalArray(sparseArray);
        // 遍历二维数组
        for (int[] ints : twoDimensionalArray2) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
    }

    /**
     * 将稀疏数组转换为二维数组
     */
    public static int[][] toTwoDimensionalArray(int[][] sparseArray) {
        int row = sparseArray[0][0];
        int col = sparseArray[0][1];

        int[][] twoDimensionalArray = new int[row][col];
        for (int i = 1; i < sparseArray.length; i++) {
            row = sparseArray[i][0];
            col = sparseArray[i][1];
            twoDimensionalArray[row][col] = sparseArray[i][2];
        }

        return twoDimensionalArray;
    }

    /**
     * 将二维数组转换为稀疏数组
     */
    public static int[][] toSparseArray(int[][] twoDimensionalArray) {

        // 获取二维数组有效数字个数
        int count = 0;
        for (int[] ints : twoDimensionalArray) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    count++;
                }
            }
        }

        // 创建稀疏数组
        int[][] sparseArray = new int[count + 1][3];
        sparseArray[0][0] = twoDimensionalArray.length;
        sparseArray[0][1] = twoDimensionalArray[0].length;
        sparseArray[0][2] = count;

        // 将二维数组中的有效数字保存咋稀疏数组中
        int row = 0;
        for (int i = 0; i < twoDimensionalArray.length; i++) {
            for (int j = 0; j < twoDimensionalArray[i].length; j++) {
                if (twoDimensionalArray[i][j] != 0) {
                    row++;
                    sparseArray[row][0] = i;
                    sparseArray[row][1] = j;
                    sparseArray[row][2] = twoDimensionalArray[i][j];
                }
            }
        }

        return sparseArray;
    }

    /**
     * 创建二维数组
     */
    public static int[][] createTwoDimensionalArray() {
        int[][] twoDimensionalArray = new int[6][7];
        twoDimensionalArray[0][3] = 22;
        twoDimensionalArray[0][6] = 15;
        twoDimensionalArray[1][1] = 11;
        twoDimensionalArray[1][5] = 17;
        twoDimensionalArray[2][3] = -6;
        twoDimensionalArray[3][5] = 39;
        twoDimensionalArray[4][0] = 91;
        twoDimensionalArray[5][2] = 28;

        return twoDimensionalArray;
    }
}
