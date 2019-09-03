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

        this.getServletContext().getContextPath();

        String parameter = req.getParameter("method").trim();
        Method invokeMethod;
        try {
            invokeMethod = this.getClass().getMethod(parameter, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            ResponseJson res = new ResponseJson();
            res.setMessage("您所调用的方法名不存在: " + parameter);
            Writer writer = new OutputStreamWriter(resp.getOutputStream());
            try (PrintWriter out = new PrintWriter(writer)) {
                out.print(JSON.toJSONString(res));
            }
            throw new ServletException(parameter + "方法不存在");
        }

        try {
            invokeMethod.setAccessible(true);

            ResponseJson result = (ResponseJson) invokeMethod.invoke(this, req, resp);
            System.out.println("Start....");
            String str = JSONObject.toJSONString(result);
            Writer writer = new OutputStreamWriter(resp.getOutputStream());

            try (PrintWriter out = new PrintWriter(writer)) {
                out.print(str);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
