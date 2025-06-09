package com.li.proxy.dynamic;

public class Demo {

    public static void main(String[] args) {

        ITeacherDao target = new TeacherDao();
        ITeacherDao proxyInstance = (ITeacherDao) new TeacherDaoProxy(target).getProxyInstance();
        proxyInstance.teach();
    }
}
