package com.huawei.deviceviewer.entity;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/19
 * Email: sunyadi@huawei.com
 */
public enum ActionType {

    APPLY(1),
    RELEASE(0);

    private int value;

    private ActionType(int value){
        this.value = value;
    }

    public int value(){
        return this.value;
    }
}
