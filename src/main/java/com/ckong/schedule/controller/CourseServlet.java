package com.ckong.schedule.controller;


import com.alibaba.fastjson.JSONObject;
import com.ckong.schedule.controller.base.BaseControllerServlet;
import com.ckong.schedule.entity.Course;
import com.ckong.schedule.entity.User;
import com.ckong.schedule.exceptions.GetClassScheduleNetWorkException;
import com.ckong.schedule.exceptions.UserNameOrPasswordException;
import com.ckong.schedule.service.factory.ServiceFactory;
import com.ckong.schedule.utils.ResponseJson;
import com.ckong.schedule.utils.cqcet.ClassScheduleCqcet;
import com.ckong.schedule.utils.cqcet.CourseProcessorCqcetImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * 处理全部与课程有关的操作
 * @author kongzhqiang
 */
@WebServlet(name = "courseServlet", urlPatterns = "/CourseServlet")
public class CourseServlet extends BaseControllerServlet {

    private  ResponseJson json = new ResponseJson();

    /**
     * 更新课程
     * @param req 请求
     * @param response 响应
     * @return 响应json
     */
    public ResponseJson updateCourse(HttpServletRequest req, HttpServletResponse response) throws SQLException {

        User reqUser = new User();
        reqUser.setUserId(req.getParameter("username"));
        reqUser.setPassword(req.getParameter("password"));

        if (!isUserExist(reqUser)) {
            json.setStatus("error");
            json.setMessage("用户不存在");
        }

        ClassScheduleCqcet util = new ClassScheduleCqcet(reqUser.getUserId(), reqUser.getPassword());
        try {
            Map<Integer, List<Course>> newCourses = new CourseProcessorCqcetImpl()
                    .processor(util.getClassSchedule());

            if (ServiceFactory.getCourserServiceInstance()
                    .insertBatch(newCourses, reqUser.getUserId())) {

                // 取得全部的课程
                Map<String, List<Course>> courses = ServiceFactory
                        .getCourserServiceInstance()
                        .finUserAllCourseById(reqUser.getUserId());

                json.setData((JSONObject)JSONObject.toJSON(courses));
                json.setMessage("课程更新成功");
                json.setStatus("ok");

            } else {
                json.setMessage("内部错误，请排查");
                json.setStatus("error");
            }

        } catch (UserNameOrPasswordException e) {
            json.setMessage("账号和密码错误请更新用户信息");
            json.setStatus("error");

        } catch (GetClassScheduleNetWorkException e) {
            json.setMessage("网络超时");
            json.setStatus("error");
        }

        return json;
    }

    /**
     * 检测用户是否存在
     * @param user 用户vo对象(必须保存有id属性值)
     * @return 存在true，反之false
     */
    private boolean isUserExist(User user) throws SQLException{
        return ServiceFactory
                .getUserServiceInstance()
                .isUserExist(user.getUserId());
    }
}
