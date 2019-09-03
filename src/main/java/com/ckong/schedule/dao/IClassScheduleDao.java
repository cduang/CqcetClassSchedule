package com.ckong.schedule.dao;

import com.ckong.schedule.entity.ClassSchedule;
import com.ckong.schedule.entity.Course;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 课程映射表dao接口
 * @author kongzhiqiang
 */
public interface IClassScheduleDao {

    /**
     * 根据账号得到该用户的全部课程
     * @param userId 用户账号
     * @return 全部课程长度为0时代表没有课程
     * @throws SQLException SQL异常
     */
    Map<String, List<Course>> findCoursesById(String userId) throws SQLException;

    /**
     * 为class_schedule 表添加记录
     * @return 成功true, 否则false
     * @param schedule 课程记录
     * @throws SQLException SQL异常
     */
    boolean insertSchedule(ClassSchedule schedule) throws SQLException;
}
