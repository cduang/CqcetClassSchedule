package com.ckong.schedule.controller.resdata;

/**
 * 用于成功时返回用户的Json序列化实现
 * @param <T>
 * @author kongzhiqiang
 */

public class SuccessResponseDataImpl<T> extends BaseResponseData<T> {

    public SuccessResponseDataImpl() {
        super("ok");
    }

}
