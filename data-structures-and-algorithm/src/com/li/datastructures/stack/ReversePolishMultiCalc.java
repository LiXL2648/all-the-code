package com.li.datastructures.stack;

import java.util.regex.Pattern;

/**
 * @author LiXL
 * @date 2022/10/6
 */
public class ReversePolishMultiCalc {

    public static void main(String[] args) {
        ReversePolishMultiCalc reversePolishMultiCalc = new ReversePolishMultiCalc();
        String expression = "1 + (2+3)*4 -5";
        reversePolishMultiCalc.replaceAllBlank(expression);
        System.out.println(reversePolishMultiCalc.replaceAllBlank(expression));
        System.out.println(reversePolishMultiCalc.isSymbol("*"));
        System.out.println(reversePolishMultiCalc.isNumber("11.1"));
    }

    /**
     * 匹配 + - * / ( ) 运算符
     */
    static final String SYMBOL = "[()+\\-*/]";
    static final String LEFT = "(";
    static final String RIGHT = ")";
    static final String ADD = "+";
    static final String MINUS = "-";
    static final String TIMES = "*";
    static final String DIVISION = "/";

    /**
     * 加减法的优先级
     */
    static final int PRIORITY_1 = 1;
    /**
     * 乘除法的优先级
     */
    static final int PRIORITY_2 = 1;
    /**
     * 括号法的优先级
     */
    static final int PRIORITY_3 = Integer.MAX_VALUE;

    /**
     * 去除所有空白字符
     */
    public String replaceAllBlank(String expression) {
        // \\s+，匹配任何空白字符，包括空格、制表符、换页符等等，等价于[\f\n\r\t\v]
        return expression.replaceAll("\\s+", "");
    }

    /**
     * 判断是否为数字 int double long float
     */
    public boolean isNumber(String number) {
        Pattern pattern = Pattern.compile("^[-+]?(\\d+\\.)?\\d+$");
        return pattern.matcher(number).matches();
    }

    /**
     * 判断是否是运算符
     */
    public boolean isSymbol(String symbol) {
        Pattern pattern = Pattern.compile(SYMBOL);
        return pattern.matcher(symbol).matches();
    }

    /**
     * 获取运算符的优先级
     */
    public int calPriority(String symbol) {
        if ("+".equals(symbol) || "-".equals(symbol)) {
            return PRIORITY_1;
        } else if ("*".equals(symbol) || "/".equals(symbol)) {
            return PRIORITY_2;
        } else {
            return PRIORITY_3;
        }
    }
}
