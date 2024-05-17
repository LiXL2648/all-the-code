package com.li.composite;

/**
 * @author LiXL
 * @date 2024/3/3
 */
public class Demo {

    public static void main(String[] args) {

        Organization department1 = new Department("信息与计算科学");
        Organization department2 = new Department("统计学");
        Organization department3 = new Department("网络工程");
        Organization department4 = new Department("计算机科学与技术");

        Organization college1 = new College("计算机科学学院");
        Organization college2 = new College("信息工程学院");
        college1.add(department1);
        college1.add(department2);

        college2.add(department3);
        college2.add(department4);

        Organization university = new University("仲恺");
        university.add(college1);
        university.add(college2);
        university.print();
    }
}
