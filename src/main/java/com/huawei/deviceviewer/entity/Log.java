package com.huawei.deviceviewer.entity;

import com.huawei.deviceviewer.utils.DateUtils;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/19
 * Email: sunyadi@huawei.com
 */
public class Log {
    private int id;
    private int deviceId;
    private String username;
    private int actionType;
    private String beginTime = "";
    private String endTime = "";
    private String createTime = DateUtils.now();
    private int status = 1;

    public Log(){}

    public Log(int deviceId, String username, int actionType, String beginTime, String endTime) {
        this.deviceId = deviceId;
        this.username = username;
        this.actionType = actionType;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Log(int deviceId, String username, int actionType) {
        this.deviceId = deviceId;
        this.username = username;
        this.actionType = actionType;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
