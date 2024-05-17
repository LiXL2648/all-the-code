package com.li.principle.inversion;

/**
 * @author LiXL
 * @date 2023/12/28
 */
public class Inversion2 {

    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.receive(new Sms());
        phone.receive(new Wx());
    }
}

class Phone {

    public Phone() {
    }

    private IReceive receive;

    public Phone(IReceive receive) {
        this.receive = receive;
    }

    public void setReceive(IReceive receive) {
        this.receive = receive;
    }

    public void receive(IReceive receive) {
        System.out.println(receive.getInfo());
    }
}
class Wx implements IReceive {

    @Override
    public String getInfo() {
        return "微信消息：Hello World";
    }
}
class Sms implements IReceive {

    @Override
    public String getInfo() {
        return "短信消息：Hello World";
    }
}
interface IReceive {

    String getInfo();
}
