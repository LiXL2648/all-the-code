package com.li.interpreter;

import java.util.Map;

/**
 * 变量解释器
 */
public class VarExpression extends Expression {

    // key：a，b，
    private final String key;

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    public int interpret(Map<String, Integer> var) {
        return var.get(key);
    }
}
