package com.li.interpreter;

import java.util.Map;

/**
 * 加法解释器
 */
public class AddExpression extends SymbolExpression {

    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret(Map<String, Integer> var) {
        // 处理相加
        return super.left.interpret(var) + super.right.interpret(var);
    }
}
