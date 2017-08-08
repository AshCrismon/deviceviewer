package com.huawei.deviceviewer.exception;

/**
 * Created by Intellij IDEA.
 * Author: gyyjq
 * Date: 2017/8/8
 * Email: yadysun@gmail.com
 */
public class DeviceOccupyingException extends RuntimeException{
    public DeviceOccupyingException(){
        super("device is occupying!");
    }

    public DeviceOccupyingException(String msg){
        super(msg);
    }
}
