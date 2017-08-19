package com.huawei.deviceviewer.exception;

/**
 * Created by Intellij IDEA.
 * Author: gyyjq
 * Date: 2017/8/8
 * Email: yadysun@gmail.com
 */
public class InvalidTimeRangeException extends RuntimeException{

    public InvalidTimeRangeException(){
        super("不合法的时间范围!");
    }

    public InvalidTimeRangeException(String msg){
        super(msg);
    }
}

