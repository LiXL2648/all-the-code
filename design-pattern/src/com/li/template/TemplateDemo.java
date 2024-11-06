package com.li.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TemplateDemo {

    public static void main(String[] args) throws IOException {
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // 选择具体社交网络
            System.out.println("\nChoose social network for posting message.\n" +
                    "1 - WeChat\n" +
                    "2 - Facebook\n" +
                    "3 - 退出");
            int choice = Integer.parseInt(reader.readLine());
            if (choice != 1 && choice != 2) {
                reader.close();
                return;
            }

            // 输入用户名密码
            System.out.print("Input user name: ");
            String username = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();

            // 输入消息
            System.out.print("Input message: ");
            String message = reader.readLine();

            // 创建具体社交网络类对象
            Network network;
            if (choice == 1) {
                network = new WeChat(username, password);
            } else {
                network = new Facebook(username, password);
            }
            network.post(message);
        }
    }
}
