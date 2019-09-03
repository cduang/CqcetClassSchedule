package com.ckong.schedule.service.impl;

import com.ckong.schedule.dao.factory.DaoFactory;
import com.ckong.schedule.entity.Course;
import com.ckong.schedule.entity.User;
import com.ckong.schedule.service.IUserService;
import com.ckong.schedule.utils.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 关于用户的业务业务层实现
 * @author kongzhiqiang
 */
public class UserServiceImpl implements IUserService {


    @Override
    public boolean addNewUser(User newUser, Map<Integer, List<Course>> courses) throws SQLException {

        Connection conn  = DbUtil.getConnection();

        try {
             if(DaoFactory.getUserDaoInstance(conn).doCreateUser(newUser)) {
                 DaoFactory.getCourseDaoInstance(conn).insertBatch(courses, newUser.getUserId());
                 return true;
             } else {
                 return false;
             }
        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtil.close(conn);
        }

    }

    @Override
    public boolean deleteUserById(String userId) throws SQLException{

        Connection conn  = DbUtil.getConnection();

        try {
            return DaoFactory.getUserDaoInstance(conn).doRemoveById(userId);
        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtil.close(conn);
        }
    }

    @Override
    public boolean updateUserInfo(User user) throws SQLException{

        Connection conn  = DbUtil.getConnection();

        try {
            return DaoFactory.getUserDaoInstance(conn).doUpdateUser(user);
        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtil.close(conn);
        }
    }

    @Override
    public User findUserById(String userId) throws SQLException{

        Connection conn  = DbUtil.getConnection();

        try {
            return DaoFactory.getUserDaoInstance(conn).findUserById(userId);
        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtil.close(conn);
        }
    }

    @Override
    public boolean isUserExist(String userId) throws SQLException{

        Connection conn  = DbUtil.getConnection();

        try {
            return DaoFactory.getUserDaoInstance(conn).isUserExist(userId);
        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtil.close(conn);
        }
    }
}
