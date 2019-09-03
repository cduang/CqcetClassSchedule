package com.ckong.schedule.service.impl;

import com.ckong.schedule.dao.factory.DaoFactory;
import com.ckong.schedule.entity.ClassSchedule;
import com.ckong.schedule.entity.Course;
import com.ckong.schedule.service.ICourseService;
import com.ckong.schedule.utils.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ICourseService接口的实现
 * @author kongzhiqiang
 */
public class CourseServiceImpl implements ICourseService {

    @Override
    public boolean addCourse(Course course, String userId) throws SQLException{
        Connection conn = DbUtil.getConnection();

        try {

            Integer courseId = DaoFactory.getCourseDaoInstance(conn).insertCourse(course);
            // 如果用户存在且课程号不等于null才可以添加课程
            if (courseId != null && DaoFactory.getUserDaoInstance(conn).isUserExist(userId)) {
                ClassSchedule schedule = new ClassSchedule();
                schedule.setUserId(userId);
                schedule.setCourseId(courseId);
                return DaoFactory.getClassScheduDaoInstance(conn).insertSchedule(schedule);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtil.close(conn);
        }
        return false;
    }

    @Override
    public Map<String, List<Course>> finUserAllCourseById(String userId) throws SQLException {

        Connection conn = DbUtil.getConnection();
        Map<String, List<Course>> courses;

        try {
             courses = DaoFactory.getClassScheduDaoInstance(conn).findCoursesById(userId);
        } finally {
            DbUtil.close(conn);
        }

        return courses;
    }
}
