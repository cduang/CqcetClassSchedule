package com.ckong.schedule.dao.impl;

import com.ckong.schedule.dao.IClassScheduleDao;
import com.ckong.schedule.entity.ClassSchedule;
import com.ckong.schedule.entity.Course;
import com.ckong.schedule.utils.DateTimeUtil;
import com.ckong.schedule.utils.DbUtil;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ClassSchedule Dao
 * @author kongzhiqiang
 */
public class ClassScheduleDaoImpl implements IClassScheduleDao {

    private Connection conn;

    private PreparedStatement pstmt;

    public ClassScheduleDaoImpl(Connection conn) {
        this.conn = conn;
    }


    @Override
    public Map<String, List<Course>> findCoursesById(String userId) throws SQLException {

        String sql = " SELECT course.course_id, course.course_name, course.class_room, day_of_week, teacher, " +
                " class_time ,course.week_range, time_stamp FROM course "
                + " WHERE course.course_id IN (SELECT  course_id FROM class_schedule WHERE class_schedule.user_id = ?) ";
        ResultSet result = null;
        Map<String, List<Course>> courses = new LinkedHashMap<>();

        int sunday = 7;
        // key 必须转换成String不然json前端无法JSON.parse()
        for (int i = 1; i <= sunday; i++) {
            courses.put(String.valueOf(i), new LinkedList<>());
        }

        try {
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, userId);
            result = this.pstmt.executeQuery();
            if (result != null) {
                while (result.next()) {

                    Course course = new Course();
                    course.setCourseId(result.getInt("course_id"));
                    course.setCourseName(result.getString("course_name"));
                    course.setClassRoom(result.getString("class_room"));
                    course.setDayOfWeek(result.getString("day_of_week"));
                    course.setTeacher(result.getString("teacher"));
                    course.setClassTime(result.getInt("class_time"));
                    course.setWeekRange(result.getString("week_range"));
                    course.setTimeStamp(
                            DateTimeUtil.timeStampToLocalDateTime(result.getTimestamp("time_stamp")));

                    courses.get((course.getDayOfWeek())).add(course);
                }
            }

            return courses;
        } finally {
            DbUtil.close(this.pstmt);
            DbUtil.close(result);
        }


    }

    @Override
    public boolean insertSchedule(ClassSchedule schedule) throws SQLException {

        String sql = " INSERT INTO class_schedule (user_id, course_id) VALUES (?, ?) ";
        try {
            this.pstmt = conn.prepareStatement(sql);
            this.pstmt.setString(1, schedule.getUserId());
            this.pstmt.setInt(2, schedule.getCourseId());
            return this.pstmt.executeUpdate() > 0;
        } finally {
            DbUtil.close(this.pstmt);
        }
    }
}
