//////////////给你一个大小为 m x n 的二进制矩阵 grid 。 
//////////////
////////////// 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设
// 
//////grid 
////////的四
//////////个边
////////////缘都
//////////////被 0（代表水）包围着。 
//////////////
////////////// 岛屿的面积是岛上值为 1 的单元格的数目。 
//////////////
////////////// 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。 
//////////////
////////////// 
//////////////
////////////// 示例 1： 
//////////////
////////////// 
//////////////输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0]
//,[
////0,
//////1,
////////1,
//////////0,
////////////1,
//////////////0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,
//1,
////0,
//////0]
////////,[
//////////0,
////////////0,0,
//////////////0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,
//1,
////0,
//////0,
////////0,
//////////0]
////////////]
//////////////输出：6
//////////////解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
////////////// 
//////////////
////////////// 示例 2： 
//////////////
////////////// 
//////////////输入：grid = [[0,0,0,0,0,0,0,0]]
//////////////输出：0
////////////// 
//////////////
////////////// 
//////////////
////////////// 提示： 
//////////////
////////////// 
////////////// m == grid.length 
////////////// n == grid[i].length 
////////////// 1 <= m, n <= 50 
////////////// grid[i][j] 为 0 或 1 
////////////// 
////////////// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 931 👎 0
////////////
//////////
////////
//////
////
//


package com.li.algorithm.leetcode.editor.cn;

import java.util.LinkedList;

public class MaxAreaOfIsland {

    public static void main(String[] args) {
        Solution solution = new MaxAreaOfIsland().new Solution();
        int[][] grid = {{0, 1},{1, 1}};
        System.out.println(solution.maxAreaOfIsland(grid));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length, col = grid[0].length, maxArea = 0, area = 0, x, y;
        // 记录每个为1的坐标是否已经访问过，如果访问过则不参与面积计算
        int[][] isVisited = new int[row][col];
        // 表示每个坐标都有上下左右四个方向
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1}, position;
        LinkedList<int[]> queue = new LinkedList<>();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                //  遍历每个坐标，如果已经访问过，则忽略
                if(grid[i][j] == 1 && isVisited[i][j] == 0) {
                    // 将访问过的坐标加入优先级队列
                    queue.addLast(new int[]{i, j});
                    while(!queue.isEmpty()) {
                        position = queue.removeFirst();
                        if(isVisited[position[0]][position[1]] == 0) {
                            // 标记该坐标已经访问过
                            isVisited[position[0]][position[1]] = 1;
                            area++;
                            // 分别将四个方向满足要求的坐标加入队列，体现了广度优先遍历
                            for(int k = 0; k < 4; k++) {
                                x = position[0] + dx[k];
                                y = position[1] + dy[k];
                                if(x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 1) {
                                    queue.addLast(new int[]{x, y});
                                }
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, area);
                    area = 0;
                }
            }
        }
        return maxArea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}