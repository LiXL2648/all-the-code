package com.li.datastructures.link;

/**
 * @author LiXL
 * @date 2022/10/3
 * 约瑟夫问题
 */
public class JosephuDemo {

    public static void main(String[] args) {
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.addBoy(5);
        linkedList.list();

        linkedList.count(1, 2);
    }
}

/**
 * 创建单项环形链表
 */
class CircleSingleLinkedList {
    // 创建一个 first 节点，当前没有编号
    Boy first = null;

    /**
     * 添加节点，构成一个环形链表
     */
    public void addBoy(int num) {
        if (num < 1) {
            return;
        }

        Boy node, curr = null;
        for (int i = 1; i <= num; i++) {
            node = new Boy(i);
            if (i == 1) {
                // 如果是单项环形链表的第一个节点
                // 让节点头指向第一个节点
                first = node;
                // 让第一个节点指向自己，形成一个环状
                node.next = node;
                // 将指针移向当前节点
                curr = node;
            } else {
                // 将最后一个节点指向第一个，形成环状
                node.next = first;
                // 将上一个节点指向当前新节点
                curr.next = node;
                // 将指针移向当前新节点
                curr = node;
            }
        }
    }

    /**
     * 遍历
     */
    public void list() {
        // 判断链表是否为空
        if (first == null) {
            return;
        }

        // 由于 first 不能移动，所以需要一个辅助指针
        Boy curr = first;
        do {
            System.out.println(curr.no);
            // 指针后移，直至下一个指针为第一节点为止
            curr = curr.next;
        } while (curr != first);
    }

    /**
     * 单向环形链表出圈
     * @param start 移动到第 start 个节点
     * @param count 第 count 个节点出列
     */
    public void count(int start, int count) {
        if (first == null || start < 1 || count < 1 || start > size()) {
            return;
        }

        // 将辅助指针指向最后一个节点
        Boy helper = first;
        while (helper.next != first) {
            helper = helper.next;
        }

        // 让 helper 和 first 移动到 start 的为止
        for (int i = 0; i < start - 1; i++) {
            helper = first;
            first = first.next;
        }

        // 让 helper 和 first 同时移动 count-1 次，然后出圈
        while (helper != first) {

            for (int i = 0; i < count - 1; i++) {
                helper = first;
                first = first.next;
            }
            // 这时 first 指向，就是需要出圈的节点
            System.out.println(first.no);
            first = first.next;
            helper.next = first;
        }
        System.out.println(first.no);
    }

    public int size() {
        if (first == null) {
            return 0;
        }
        Boy curr = first;
        int size = 1;
        while (curr.next != first) {
            size++;
            curr = curr.next;
        }
        return size;
    }
}

class Boy {
    int no;
    Boy next;

    public Boy(int no) {
        this.no = no;
    }
}