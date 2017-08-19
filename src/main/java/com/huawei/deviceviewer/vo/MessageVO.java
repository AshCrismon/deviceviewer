package com.huawei.deviceviewer.vo;

import org.springframework.stereotype.Component;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/13
 * Email: sunyadi@huawei.com
 */
@Component
public class MessageVO {

    private int statusCode;
    private String msg;
    private Object data = "";
    private Object token;

    public MessageVO(){}

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }
}
