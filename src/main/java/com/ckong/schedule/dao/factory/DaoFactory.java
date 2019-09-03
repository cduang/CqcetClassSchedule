package com.ckong.schedule.dao.factory;

import com.ckong.schedule.dao.IClassScheduleDao;
import com.ckong.schedule.dao.ICourseDao;
import com.ckong.schedule.dao.IUserDao;
import com.ckong.schedule.dao.impl.ClassScheduleDaoImpl;
import com.ckong.schedule.dao.impl.CourseDaoImpl;
import com.ckong.schedule.dao.impl.UserDaoImpl;

import java.sql.Connection;

/**
 * dao工厂类
 * @author kongzhiqiang
 */
public class DaoFactory {

    /**
     * 获得IClassSchedule的实例对象
     * @param conn 数据库连接对象
     * @return IClassSchedule的实现类对象
     */
    public static IClassScheduleDao getClassScheduDaoInstance(Connection conn) {
        return new ClassScheduleDaoImpl(conn);
    }

    /**
     * 获得ICourse的实例对象
     * @param conn 数据库连接对象
     * @return ICourse 的实现类对象
     */
    public static ICourseDao getCourseDaoInstance(Connection conn) {
        return new CourseDaoImpl(conn);
    }

    /**
     * 获得IUserDao的实例对象
     * @param conn 数据库连接对象
     * @return IUserDao的实现类对象
     */
    public static IUserDao getUserDaoInstance(Connection conn) {
        return new UserDaoImpl(conn);
    }
}
