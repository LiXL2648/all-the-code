package com.li.principle.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXL
 * @date 2024/1/3
 */
public class Demeter1 {

    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.printAllEmployee(new CollegeManager());
    }
}

// 学校总部员工类
class Employee {
    private final int id;

    public Employee(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

// 学院的职工类
class CollegeEmployee {
    private final int id;

    public CollegeEmployee(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class CollegeManager {
    public List<CollegeEmployee> getAllEmployee() {
        List<CollegeEmployee> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new CollegeEmployee(i));
        }
        return list;
    }
}

class SchoolManager {
    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Employee(i));
        }
        return list;
    }

    public void printAllEmployee(CollegeManager sub) {
        // CollegeEmployee类并不是SchoolManager类的直接朋友
        List<CollegeEmployee> collegeEmployeeList = sub.getAllEmployee();
        for (CollegeEmployee collegeEmployee : collegeEmployeeList) {
            System.out.println(collegeEmployee.getId());
        }

        System.out.println();
        List<Employee> employeeList = getAllEmployee();
        for (Employee employee : employeeList) {
            System.out.println(employee.getId());
        }
    }
}


