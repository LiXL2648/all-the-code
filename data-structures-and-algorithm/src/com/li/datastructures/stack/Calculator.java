package com.li.datastructures.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * @author LiXL
 * @date 2022/10/3
 * 栈实现综合计算器
 */
public class Calculator {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String expression = scanner.next();
            int result = calculator.calculate(expression);
            System.out.printf("%s = %d\n", expression, result);
        }
    }

    /**
     * 栈实现综合计算器
     */
    public int calculate(String expression) {
        // 简单校验表达式的合理性
        if (expression == null || expression.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        // 操作数栈
        ArrayStack2<Integer> numStack = new ArrayStack2<>();
        // 操作符栈
        ArrayStack2<Character> operatorStack = new ArrayStack2<>();
        // 创建索引
        int index = 0;
        // 预先定义好需要使用的变量
        int num, num1, num2, result;
        char operator;
        while (expression.length() > 0) {
            if (index == expression.length()) {
                // 当表达式只剩下最后一个数值时
                num = Integer.parseInt(expression);
                numStack.push(num);
                expression = "";
            } else if (isOperator(expression.charAt(index))) {
                // 当扫描到操作符时
                // 获取操作符前面的数值，放入到操作数栈中
                num = Integer.parseInt(expression.substring(0, index));
                numStack.push(num);
                // 获取当前操作符，判断当前操作符栈是否为空，并且判断当前操作符与操作符栈顶的操作符的优先级大小
                operator = expression.charAt(index);
                if (!operatorStack.isEmpty() && priority(operator) <= priority(operatorStack.peek())) {
                    // 如果当前操作符栈不为空，并且当前操作符的优先级小于等于操作符栈顶的操作符
                    // 弹出操作数栈顶两个元素与操作符栈顶一个元素，进行运算，将结果放入到操作数栈中
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    result = calculate(num1, num2, operatorStack.pop());
                    numStack.push(result);
                }
                // 将当前操作符压入操作符栈中
                operatorStack.push(operator);
                // 更新将表达式，去除已经完成入栈的表达式
                expression = expression.substring(index + 1);
                index = 0;
            } else {
                index++;
            }
        }

        // 将操作数栈和操作符中的元素按照顺序进行运算，并将最终结果返回
        while (!operatorStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = operatorStack.pop();
            result = calculate(num1, num2, operator);
            numStack.push(result);
        }
        return numStack.pop();
    }

    /**
     * 返回操作符的优先级，优先级使用数字表示，数字越大，则优先级越高
     */
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断一个字符是否是操作符
     */
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算
     */
    public int calculate(int num1, int num2, int operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}

/**
 * 模拟栈
 */
class ArrayStack2<T> {
    private Object[] elements;
    private int top = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public ArrayStack2() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public ArrayStack2(int maxSize) {
        elements = new Object[maxSize];
    }

    /**
     * 入栈
     */
    public void push(T obj) {
        // 如果栈满了，就进行扩容
        ensureCapacity();
        // 将元素保存在栈顶
        elements[top++] = obj;
    }

    /**
     * 出栈
     */
    public T pop() {
        // 先判断栈是否为空
        if (top == 0) {
            throw new EmptyStackException();
        }

        // 先取出栈顶元素，在将栈顶置空，最后将元素返回
        T element = (T) elements[--top];
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

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * 查看栈顶元素
     */
    public T peek() {
        return (T) elements[top - 1];
    }
}
