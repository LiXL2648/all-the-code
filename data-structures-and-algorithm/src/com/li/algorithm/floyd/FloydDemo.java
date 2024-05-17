package com.li.algorithm.floyd;

import com.li.algorithm.graph.Graph;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/12/20
 */
public class FloydDemo {

    // 记录各出发顶点到各顶点的距离
    private int[][] dis;

    // 记录各顶点的前驱顶点
    private int[][] pre;

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Graph graph = new Graph(vertexes.length);
        for (char vertex : vertexes) {
            graph.addVertex(vertex);
        }

        graph.addEdge(0, 0, 0);
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 7);
        graph.addEdge(0, 6, 2);
        graph.addEdge(1, 1, 0);
        graph.addEdge(1, 3, 9);
        graph.addEdge(1, 6, 3);
        graph.addEdge(2, 2, 0);
        graph.addEdge(2, 4, 8);
        graph.addEdge(3, 3, 0);
        graph.addEdge(3, 5, 4);
        graph.addEdge(4, 4, 0);
        graph.addEdge(4, 5, 5);
        graph.addEdge(4, 6, 4);
        graph.addEdge(5, 5, 0);
        graph.addEdge(5, 6, 6);
        graph.addEdge(6, 6, 0);

        FloydDemo floydDemo = new FloydDemo(graph.getEdges());
        floydDemo.floyd();
        floydDemo.show(graph.getVertexes());
    }

    public FloydDemo(int[][] matrix) {
        int length = matrix.length;
        this.dis = new int[length][length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(matrix[i], 0, dis[i], 0, matrix[i].length);
            this.pre = new int[length][length];
            Arrays.fill(pre[i], i);
        }
    }

    public void floyd() {
        int length = dis.length, len;
        // 遍历中间顶点
        for (int k = 0; k < length; k++) {
            // 遍历出发顶点
            for (int i = 0; i < length; i++) {
                // 遍历达到顶点
                for (int j = 0; j < length; j++) {
                    // 求从i顶点出发，经过k中间顶点，到达j顶点的距离
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }

    public void show(char[] vertexes) {
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertexes[pre[i][j]] + " ");
            }
            System.out.println();

            for (int j = 0; j < dis.length; j++) {
                System.out.print("(" + vertexes[i] + "到" + vertexes[j] + "的距离为" + dis[i][j] + ")");
            }
            System.out.println();
        }
    }
}
