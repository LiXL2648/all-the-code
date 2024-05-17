package com.li.datastructures.queue;

import java.util.Random;
import java.util.Scanner;

/**
 * @author LiXL
 * @date 2022/9/27
 * 数组模拟队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(10);
        Scanner scanner = new Scanner(System.in);
        char c;
        boolean flag = true;
        while (flag) {
            System.out.print("请输入命令：");
            c = scanner.next().charAt(0);
            int value;
            switch (c) {
                case 'a':
                    arrayQueue.add(new Random().nextInt(100));
                    break;
                case 'r':
                    value = arrayQueue.remove();
                    System.out.println(value);
                    break;
                case 'p':
                    value = arrayQueue.peek();
                    System.out.println(value);
                    break;
                case 'l':
                    arrayQueue.list();
                    break;
                case 'e':
                    flag = false;
                    break;
            }
        }
    }
}

class ArrayQueue {
    private final int[] arr; // 模拟队列的数组
    private final int maxSize; // 队列最大长度
    private int rear; // 队列尾的前一个位置
    private int front; // 队列头的前一个位置

    public ArrayQueue(int arrMaxSize) {
        rear = -1;
        front = -1;
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否已经满了
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加一个元素到队列中
     */
    public void add(int num) {
        if (!isFull()) {
            arr[++rear] = num;
        } else {
            System.out.println("队列已满，无法添加数据");
        }
    }

    /**
     * 从队列中取出一个元素
     */
    public int remove() {
        if (!isEmpty()) {
            return arr[++front];
        }
        System.out.println("队列已空，无法获数据");
        return Integer.MIN_VALUE;
    }

    /**
     * 遍历队列元素
     */
    public void list() {
        if (!isEmpty()) {
            System.out.print("arr[");
            for (int i = front; i < rear; i++) {
                System.out.printf("%d ", arr[i + 1]);
            }
            System.out.println("]");
        }
    }

    /**
     * 查看队列头
     */
    public int peek() {
        if (!isEmpty()) {
            return arr[front + 1];
        }
        System.out.println("队列已空，无法获数据");
        return Integer.MIN_VALUE;
    }
}
