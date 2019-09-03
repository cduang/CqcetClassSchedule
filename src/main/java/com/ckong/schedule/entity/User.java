package com.ckong.schedule.entity;

import java.util.Objects;

/**
 * 用户bean
 * @author kongzhiqiang
 */
public class User {

    private String userId;
    private String password;
    private String locationSchool;

    public User(){
        this.userId = "";
        this.password = "";
        this.locationSchool = "";
    }
    /**
     * @param userId 教务系统账号
     * @param password 教务系统密码密码
     * @param locationSchool 所在学校 默认电子工程职业学院
     */
    public User(String userId, String password, String locationSchool) {
        this.userId = userId;
        this.password = password;
        this.locationSchool = locationSchool;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocationSchool() {
        return locationSchool;
    }

    public void setLocationSchool(String locationSchool) {
        this.locationSchool = locationSchool;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        User other = (User)o;
        return Objects.equals(this.userId, other.userId) &&
                Objects.equals(this.password, other.password) &&
                Objects.equals(this.locationSchool, other.locationSchool);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", locationSchool='" + locationSchool + '\'' +
                '}';
    }
}
