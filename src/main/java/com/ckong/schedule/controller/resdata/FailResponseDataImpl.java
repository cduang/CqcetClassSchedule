package com.ckong.schedule.controller.resdata;

/**
 * 用于失败时返回给用户的Json序列化对象
 * @author kongzhiqiang
 */
public class FailResponseDataImpl extends BaseResponseData{

    private String errorMessage;
    public FailResponseDataImpl(String statusCode) {
        super(statusCode);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
