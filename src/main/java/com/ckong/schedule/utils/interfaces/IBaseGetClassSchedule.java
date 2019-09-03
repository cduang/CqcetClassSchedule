package com.ckong.schedule.utils.interfaces;

import com.ckong.schedule.exceptions.GetClassScheduleNetWorkException;
import com.ckong.schedule.exceptions.UserNameOrPasswordException;

/**
 * 获取课表HTML的基本接口,根据不同的系统去实现。
 * @author kongzhiqiang
 */
public interface IBaseGetClassSchedule {
    /**
     * 获取课表HTML以String类型返回
     * @return 课程表HTML
     * @throws  UserNameOrPasswordException 用户名或者密码错误时抛出
     * @throws  GetClassScheduleNetWorkException 登录或者获取课表超时抛出
     */
    String getClassSchedule() throws UserNameOrPasswordException, GetClassScheduleNetWorkException;
}
