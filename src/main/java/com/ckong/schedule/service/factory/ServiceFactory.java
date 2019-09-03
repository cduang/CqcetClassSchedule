package com.ckong.schedule.service.factory;

import com.ckong.schedule.service.ICourseService;
import com.ckong.schedule.service.IUserService;
import com.ckong.schedule.service.impl.CourseServiceImpl;
import com.ckong.schedule.service.impl.UserServiceImpl;

/**
 * 业务层工厂类
 * @author kongzhiqiang
 */
public class ServiceFactory {

    /**
     * 得到ICourseService接口实例
     * @return ICourseService接口实例
     */
    public static ICourseService getCourserServiceInstance() {
        return new CourseServiceImpl();
    }

    /**
     * 得到IUserService
     * @return IUserService实例
     */
    public static IUserService getUserServiceInstance() {
        return new UserServiceImpl();
    }
}
