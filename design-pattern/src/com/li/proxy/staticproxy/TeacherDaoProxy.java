package com.li.proxy.staticproxy;

/**
 * @author LiXL
 * @date 2024/6/26
 */
public class TeacherDaoProxy implements ITeacherDao {

    private final ITeacherDao teacherDao;

    public TeacherDaoProxy(ITeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void teach() {
        System.out.println("授课前准备...");
        teacherDao.teach();
        System.out.println("结束授课...");
    }
}
