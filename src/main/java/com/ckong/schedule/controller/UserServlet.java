package com.ckong.schedule.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ckong.schedule.controller.base.BaseControllerServlet;
import com.ckong.schedule.utils.ResponseJson;
import com.ckong.schedule.entity.Course;
import com.ckong.schedule.entity.User;
import com.ckong.schedule.exceptions.GetClassScheduleNetWorkException;
import com.ckong.schedule.exceptions.UserNameOrPasswordException;
import com.ckong.schedule.service.factory.ServiceFactory;
import com.ckong.schedule.utils.cqcet.ClassScheduleCqcet;
import com.ckong.schedule.utils.cqcet.CourseProcessorCqcetImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 处理关于用户操作的Servlet
 *
 * @author kongzhiqiang
 */

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseControllerServlet {

    /**
     * @param req  Http请求
     * @param resp Http响应
     * @return 处理结果
     */
    public ResponseJson login(HttpServletRequest req, HttpServletResponse resp) throws SQLException{

        User requestUser = new User();
        requestUser.setUserId(req.getParameter("username"));
        requestUser.setPassword(req.getParameter("password"));
        requestUser.setLocationSchool(
                new String(req.getParameter("location_school").getBytes(), StandardCharsets.UTF_8));

        ResponseJson json = new ResponseJson();

        switch (isRegister(requestUser)) {

            case 1: {
                try {
                    try {
                        if (this.register(requestUser)) {
                                json.setMessage("注册并登录成功");
                                json.setStatus("ok");
                                json.setData((JSONObject)JSON.toJSON(ServiceFactory.getCourserServiceInstance()
                                        .finUserAllCourseById(requestUser.getUserId())));
                        } else {
                            json.setStatus("error");
                            json.setMessage("登录失败原因未知");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        json.setStatus("error");
                        json.setMessage("获得数据失败,请稍后重试");
                    }
                } catch (UserNameOrPasswordException e) {
                    json.setStatus("error");
                    json.setMessage("账号或密码错误请稍后重试");
                } catch (GetClassScheduleNetWorkException e) {
                    json.setStatus("error");
                    json.setMessage("网络超时请稍后重试");
                }
                break;
            }
            case 3: {
                json.setStatus("error");
                json.setMessage("密码错误");
                break;
            }
            case 5: {
                json.setStatus("error");
                json.setMessage("学校填写错误");
                break;
            }
            case 3 + 5: {
                json.setStatus("error");
                json.setMessage("学校和密码都填写错误");
                break;
            }

            default: {
                try {
                    json.setMessage("登录成功");
                    json.setStatus("ok");
                    json.setData((JSONObject)JSON.toJSON(ServiceFactory.getCourserServiceInstance()
                            .finUserAllCourseById(requestUser.getUserId())));
                } catch (SQLException e) {
                    e.printStackTrace();
                    json.setMessage("error");
                    json.setMessage("获得数据失败,请稍后重试");
                }
            }
        }
        return json;
    }

    /**
     * 用户注册
     * @param registerUser 注册的User对象
     */
    private boolean register(User registerUser) throws UserNameOrPasswordException
            , GetClassScheduleNetWorkException, SQLException {

        Map<Integer, List<Course>> courses = this.getClassSchedule(registerUser.getUserId()
                , registerUser.getPassword());

        return ServiceFactory.getUserServiceInstance().addNewUser(registerUser, courses);
    }

    private Map<Integer, List<Course>> getClassSchedule(String userName, String password)
            throws UserNameOrPasswordException,
            GetClassScheduleNetWorkException {
        String html = new ClassScheduleCqcet(userName, password).getClassSchedule();
        return new CourseProcessorCqcetImpl().processor(html);
    }


    /**
     * 检测用户是否已经成功注册
     *
     * @param requestUser 从客户端传来的User对象
     * @return 1:账户不相等, 3:密码不相等, 5:学校不相等
     */
    private int isRegister(User requestUser) throws SQLException {

        int result = 0;

        User findUser = ServiceFactory.getUserServiceInstance().
                findUserById(requestUser.getUserId());

        if (!requestUser.getUserId().equals(findUser.getUserId())) {
            return 1;
        }
        if (!requestUser.getPassword().equals(findUser.getPassword())) {
            result += 3;
        }
        if (!requestUser.getLocationSchool().equals(findUser.getLocationSchool())) {
            result += 5;
        }
        return result;
    }

}
