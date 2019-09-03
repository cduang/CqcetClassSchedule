package com.ckong.schedule.entity;

/**
 * 课程表bean
 * @author kongzhiqiang
 */
public class ClassSchedule {


    private String userId;
    private Integer courseId;

    public ClassSchedule() {}

    /**
     * @param userId 用户id
     * @param courseId 课程id
     */
    public ClassSchedule(String userId, Integer courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
