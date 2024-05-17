package com.li.datastructures.huffmantree;

import com.li.datastructures.tree.Node;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiXL
 * @data 2023/10/5
 */
public class HuffmanTreeDemo {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        root.prevOrder();
    }

    /**
     * 创建哈夫曼树
     */
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = Arrays.stream(arr).mapToObj(Node::new).collect(Collectors.toList());
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.getValue() + right.getValue());
            parent.setLeft(left);
            parent.setRight(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
