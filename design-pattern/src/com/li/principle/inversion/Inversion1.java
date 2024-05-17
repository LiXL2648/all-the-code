package com.li.principle.inversion;

/**
 * @author LiXL
 * @date 2023/12/28
 */
public class Inversion1 {

    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
    }
}

class Email {

    public String getInfo() {
        return "邮件信息：Hello World";
    }
}

/**
 * 如果获取的对象是微信、短信等，则需要新增类，同时Person也要增加相应的接收方法
 * 解决思想：引入一个抽象接口 IReceive，表示接收者，这样Person类与接口IReceive发生依赖
 */
class Person {

    public void receive(Email email) {
        System.out.println(email.getInfo());
    }
}
