package com.ckong.schedule.controller;

import com.ckong.schedule.controller.base.BaseControllerServlet;
import com.ckong.schedule.controller.resdata.BaseResponseData;
import com.ckong.schedule.controller.resdata.FailResponseDataImpl;
import org.apache.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户更新课程接口
 * @author kongzhiqiang
 *
 */
public class UpdateServlet  extends BaseControllerServlet {

    public BaseResponseData<?> updateCourse(HttpServletRequest req, HttpServletResponse res) {

        String username = req.getParameter("username");
        String password = req.getParameter("username");

    }
}
