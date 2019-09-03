package com.ckong.schedule.dao;

import com.ckong.schedule.entity.Course;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * 孔
 * @author kongzhiqiang
 */
public interface ICourseDao {

    /**
     * 批量添加课程同完成对ClassSchedule表记录的添加
     * @param courses 全部课程
     * @param userId 用户id
     * @throws SQLException SQL异常必须处理
     */
    void insertBatch(Map<Integer, List<Course>> courses, String userId) throws SQLException;

    /**
     * 添加单个课程
     * @param entity 课程实体类
     * @return 成功返回课程编号,反之返回null
     * @throws SQLException SQL异常必须捕获
     */
    Integer insertCourse(Course entity) throws SQLException;

}
