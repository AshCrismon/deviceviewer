package com.huawei.deviceviewer.entity.device;

public enum DeviceState {

    FREE("空闲", 0),
    OCCUPYING("占用", 1)
    ;

    private String desc;
    private int value;
    private DeviceState(String desc, int value){
        this.desc = desc;
        this.value = value;
    }

    public int value(){
        return value;
    }

    public String desc(){
        return desc;
    }
}
