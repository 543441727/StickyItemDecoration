package com.qianmo.stickyitemdecoration.base;


/**
 * Created by wangjitao on 2017/3/3 0003.
 * E-Mail：543441727@qq.com
 * 数据返回体的基本接收类,由于本次接口没有code和message
 */

public class BaseResponse<T> {
    //    private int code;
//    private String message;
    private T data;

//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
