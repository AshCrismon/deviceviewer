package com.huawei.deviceviewer.entity.common;

public enum MessageTip {

    SUCCESS("请求成功"),

    DEVICE_NOT_FOUND("设备不存在"),
    DEVICE_OCCUPYING("设备正在被占用"),
    INVALID_TIME_RANGE("开始时间不能大于结束时间"),
    DEVICE_APPLY_SUCCESS("设备申请成功"),
    DEVICE_RELEASE_SUCCESS("设备成功释放")


    ;

    private String message;
    private MessageTip(String message){
        this.message = message;
    }

    public String value(){
        return message;
    }
}
