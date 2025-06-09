package com.li.interpreter;

import java.util.Map;

/**
 * 减法解释器
 */
public class SubExpression extends SymbolExpression {

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret(Map<String, Integer> var) {
        // 处理相减
        return super.left.interpret(var) - super.right.interpret(var);
    }
}
