package com.li.algorithm.prim;

import com.li.algorithm.graph.Graph;

/**
 * @author LiXL
 * @date 2023/12/15
 */
public class PrimDemo {

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Graph graph = new Graph(data.length);
        for (char c : data) {
            graph.addVertex(c);
        }

        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 7);
        graph.addEdge(0, 6, 2);
        graph.addEdge(1, 3, 9);
        graph.addEdge(1, 6, 3);
        graph.addEdge(2, 4, 8);
        graph.addEdge(3, 5, 4);
        graph.addEdge(4, 5, 5);
        graph.addEdge(4, 6, 4);
        graph.addEdge(5, 6, 6);

        graph.show();
        prim(graph, 0);
    }

    public static void prim(Graph graph, int v) {
        int[][] edges = graph.getEdges();
        char[] vertexes = graph.getVertexes();
        int numOfVertex = graph.getNumOfVertex();
        // 记录顶点是否已经被访问过
        int[] isVisited = new int[numOfVertex];
        // 将当前顶点设置为已访问
        isVisited[v] = 1;
        // 记录最小边的两个顶点
        int h1 = -1, h2 = -1, minWeight;
        for (int k = 1; k < numOfVertex; k++) {
            // prim 算法结束后，会产生 numOfVertex - 1 条边
            minWeight = Integer.MAX_VALUE;
            for (int i = 0; i < numOfVertex; i++) {
                // 遍历已经访问过的顶点
                for (int j = 0; j < numOfVertex; j++) {
                    // 遍历还没有访问过的顶点
                    if (isVisited[i] == 1 && isVisited[j] == 0 && minWeight > edges[i][j]) {
                        // 如果还没有访问过的边的权值小于 minWeight，则替换 minWeight
                        h1 = i;
                        h2 = j;
                        minWeight = edges[i][j];
                    }
                }
            }
            System.out.println("边<" + vertexes[h1] + ", " + vertexes[h2] + ">=" + minWeight);
            isVisited[h2] = 1;
        }
    }
}
