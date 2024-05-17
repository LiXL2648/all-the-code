package com.li.principle.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXL
 * @date 2024/1/3
 */
public class Demeter2 {

    public static void main(String[] args) {
        SchoolManager1 schoolManager = new SchoolManager1();
        schoolManager.printAllEmployee(new CollegeManager1());
    }
}

// 学校总部员工类
class Employee1 {
    private final int id;

    public Employee1(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

// 学院的职工类
class CollegeEmployee1 {
    private final int id;

    public CollegeEmployee1(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class CollegeManager1 {
    private List<CollegeEmployee1> getAllEmployee() {
        List<CollegeEmployee1> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new CollegeEmployee1(i));
        }
        return list;
    }

    public void printAllEmployee() {
        List<CollegeEmployee1> collegeEmployeeList = getAllEmployee();
        for (CollegeEmployee1 collegeEmployee : collegeEmployeeList) {
            System.out.println(collegeEmployee.getId());
        }
    }
}

class SchoolManager1 {
    public List<Employee1> getAllEmployee() {
        List<Employee1> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Employee1(i));
        }
        return list;
    }

    public void printAllEmployee(CollegeManager1 sub) {
        // 将输出学院员工的方法，封装到 CollegeManager1
        sub.printAllEmployee();
        System.out.println();
        List<Employee1> employeeList = getAllEmployee();
        for (Employee1 employee : employeeList) {
            System.out.println(employee.getId());
        }
    }
}


