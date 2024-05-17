package com.li.datastructures.stack;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author LiXL
 * @date 2022/10/5
 * 逆波兰计算器
 */
public class PolishNotationDemo {

    public static void main(String[] args) {
        PolishNotation polishNotation = new PolishNotation();
        Scanner scanner = new Scanner(System.in);
        // 修改默认分隔符
        scanner.useDelimiter("\n");
        // "1+((2+3)*4)-5"
        // "(3+4)*5-6"
        String expression;
        while (true) {
            expression = scanner.next();
            String suffixExpression = polishNotation.toSuffixExpression(expression);
            System.out.println(suffixExpression);
            BigDecimal decimal = polishNotation.calculate(suffixExpression);
            // 不使用科学计算法输出
            System.out.println(decimal.stripTrailingZeros().toPlainString());
         }
    }
}

/**
 * 波兰运算规则
 */
class PolishNotation {

    /**
     * 匹配 + - * / ( ) 运算符
     */
    static final String SYMBOL = "[()+\\-*/]";
    static final String LEFT = "(";
    static final String RIGHT = ")";
    static final String ADD = "+";
    static final String SUBTRACT = "-";
    static final String MULTIPLY = "*";
    static final String DIVIDE = "/";
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
     * 将中缀表达式转化为后缀表达式
     */
    public String toSuffixExpression(String expression) {
        // 对表达式进行简单地校验
        if (expression == null || expression.isEmpty()) {
            throw new RuntimeException();
        }
        // 去除所有空白字符
        expression = replaceAllBlank(expression);
        // 将中缀表达式转成对应的 List
        List<String> expressionList = toInfixExpressionList(expression);

        // 定义运算符栈
        Stack<String> s1 = new Stack<>();
        // 定义储存中间结果集合，由于储存中间结果不需要pop操作，并且栈逆序比较麻烦，因此使用集合代替栈
        List<String> s2 = new ArrayList<>();

        // 从左至右扫描中缀表达式
        expressionList.forEach(str -> {
            if (isNumber(str)) {
                // 遇到操作数时，将其加入 s2
                s2.add(str);
            } else if (s1.isEmpty() || s1.peek().equals(LEFT) || str.equals(LEFT)) {
                // 如果 s1 为空，或栈顶运算符为左括号“(”，或者当前运算符是左括号“(”，则直接压入 s1
                s1.push(str);
            } else if (str.equals(RIGHT)) {
                // 如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并加入 s2，直到遇到左括号为止
                while (!s1.peek().equals(LEFT)) {
                    s2.add(s1.pop());
                }
                // 丢弃这一对括号
                s1.pop();
            } else if ( priority(str) > priority(s1.peek())) {
                    // 若当前运算符优先级比栈顶运算符的高，则直接加入 s1
                    s1.push(str);
            } else {
                // 当前运算符优先级不比栈顶运算符的高，将 s1 栈顶的运算符弹出并加入到 s2 中，再次比较当前运算符优先级与栈顶运算符的优先级
                while (!s1.isEmpty() && priority(str) <= priority(s1.peek())) {
                    s2.add(s1.pop());
                }
                // 直到运算符栈为空，或者当前运算符优先级比栈顶运算符的高，将当前运算符压入 s1
                s1.push(str);
            }
        });
        // s1 中剩余的运算符依次弹出并加入 s2
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }

        // 将集合拼接成字符串，并使用空格隔开
        return String.join(" ", s2);
    }

    /**
     * 将中缀表达式转成对应的 List
     */
    public List<String> toInfixExpressionList(String expression) {
        List<String> list = new ArrayList<>();
        int index = 0;
        String subStr;
        // 如果index等于字符串的长度，则意味着扫描到最后一个数值
        while (index != expression.length()) {
            // 如果扫描的字符串是操作符，则将操作符和前面的数值都加入集合中
            if (isSymbol(subStr = expression.charAt(index) + "")) {
                if (index != 0) {
                    // 如果连续出现操作符，则不需要将前面的数值加入集合
                    list.add(expression.substring(0, index));
                }
                list.add(subStr);
                // 去除已经加入集合中的部分，并让index重新置为0
                expression = expression.substring(index + 1);
                index = 0;
            } else {
                // 如果没有扫描到操作符，则让index自增
                index++;
            }
        }
        list.add(expression);
        return list;
    }

    /**
     * 后缀表达式计算
     */
    public BigDecimal calculate(String suffixExpression) {
        Stack<BigDecimal> stack = new Stack<>();
        // 将表达式进行切割，并转换成 List 进行遍历
        Arrays.asList(suffixExpression.split(" ")).forEach(str -> {
            if (isNumber(str)) {
                // 如果是数字，则直接放到数栈中
                BigDecimal decimal = new BigDecimal(str);
                stack.push(decimal);
            } else {
                // 如果是操作符，则弹出栈顶和次栈顶，进行计算
                BigDecimal decimal1 = stack.pop();
                BigDecimal decimal2 = stack.pop();
                // 计算得出结果后压入栈
                BigDecimal decimal = calculate(decimal2, decimal1, str);
                stack.push(decimal);
            }
        });

        return stack.pop();
    }

    /**
     * 返回操作符的优先级，优先级使用数字表示，数字越大，则优先级越高
     */
    public int priority(String symbol) {
        if ("+".equals(symbol) || "-".equals(symbol)) {
            return PRIORITY_1;
        } else if ("*".equals(symbol) || "/".equals(symbol)) {
            return PRIORITY_2;
        } else {
            return PRIORITY_3;
        }
    }

    /**
     * 计算
     */
    public BigDecimal calculate(BigDecimal decimal1, BigDecimal decimal2, String operator) {
        BigDecimal result = null;
        switch (operator) {
            case ADD:
                result = decimal1.add(decimal2);
                break;
            case SUBTRACT:
                result = decimal1.subtract(decimal2);
                break;
            case MULTIPLY:
                result = decimal1.multiply(decimal2);
                break;
            case DIVIDE:
                result = decimal1.divide(decimal2);
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 去除所有空白字符
     */
    public String replaceAllBlank(String expression) {
        // \\s+，匹配任何空白字符，包括空格、制表符、换页符等等，等价于[\f\n\r\t\v]
        return expression.replaceAll("\\s+", "");
    }

    /**
     * 判断是否是运算符
     */
    public boolean isSymbol(String symbol) {
        Pattern pattern = Pattern.compile(SYMBOL);
        return pattern.matcher(symbol).matches();
    }

    /**
     * 判断是否为数字 int double long float
     */
    public boolean isNumber(String number) {
        Pattern pattern = Pattern.compile("^[-+]?(\\d+\\.)?\\d+$");
        return pattern.matcher(number).matches();
    }
}

