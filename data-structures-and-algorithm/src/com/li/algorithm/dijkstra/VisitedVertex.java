package com.li.algorithm.dijkstra;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/12/19
 */
public class VisitedVertex {

    // 记录各个顶点是否已经访问过
    private final int[] alreadyArr;

    // 每个下标对应的值为前一个顶点的下标
    private final int[] preVisited;

    // 记录出发顶点到各个顶点的最短距离
    private final int[] dis;

    private static final int INF = 10000;

    public VisitedVertex(int numOfVertex, int index) {
        this.alreadyArr = new int[numOfVertex];
        this.preVisited = new int[numOfVertex];
        this.dis = new int[numOfVertex];
        Arrays.fill(dis, INF);
        dis[index] = 0;
        alreadyArr[index] = 1;
    }

    /**
     * 判断顶点是否已经访问过
     */
    public boolean isVisited(int index) {
        return this.alreadyArr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     */
    public void updateDis(int index, int dis) {
        this.dis[index] = dis;
    }

    /**
     * 更新pre顶点的前驱顶点为index顶点
     */
    public void updatePreVisited(int pre, int index) {
        this.preVisited[pre] = index;
    }

    /**
     * 继续选择并返回新的访问顶点
     */
    public int updateAlreadyArr() {
        int min = INF, index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            // 找到距离出发顶点最小距离的顶点
            if (alreadyArr[i] == 0 && getDis(i) < min) {
                min = getDis(i);
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }

    /**
     * 获取出发顶点到index顶点的距离
     */
    public int getDis(int index) {
        return this.dis[index];
    }

    public void show() {
        System.out.println(Arrays.toString(alreadyArr));
        System.out.println(Arrays.toString(preVisited));
        System.out.println(Arrays.toString(dis));
    }
}
