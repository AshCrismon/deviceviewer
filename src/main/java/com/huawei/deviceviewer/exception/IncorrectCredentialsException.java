package com.huawei.deviceviewer.exception;

/**
 * Created by Intellij IDEA.
 * Author: hadoop
 * Date: 2017/7/30
 * Email: yadysun@gmail.com
 */
public class IncorrectCredentialsException extends RuntimeException{

    public IncorrectCredentialsException() {
        super("Your account or password is wrong!");
    }
}
