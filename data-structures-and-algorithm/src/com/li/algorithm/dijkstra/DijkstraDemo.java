package com.li.algorithm.dijkstra;

import com.li.algorithm.graph.Graph;

/**
 * @author LiXL
 * @date 2023/12/19
 */
public class DijkstraDemo {

    public static void main(String[] args) {
        // 0    1    2    3    4    5    6
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Graph graph = new Graph(vertexes.length);
        for (char vertex : vertexes) {
            graph.addVertex(vertex);
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
        dijkstra(graph, 6);
    }

    public static void dijkstra(Graph graph, int index) {
        int numOfVertex = graph.getNumOfVertex();
        VisitedVertex visitedVertex = new VisitedVertex(numOfVertex, index);
        update(visitedVertex, graph, index);
        for (int i = 1; i < numOfVertex; i++) {
            index = visitedVertex.updateAlreadyArr();
            update(visitedVertex, graph, index);
        }
        visitedVertex.show();
    }

    /**
     * 更新index顶点到周围顶点的距离和周围顶点的前驱顶点
     */
    public static void update(VisitedVertex visitedVertex, Graph graph, int index) {
        int[][] matrix = graph.getEdges();
        int len;
        for (int i = 0; i < graph.getNumOfVertex(); i++) {
            // 出发顶点到index顶点的距离+从index顶点到i顶点的距离之和
            len = visitedVertex.getDis(index) + matrix[index][i];
            // 如果 i 顶点没有被访问过，并且 len 小于出发顶点到 i 顶点的距离
            if (!visitedVertex.isVisited(i) && visitedVertex.getDis(i) > len) {
                // 更新出发顶点到 i 顶点的距离
                visitedVertex.updateDis(i, len);
                // 更新 i 顶点的前驱顶点为index
                visitedVertex.updatePreVisited(i, index);
            }
        }
    }
}
