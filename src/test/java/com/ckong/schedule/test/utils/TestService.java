package com.ckong.schedule.test.utils;

import com.ckong.schedule.entity.Course;
import com.ckong.schedule.entity.User;
import com.ckong.schedule.exceptions.GetClassScheduleNetWorkException;
import com.ckong.schedule.exceptions.UserNameOrPasswordException;
import com.ckong.schedule.service.factory.ServiceFactory;
import com.ckong.schedule.utils.cqcet.ClassScheduleCqcet;
import com.ckong.schedule.utils.cqcet.CourseProcessorCqcetImpl;
import com.ckong.schedule.utils.interfaces.IBaseGetClassSchedule;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestService {

    public static void main(String[] args) {

        IBaseGetClassSchedule classScheduleUtil = new ClassScheduleCqcet("2017130323", "q7878478");
        Map<Integer, List<Course>> courses = new LinkedHashMap<>();
        try {
            courses = new CourseProcessorCqcetImpl().processor(classScheduleUtil.getClassSchedule());
        } catch (UserNameOrPasswordException e) {
            System.out.println("账号或密码错误");
            e.printStackTrace();
        } catch (GetClassScheduleNetWorkException e) {
            System.out.println("网络超时");
            e.printStackTrace();
        }


    }
}
