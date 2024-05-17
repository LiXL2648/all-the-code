package com.li.datastructures.huffmantree;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LiXL
 * @data 2023/10/6
 */
public class HuffmanCodingDemo {

    public static void main(String[] args) {
        /*String text = "i like like like java do you like a java";
        byte[] encode = encode(text.getBytes());
        System.out.println(Arrays.toString(encode));
        byte[] decode = decode(encode, huffmanCodingTable);
        System.out.println(new String(decode));*/

//        String src = "C:\\Users\\16344\\Desktop\\t_alipay_config.sql";
//        String dst = "C:\\Users\\16344\\Desktop\\t_alipay_config.zip";
//        encodeFile(src, dst);

        String src = "C:\\Users\\16344\\Desktop\\t_alipay_config.zip";
        String dst = "C:\\Users\\16344\\Desktop\\t_alipay_config.txt";
        decodeFile(src, dst);
    }

    private static void decodeFile(String src, String dst) {
        try (FileInputStream fis = new FileInputStream(src);
             ObjectInputStream ois = new ObjectInputStream(fis);
             FileOutputStream fos = new FileOutputStream(dst)) {
            byte[] bytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodingTable = (Map<Byte, String>) ois.readObject();
            byte[] decode = decode(bytes, huffmanCodingTable);
            fos.write(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件压缩
     *
     * @param src
     * @param dst
     */
    private static void encodeFile(String src, String dst) {
        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dst);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            byte[] encode = encode(bytes);
            oos.writeObject(encode);
            oos.writeObject(huffmanCodingTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解码
     */
    private static byte[] decode(byte[] bytes, Map<Byte, String> huffmanCodingTable) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            boolean flag = i == bytes.length - 1;
            sb.append(byteToBit(!flag, bytes[i]));
        }

        Map<String, Byte> map = new HashMap<>();
        huffmanCodingTable.forEach((k, v) -> map.put(v, k));
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < sb.length(); ) {
            int count = 1;
            while (true) {
                String substring;
                if (i + count < sb.length()) {
                    substring = sb.substring(i, i + count);
                } else {
                    substring = sb.substring(i);
                }
                Byte aByte = map.get(substring);
                if (aByte != null) {
                    list.add(aByte);
                    break;
                } else {
                    count++;
                }
            }
            i += count;
        }

        byte[] decode = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            decode[i] = list.get(i);
        }
        return decode;
    }


    /**
     * 将字节转换为二进制
     */
    private static String byteToBit(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String binary = Integer.toBinaryString(temp);
        if (flag) {
            return binary.substring(binary.length() - 8);
        }
        return binary;
    }

    private static byte[] encode(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanCodingTree = createHuffmanCodingTree(nodes);
        Map<Byte, String> huffmanCodingTable = createHuffmanCodingTable(huffmanCodingTree);
        return encode(bytes, huffmanCodingTable);
    }

    /**
     * '
     * 编码
     */
    private static byte[] encode(byte[] bytes, Map<Byte, String> huffmanCodingTable) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(huffmanCodingTable.get(aByte));
        }

        int len = (sb.length() + 7) / 8;
        int index = 0;
        byte[] encode = new byte[len];
        for (int i = 0; i < sb.length(); i += 8) {
            String substring;
            if (i + 8 > sb.length()) {
                substring = sb.substring(i);
            } else {
                substring = sb.substring(i, i + 8);
            }
            encode[index++] = (byte) Integer.parseInt(substring, 2);
        }

        return encode;
    }

    private static final Map<Byte, String> huffmanCodingTable = new HashMap<>();

    private static final StringBuilder stringBuilder = new StringBuilder();

    public static Map<Byte, String> createHuffmanCodingTable(Node node) {
        if (node == null) {
            return null;
        } else {
            createHuffmanCodingTable(node.left, "0", stringBuilder);
            createHuffmanCodingTable(node.right, "1", stringBuilder);
        }

        return huffmanCodingTable;
    }

    /**
     * 创建哈夫曼编码表
     */
    public static void createHuffmanCodingTable(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder sb = new StringBuilder(stringBuilder);
        sb.append(code);
        if (node != null) {
            if (node.data == null) {
                // 如果是非叶子结点
                createHuffmanCodingTable(node.left, "0", sb);
                createHuffmanCodingTable(node.right, "1", sb);
            } else {
                // 如果是叶子结点
                huffmanCodingTable.put(node.data, sb.toString());
            }
        }
    }

    /**
     * 生成哈夫曼树
     */
    private static Node createHuffmanCodingTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 获取叶子结点
     */
    private static List<Node> getNodes(byte[] bytes) {
        Map<Byte, Integer> countMap = new HashMap<>();
        for (byte aByte : bytes) {
            Integer count = countMap.getOrDefault(aByte, 0);
            countMap.put(aByte, count + 1);
        }

        return countMap.entrySet().stream().map(entry -> new Node(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }
}
