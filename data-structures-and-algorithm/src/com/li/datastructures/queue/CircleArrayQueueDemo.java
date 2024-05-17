package com.li.datastructures.queue;

import java.util.Random;
import java.util.Scanner;

/**
 * @author LiXL
 * @date 2022/9/28
 * 数组模拟环形队列
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(5);
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
class CircleArrayQueue {
    private final int[] arr; // 模拟队列的数组
    private final int maxSize; // 队列最大长度
    private int rear; // 指向队列的最后一个元素的后一个位置
    private int front; // 指向队列的第一个元素

    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        rear = 0;
        front = 0;
    }

    /**
     * 判断队列是否已经满了
     */
    public boolean isFull() {
        // 预留一个空间
        return (rear + 1) % maxSize == front;
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
            arr[rear++] = num;
            // 当 rear 超过 maxSize之后，该值指向的下一个应该是原点
            rear %= maxSize;
        } else {
            System.out.println("队列已满，无法添加数据");
        }
    }

    /**
     * 从队列中取出一个元素
     */
    public int remove() {
        if (!isEmpty()) {
            int value = arr[front++];
            // 当 front 超过 maxSize 之后，下一个指向应该是原点
            front %= maxSize;
            return value;
        }
        System.out.println("队列已空，无法获数据");
        return Integer.MIN_VALUE;
    }

    /**
     * 获取队列有效数据的个数
     */
    public int size() {
        return (rear - front + maxSize ) % maxSize;
    }

    /**
     * 遍历队列元素
     */
    public void list() {
        if (!isEmpty()) {
            System.out.print("arr[");
            int count = size();
            int index;
            for (int i = 0; i < count; i++) {
                index = (front + i ) % maxSize;
                System.out.print(arr[index] + " ");
            }
            System.out.println("]");
        } else {
            System.out.println("队列已空，无法获数据");
        }
    }

    /**
     * 查看队列头
     */
    public int peek() {
        if (!isEmpty()) {
            return arr[front];
        }
        System.out.println("队列已空，无法获数据");
        return Integer.MIN_VALUE;
    }
}
