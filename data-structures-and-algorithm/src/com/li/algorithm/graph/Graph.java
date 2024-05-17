package com.li.algorithm.graph;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/12/15
 */
public class Graph {

    private final char[] vertexes;

    private final int[][] edges;

    private Integer numOfVertex;

    private Integer numOfEdge;

    public Graph(int n) {
        this.vertexes = new char[n];
        this.edges = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                edges[i][j] = 10000;
            }
        }
        numOfVertex = 0;
        numOfEdge = 0;
    }

    public void show() {
        for (int i = 0; i < this.numOfVertex; i++) {
            System.out.println(Arrays.toString(this.edges[i]));
        }
    }

    public void addVertex(char c) {
        this.vertexes[this.numOfVertex++] = c;
    }

    public void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        if (v1 != v2) {
            this.numOfEdge++;
        }
    }

    public char[] getVertexes() {
        return this.vertexes;
    }

    public int[][] getEdges() {
        return this.edges;
    }

    public Integer getNumOfVertex() {
        return this.numOfVertex;
    }

    public Integer getNumOfEdge() {
        return this.numOfEdge;
    }
}
