package com.li.datastructures.huffmantree;

/**
 * @author LiXL
 * @data 2023/10/6
 */
public class Node implements Comparable<Node> {

    Byte data;

    int weight;

    Node left;

    Node right;

    public Node(Byte data, int weight) {
        this.weight = weight;
        this.data = data;
    }

    public void preOder() {
        System.out.println(this);
        if (left != null) {
            left.preOder();
        }
        if (right != null) {
            right.preOder();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
