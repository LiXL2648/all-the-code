package com.li.algorithm.kruskal;

import com.li.algorithm.graph.Graph;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/12/15
 */
public class KruskalDemo {

    private static final int INF = 10000;
    public static void main(String[] args) {
        //                  0    1    2    3    4    5    6
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Graph graph = new Graph(vertexes.length);
        for (char vertex : vertexes) {
            graph.addVertex(vertex);
        }

        graph.addEdge(0, 0, 0);
        graph.addEdge(0, 1, 12);
        graph.addEdge(0, 5, 16);
        graph.addEdge(0, 6, 14);
        graph.addEdge(1, 1, 0);
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 5, 7);
        graph.addEdge(2, 2, 0);
        graph.addEdge(2, 3, 3);
        graph.addEdge(2, 4, 5);
        graph.addEdge(2, 5, 6);
        graph.addEdge(3, 3, 0);
        graph.addEdge(3, 4, 4);
        graph.addEdge(4, 4, 0);
        graph.addEdge(4, 5, 2);
        graph.addEdge(4, 6, 8);
        graph.addEdge(5, 5, 0);
        graph.addEdge(5, 6, 9);
        graph.addEdge(6, 6, 0);

        graph.show();
        Edge[] edges = kruskal(graph);
        System.out.println(Arrays.toString(edges));
    }

    private static Edge[] kruskal(Graph graph) {
        int[][] matrix = graph.getEdges();
        char[] vertexes = graph.getVertexes();
        Integer numOfEdge = graph.getNumOfEdge();
        // 获取图中所有边的集合
        Edge[] edges = getEdges(matrix, vertexes, numOfEdge);
        // 按照边的权值大小进行排序
        sortEdges(edges);
        // 保存”已有最小生成树每个顶点“中每个顶点在最小生成树中的终点
        int[] ends = new int[numOfEdge];
        // 保存最小生成树
        Edge[] ret = new Edge[vertexes.length - 1];
        int index = 0;
        for (Edge edge : edges) {
            // 获取边起点的下标
            int p1 = getVertexIndex(edge.getStart(), vertexes);
            // 获取边终点的下标
            int p2 = getVertexIndex(edge.getEnd(), vertexes);
            // 获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            // 获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            // 是否构成回路
            if (m != n) {
                ends[m] = n;
                ret[index++] = edge;
            }
        }
        return ret;
    }

    /**
     * 获取顶点对应的下标
     */
    private static int getVertexIndex(char vertex, char[] vertexes) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertex == vertexes[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中所有的边
     */
    private static Edge[] getEdges(int[][] matrix, char[] vertexes, int numOfEdge) {
        Edge[] edges = new Edge[numOfEdge];
        int numOfVertex = vertexes.length, index = 0;
        for (int i = 0; i < numOfVertex; i++) {
            for (int j = i + 1; j < numOfVertex; j++) {
                if (INF != matrix[i][j]) {
                    edges[index++] = new Edge(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 对图中的所有边进行冒泡排序
     */
    private static void sortEdges(Edge[] edges) {
        int edgeNum = edges.length;
        Edge temp;
        for (int i = 0; i < edgeNum - 1; i++) {
            for (int j = i + 1; j < edgeNum; j++) {
                if (edges[i].getWeight() > edges[j].getWeight()) {
                    temp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = temp;
                }
            }
        }
    }

    /**
     * 获取下标为 i 的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends 数组为了记录各个顶点对应的终点是哪个，end 是在遍历的过程中，逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return 返回下标为 i 的顶点对应的终点
     */
    private static int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}
