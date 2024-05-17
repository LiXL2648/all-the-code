package com.li.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LiXL
 * @date 2023/12/6
 */
public class GraphDemo {

    private final List<String> vertexList;

    private final int[][] edges;

    private int numOfEdges;

    private boolean[] isVisited;

    public static void main(String[] args) {
        // String[] vertexes = {"A", "B", "C", "D", "E"};
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        GraphDemo graphDemo = new GraphDemo(vertexes.length);

        // 循环添加顶点
        for (String vertex : vertexes) {
            graphDemo.insertVertex(vertex);
        }

        // 添加边
        // A-B A-C B-C B-D B-E
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);

        graphDemo.insertEdge(0, 1, 1);
        graphDemo.insertEdge(0, 2, 1);
        graphDemo.insertEdge(1, 3, 1);
        graphDemo.insertEdge(1, 4, 1);
        graphDemo.insertEdge(3, 7, 1);
        graphDemo.insertEdge(4, 7, 1);
        graphDemo.insertEdge(2, 5, 1);
        graphDemo.insertEdge(2, 6, 1);
        graphDemo.insertEdge(5, 6, 1);


        graphDemo.showGraph();

        System.out.println("深度优先遍历");
        graphDemo.dfs();
        System.out.println();
        System.out.println("广度优先遍历");
        graphDemo.bfs();
    }

    public GraphDemo(int n) {
        this.vertexList = new ArrayList<>(n);
        this.edges = new int[n][n];
        this.numOfEdges = 0;
        this.isVisited = new boolean[n];
    }

    /**
     * 对所有顶点进行广度优先遍历
     */
    public void bfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    /**
     * 广度优先遍历算法
     */
    private void bfs(boolean[] isVisited, int i) {
        int u, w;
        LinkedList<Integer> queue = new LinkedList<>();
        // 1.访问初始顶点v
        System.out.print(getVertexByIndex(i) + "->");
        // 并标记顶点v为已访问
        isVisited[i] = true;
        // 2.将顶点v入队列
        queue.addLast(i);
        // 3.当队列非空时
        while (!queue.isEmpty()) {
            // 4.出队列，取得队头顶点u
            u = queue.removeFirst();
            // 5.查找顶点u的第一个邻接结点w
            w = getFirstNeighbor(u);
            // 6.若结点u的邻接结点w不存在，则转到步骤3；否则循环执行以下三个步骤：
            while (w != -1) {
                if (!isVisited[w]) {
                    // 6.1若结点w尚未被访问，则访问结点w并标记为已访问
                    System.out.print(getVertexByIndex(w) + "->");
                    isVisited[w] = true;
                    // 6.2结点w入队列
                    queue.addLast(w);
                }
                // 查找顶点u的邻接顶点w的下一个邻接顶点，转到步骤6
                w = getNextNeighbor(u, w);
            }
        }
    }

    /**
     * 对所有顶点进行深度优先遍历
     */
    public void dfs() {
        this.isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 深度优先遍历算法
     */
    private void dfs(boolean[] isVisited, int i) {
        // 1.访问初始顶点v
        System.out.print(getVertexByIndex(i) + "->");
        // 将顶点v标记为已访问
        isVisited[i] = true;
        // 2.查找顶点v的第一个邻接顶点 w
        int w = getFirstNeighbor(i);
        // 3.若w存在，则继续执行4，如果w不存在，则回到第1步，将从v的下一个结点继续
        while (w != -1) {
            if (!isVisited[w]) {
                // 4.若w未被访问，对w进行深度优先遍历递归
                dfs(isVisited, w);
            }
            // 5.查找顶点v的邻接顶点w的下一个邻接顶点，转到步骤3
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 获取第一个邻接顶点
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < getNumOfVertex(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 根据第一个邻接顶点获取下一个邻接顶点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < getNumOfVertex(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 插入顶点
     */
    public void insertVertex(String vertex) {
        this.vertexList.add(vertex);
    }

    /**
     * ‘添加边
     */
    public void insertEdge(int v1, int v2, int weight) {
        this.edges[v1][v2] = weight;
        this.edges[v2][v1] = weight;
        this.numOfEdges++;
    }

    /**
     * 获取顶点个数
     */
    public int getNumOfVertex() {
        return this.vertexList.size();
    }

    /**
     * 获取边的个数
     */
    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    /**
     * 根据索引获取顶点
     */
    public String getVertexByIndex(int index) {
        return this.vertexList.get(index);
    }

    /**
     * 获取顶点间的权值
     */
    public int getWeight(int v1, int v2) {
        return this.edges[v1][v2];
    }

    /**
     * 打印图
     */
    public void showGraph() {
        for (int[] edge : this.edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
