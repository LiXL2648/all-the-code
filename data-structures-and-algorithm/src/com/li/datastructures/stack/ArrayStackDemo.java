package com.li.datastructures.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author LiXL
 * @date 2022/10/3
 * 模拟栈应用
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.list();
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        arrayStack.list();
    }
}

/**
 * 模拟栈
 */
class ArrayStack {
    private Object[] elements;
    private int top = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public ArrayStack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public ArrayStack(int maxSize) {
        elements = new Object[maxSize];
    }

    /**
     * 入栈
     */
    public void push(Object obj) {
        // 如果栈满了，就进行扩容
        ensureCapacity();
        // 将元素保存在栈顶
        elements[top++] = obj;
    }

    /**
     * 出栈
     */
    public Object pop() {
        // 先判断栈是否为空
        if (top == 0) {
            throw new EmptyStackException();
        }

        // 先取出栈顶元素，在将栈顶置空，最后将元素返回
        Object element = elements[--top];
        // 将弹出的元素，交给垃圾回收器回收
        elements[top] = null;
        return element;
    }

    /**
     * 遍历栈
     */
    public void list() {
        if (top == 0) {
            return;
        }
        System.out.print("ArrayStack[");
        for (int i = top - 1; i >= 0; i--) {
            System.out.print(elements[i] + " ");
        }
        System.out.println("]");
    }

    /**
     * 栈自动扩容
     */
    public void ensureCapacity() {
        // 当栈满时，采取扩容机制，扩大为原来的两倍
        if (top == elements.length) {
            elements = Arrays.copyOf(elements, top * 2 + 1);
        }
    }
}
