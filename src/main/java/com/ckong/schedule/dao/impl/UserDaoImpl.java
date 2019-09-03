package com.ckong.schedule.dao.impl;

import com.ckong.schedule.dao.IUserDao;
import com.ckong.schedule.entity.User;
import com.ckong.schedule.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * user表的dao实现
 * @author kongzhiqiang
 */
public class UserDaoImpl implements IUserDao {

    private PreparedStatement pstmt;
    private Connection conn;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean doCreateUser(User vo) throws SQLException {

        String sql = "INSERT INTO user ( user_id, password, location_school )" +
                "VALUES ( ?, ? , ? )";

        try {
            this.pstmt = conn.prepareStatement(sql);
            this.pstmt.setString(1, vo.getUserId());
            this.pstmt.setString(2, vo.getPassword());
            this.pstmt.setString(3, vo.getLocationSchool());

            return this.pstmt.executeUpdate() > 0;

        } finally {
            DbUtil.close(this.pstmt);
        }

    }

    @Override
    public boolean doUpdateUser(User vo) throws SQLException {

        String sql = " UPDATE user SET user_id = ?, password = ?, location_school = ? " +
                "WHERE user_id = ? ";

        try {
            this.pstmt = conn.prepareStatement(sql);
            this.pstmt.setString(1, vo.getUserId());
            this.pstmt.setString(2, vo.getPassword());
            this.pstmt.setString(3, vo.getLocationSchool());
            this.pstmt.setString(4, vo.getUserId());
            return this.pstmt.executeUpdate() > 0;
        } finally {
            DbUtil.close(this.pstmt);
        }

    }

    @Override
    public boolean doRemoveById(String userId) throws SQLException {

        // 错误的实现
        String sql = " DELETE FROM user WHERE user_id = ? ";
        try {

            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, userId);

            return this.pstmt.executeUpdate() > 0;
        } finally {
            DbUtil.close(this.pstmt);
        }
    }

    @Override
    public User findUserById(String userId) throws SQLException {

        String sql = " SELECT user_id, password, location_school FROM user " +
                " WHERE user_id = ? ";
        ResultSet result = null;
        User user = new User();
        try {
            this.pstmt = conn.prepareStatement(sql);
            this.pstmt.setString(1, userId);

            result = this.pstmt.executeQuery();

            if (result.next()) {
                user.setUserId(result.getString("user_id"));
                user.setPassword(result.getString("password"));
                user.setLocationSchool(result.getString("location_school"));
                return user;
            }
        } finally {
            DbUtil.close(this.pstmt);
            DbUtil.close(result);
        }

        return user;
    }


    @Override
    public boolean isUserExist(String userId) throws SQLException {

        String sql = " SELECT user_id, password FROM user WHERE user_id = ?" ;
        ResultSet resultSet = null;

        try {
            this.pstmt = conn.prepareStatement(sql);
            this.pstmt.setString(1, userId);
            resultSet = this.pstmt.executeQuery();
            return resultSet.next();

        } finally {
            DbUtil.close(this.pstmt);
            DbUtil.close(resultSet);
        }

    }
}