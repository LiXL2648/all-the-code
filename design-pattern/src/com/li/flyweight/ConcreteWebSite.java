package com.li.flyweight;

/**
 * @author LiXL
 * @date 2024/3/12
 */
public class ConcreteWebSite extends WebSite {

    private final String type;

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("网站的发布形式为：" + type + ", 使用者：" + user.getName());
    }
}
