package com.ckong.schedule.exceptions;

/**
 * BaseServlet调用方不存在异常
 * @author kongzhiqiang
 */
public class InvokeMethodNoSuchException extends RuntimeException{

    public InvokeMethodNoSuchException() {
        super();
    }
    public InvokeMethodNoSuchException(String msg) {
        super(msg);
    }
    public InvokeMethodNoSuchException(String msg, Throwable c) {
        super(msg, c);
    }
}
