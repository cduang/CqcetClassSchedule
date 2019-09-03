package com.ckong.schedule.controller.resdata;


/**
 * 用于json序列化的的响应对象基类
 * @author kongzhiqiang
 */
public abstract class BaseResponseData<T> {

    private String status;
    private T data;

    protected BaseResponseData(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponseData{" +
                "statusCode='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
