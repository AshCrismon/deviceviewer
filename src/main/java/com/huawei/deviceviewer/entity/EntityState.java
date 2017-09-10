package com.huawei.deviceviewer.entity;

public enum EntityState {
    NORMAL("有效", 1),
    LOCKED("锁定", 0),
    DELETE("无效", -1)
    ;

    private String desc;
    private int value;
    private EntityState(String desc, int value){
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
