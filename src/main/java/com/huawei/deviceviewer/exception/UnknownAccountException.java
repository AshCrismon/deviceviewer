package com.huawei.deviceviewer.exception;

/**
 * Created by Intellij IDEA.
 * Author: hadoop
 * Date: 2017/7/30
 * Email: yadysun@gmail.com
 */
public class UnknownAccountException extends RuntimeException{

    public UnknownAccountException() {
        super("Unknown account!");
    }
}
