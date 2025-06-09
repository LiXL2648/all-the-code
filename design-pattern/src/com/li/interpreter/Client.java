package com.li.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void main(String[] args) throws IOException {

        String expStr = getExpStr();

        Map<String, Integer> var = getExpStrValue(expStr);
        System.out.println(new Calculator(expStr).run(var));
    }

    private static Map<String, Integer> getExpStrValue(String expStr) throws IOException {
        Map<String, Integer> var = new HashMap<>();
        for (char ch : expStr.toCharArray()) {
            if (ch != '+' && ch != '-') {
                String key = String.valueOf(ch);
                if (!var.containsKey(key)) {
                    System.out.print("请输入" + key + "的值：");
                    String value = new BufferedReader(new InputStreamReader(System.in)).readLine();
                    var.put(key, Integer.valueOf(value));
                }
            }
        }
        return var;
    }

    private static String getExpStr() throws IOException {

        System.out.print("请输入表达式：");
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}
