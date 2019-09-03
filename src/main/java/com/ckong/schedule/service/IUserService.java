package com.ckong.schedule.service;

import com.ckong.schedule.entity.Course;
import com.ckong.schedule.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 关于用户业务层接口
 * @author kongzhiqiang
 */
public interface IUserService {

    /**
     * 添加新用户需要使用到UserDao中的: </br>
     * <li> doCreateUser 方法</li>,
     * 和Course中的:
     * <li>insertBatch 方法</li>
     * @param newUser 新用户entity
     * @return 成功true，否则false
     */
    boolean addNewUser(User newUser, Map<Integer, List<Course>> courses) throws SQLException;

    boolean deleteUserById(String userId) throws SQLException;

    boolean updateUserInfo(User user) throws SQLException;

    /**
     * 根据用户id调用查找数据
     * @param userId 用户id
     * @return 成功返回返回装有数据的对象,失败怎返回空的User对象
     * @throws SQLException SQL异常
     */
    User findUserById (String userId) throws SQLException;

    boolean isUserExist (String userId) throws SQLException;
}
