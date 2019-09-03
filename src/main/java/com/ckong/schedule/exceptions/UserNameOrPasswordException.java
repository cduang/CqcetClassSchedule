package com.ckong.schedule.exceptions;

/**
 * 用户名或密码错误异常
 * @author kongzhiqiang
 *
 */
public class UserNameOrPasswordException extends Exception {

    public UserNameOrPasswordException(String msg) {
        super(msg);
    }

}
