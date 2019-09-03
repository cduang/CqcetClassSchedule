package com.ckong.schedule.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 封装我们的响应消息对象，
 * @author kongzhiqiang
 *
 */
public class ResponseJson implements Serializable {

    private String status;
    private JSONObject data;
    private String message;

    public ResponseJson() {
        this.status = "";
        this.data = null;
        this.message = "";

    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
