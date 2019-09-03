package com.ckong.schedule.dao;

import com.ckong.schedule.entity.User;

import java.sql.SQLException;

/**
 * 用户表DAO接口
 * @author kongzhiqiang
 */
public interface IUserDao {

    /**
     * 创建用户
     * @param vo 封装了用户信息的vo对象
     * @throws SQLException 查询异常
     * @return  成功true反之false
     */
    boolean doCreateUser(User vo) throws SQLException;

    /**
     * 根据user_id修改用户的信息
     * @param vo 保存用户星系的的对象
     * @throws SQLException 查询异常
     * @return  成功true反之false
     */
    boolean doUpdateUser(User vo) throws SQLException;

    /**
     * 删除用户
     * @param userId 用户id
     * @return 成功true,反之false
     * @throws SQLException SQL查询异常
     */
    boolean doRemoveById(String userId) throws SQLException;

    /**
     * 查询用户信息
     * @param userId 用户
     * @return 用户bean
     * @throws SQLException SQL异常
     */
    User findUserById(String userId) throws SQLException;

    /**
     * 验证用户是否存在
     * @param userId 用户id
     * @return 用户已经存在返回true，反之false
     * @throws SQLException SQL异常必须捕获
     */
    boolean isUserExist (String userId) throws SQLException;
}