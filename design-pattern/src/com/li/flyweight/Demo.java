package com.li.flyweight;

/**
 * @author LiXL
 * @date 2024/3/12
 */
public class Demo {

    public static void main(String[] args) {

        WebSiteFactory webSiteFactory = new WebSiteFactory();

        WebSite newWebSite = webSiteFactory.getWebSiteCategory("新闻");
        newWebSite.use(new User("a"));

        WebSite blogWebSite = webSiteFactory.getWebSiteCategory("博客");
        blogWebSite.use(new User("b"));

        WebSite blogWebSite1 = webSiteFactory.getWebSiteCategory("博客");
        blogWebSite1.use(new User("c"));

        System.out.println("网站类型个数：" + webSiteFactory.getWebSiteSize());
    }
}
