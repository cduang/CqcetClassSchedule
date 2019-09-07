package com.ckong.schedule.controller.base;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ckong.schedule.utils.ResponseJson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Optional;


/**
 * 基础Servlet 抽象类本项目中所有的Servlet类都必须继承此类
 * @author kongzhiqiang
 */
public abstract class BaseControllerServlet extends HttpServlet {

    /**
     * 处理项目中请求分发
     * @param req Http请求
     * @param resp Http响应
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=UTF-8");

        // 调用的方法不能为空
        String methodName = Optional
                .ofNullable(req.getParameter("method"))
                .map(String::trim)
                .orElse("toNotFoundMethodErrorPage");

        Method invokeMethod;
        try {
            invokeMethod = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            ResponseJson res = new ResponseJson();
            res.setMessage("您所调用的方法名不存在: " + methodName);
            Writer writer = new OutputStreamWriter(resp.getOutputStream());
            try (PrintWriter out = new PrintWriter(writer)) {
                out.print(JSON.toJSONString(res));
            }
            throw new ServletException(methodName + "方法不存在");
        }

        try {

            ResponseJson result = (ResponseJson) invokeMethod.invoke(this, req, resp);
            System.out.println("Start....");
            String str = JSONObject.toJSONString(result);
            Writer writer = new OutputStreamWriter(resp.getOutputStream());

            try (PrintWriter out = new PrintWriter(writer)) {
                out.print(str);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e ) {
            // 处理项目中抛出的SQLException
            if (e.getTargetException() instanceof SQLException ) {
                e.printStackTrace();
            }
        }

    }

    public void toNotFoundMethodErrorPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect( req.getContextPath() + "/static/NotFoundMethodPage.jsp");
    }

}
