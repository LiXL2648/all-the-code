package com.li.proxy.dynamic;

public class TeacherDao implements ITeacherDao {

    @Override
    public void teach() {
        System.out.println("老师授课中");
    }
}
