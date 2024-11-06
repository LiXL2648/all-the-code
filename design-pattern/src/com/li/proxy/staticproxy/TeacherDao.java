package com.li.proxy.staticproxy;

/**
 * @author LiXL
 * @date 2024/6/26
 */
public class TeacherDao implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println("老师授课中...");
    }
}
