package com.huawei.deviceviewer.exception;

/**
 * Created by Intellij IDEA.
 * Author: gyyjq
 * Date: 2017/8/8
 * Email: yadysun@gmail.com
 */
public class InvalidTimeRangeException extends RuntimeException{

    public InvalidTimeRangeException(){
        super("Invalid time range!");
    }

    public InvalidTimeRangeException(String msg){
        super(msg);
    }
}

