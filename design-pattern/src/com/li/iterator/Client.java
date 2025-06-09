package com.li.iterator;

import java.util.Arrays;
import java.util.Iterator;

public class Client {

    public static void main(String[] args) {

        ComputerCollege computerCollege = new ComputerCollege();
        computerCollege.addDepartment("信息与计算科学", "主要是学习软件开发");
        computerCollege.addDepartment("网络工程", "掌握计算机网络和网路系统开发");
        computerCollege.addDepartment("数据科学与大数据技术", "学习与掌握大数据技术");

        InfoCollege infoCollege = new InfoCollege();
        infoCollege.addDepartment("电子信息工程", "应用和集成电子信息系统");
        infoCollege.addDepartment("通信工程", "主要是学习通信技术");
        Arrays.asList(computerCollege, infoCollege).forEach(college -> {
            System.out.println(college.getName());
            Iterator<Department> iterator = college.createIterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        });
    }
}
