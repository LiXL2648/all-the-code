package com.li.interpreter;

import java.util.Map;
import java.util.Stack;

public class Calculator {

    // 定义一个表达式解析器
    private final Expression expression;

    public Calculator(String expStr) {
        // 先进先出
        Stack<Expression> stack = new Stack<>();
        Expression left, right;
        for (int i = 0; i < expStr.length(); i++) {
            switch (expStr.charAt(i)) {
                case '+':
                    // 从stack取出left
                    left = stack.pop();
                    // 构造right
                    right = new VarExpression(String.valueOf(expStr.charAt(++i)));
                    // 构造AddExpression
                    stack.push(new AddExpression(left, right));
                    break;
                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(expStr.charAt(++i)));
                    // 构造SubExpression
                    stack.push(new SubExpression(left, right));
                    break;
                default:
                    stack.push(new VarExpression(String.valueOf(expStr.charAt(i))));
                    break;
            }
        }
        // stack中最后一个元素就是最终表达式
        this.expression = stack.pop();
    }

    public int run(Map<String, Integer> var) {
        return this.expression.interpret(var);
    }
}
