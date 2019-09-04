package com.ckong.schedule.service;

import com.ckong.schedule.entity.Course;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 关于课程的业务层的操作接口
 * @author kongzhiqiang
 */
public interface ICourseService {

    /**
     * 添加单个课程,将会使用到:</br>
     * ICourseDao中的insertCourse方法</br>
     * IUserDao中的isUserExist方法</br>
     * IClassScheduleDao中的insertSchedule方法
     * @param course 课程entity
     * @param userId 用户账号
     * @return 成功返回true,否则false
     * @throws SQLException SQL执行异常
     */
    boolean addCourse(Course course, String userId) throws SQLException;

    /**
     * 此方法用于查找用户的的全部课程
     * @param userId 用户账号
     * @return 课程集合
     * @throws SQLException sql异常
     */
    Map<String, List<Course>> finUserAllCourseById(String userId) throws SQLException;


    /**
     * 批量添加课程
     * @param newCourses 多个课程
     * @return 成功true, 反之false
     */
    boolean insertBatch(Map<Integer, List<Course>> newCourses, String userId);
}
