package com.ckong.schedule.dao.impl;

import com.ckong.schedule.dao.IClassScheduleDao;
import com.ckong.schedule.dao.ICourseDao;
import com.ckong.schedule.entity.ClassSchedule;
import com.ckong.schedule.entity.Course;
import com.ckong.schedule.utils.DbUtil;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * course 表的实现
 * @author kongzhiqiang
 */
public class CourseDaoImpl implements ICourseDao {

    private Connection conn;
    private PreparedStatement pstmt;

    public CourseDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertBatch(Map<Integer, List<Course>> courses, String userId) throws SQLException {

        IClassScheduleDao scheduleDao = new ClassScheduleDaoImpl(this.conn);
        for (Map.Entry<Integer, List<Course>> c : courses.entrySet()) {
            if (c.getValue().size() != 0) {
                for (Course course : c.getValue()) {

                    ClassSchedule schedule = new ClassSchedule();
                    schedule.setUserId(userId);
                    int courseId = this.insertCourse(course);
                    schedule.setCourseId(courseId);

                    scheduleDao.insertSchedule(schedule);
                }
            }
        }

    }

    @Override
    public Integer insertCourse(Course entity) throws SQLException {

        String sql = " INSERT INTO course (course_name, class_room, " +
                " day_of_week, teacher, class_time, week_range) VALUES (?, ? , ?, ? , ?, ?) ";
        ResultSet resultSet = null;
        try {
            this.pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            this.pstmt.setString(1, entity.getCourseName());
            this.pstmt.setString(2, entity.getClassRoom());
            this.pstmt.setString(3, entity.getDayOfWeek());
            this.pstmt.setString(4, entity.getTeacher());
            this.pstmt.setInt(5, entity.getClassTime());
            this.pstmt.setString(6, entity.getWeekRange());

            if (this.pstmt.executeUpdate() > 0 ) {
                resultSet = this.pstmt.getGeneratedKeys();

                if (resultSet.next()) {
                    return resultSet.getInt("course_id");
                } else {
                    return null;
                }
            }

            return null;

        } finally {
            DbUtil.close(resultSet);
            DbUtil.close(this.pstmt);
        }

    }

}
