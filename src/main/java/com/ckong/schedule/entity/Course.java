package com.ckong.schedule.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 课程bean
 * @author kongzhiqiang
 */
public class Course {

    private Integer courseId;
    private String courseName;
    private String classRoom;
    private String dayOfWeek;
    private String teacher;
    private Integer classTime;
    private String weekRange;
    private LocalDateTime timeStamp;

    public Course(){}

    /**
     *
     * @param courseName 课程名
     * @param classRoom 上课教室
     * @param dayOfWeek 星期几
     * @param teacher 授课教师
     * @param classTime 上课时间
     */
    public Course(String courseName, String classRoom, String dayOfWeek, String teacher, Integer classTime,
                  String weekRange, LocalDateTime timeStamp) {
        this.courseName = courseName;
        this.classRoom = classRoom;
        this.dayOfWeek = dayOfWeek;
        this.teacher = teacher;
        this.classTime = classTime;
        this.weekRange = weekRange;
        this.timeStamp = timeStamp;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getClassTime() {
        return classTime;
    }

    public void setClassTime(Integer classTime) {
        this.classTime = classTime;
    }

    public String getWeekRange() {
        return weekRange;
    }

    public void setWeekRange(String weekRange) {
        this.weekRange = weekRange;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", teacher='" + teacher + '\'' +
                ", classTime=" + classTime +
                ", weekRange='" + weekRange + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}