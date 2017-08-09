package com.huawei.deviceviewer.entity;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/22
 * Email: sunyadi@huawei.com
 */
public class Device {
    private int id;
    private String name = "";
    private String controllerIPs = "";
    private String type = "";
    private String occupier = "";
    private String beginTime = "";
    private String endTime = "";
    private String deviceGroup = "";
    private String deviceHostIPs = "";
    private String note = "";
    private int isOccupied = 0;
    private int status = 1;

    public Device(){}

    public Device(String type, String name, String ips, int isOccupied, String occupier) {
        this.type = type;
        this.name = name;
        this.controllerIPs = ips;
        this.isOccupied = isOccupied;
        this.occupier = occupier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIps() {
        return controllerIPs;
    }

    public void setIps(String controllerIPs) {
        this.controllerIPs = controllerIPs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOccupier() {
        return occupier;
    }

    public void setOccupier(String occupier) {
        this.occupier = occupier;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDeviceGroup() {
        return deviceGroup;
    }

    public void setDeviceGroup(String deviceGroup) {
        this.deviceGroup = deviceGroup;
    }

    public String getDeviceHostIPs() {
        return deviceHostIPs;
    }

    public void setDeviceHostIPs(String deviceHostIPs) {
        this.deviceHostIPs = deviceHostIPs;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(int isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
