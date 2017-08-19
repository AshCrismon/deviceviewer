package com.huawei.deviceviewer.entity;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/22
 * Email: sunyadi@huawei.com
 */
public class Device {
    private int id;
    private String deviceName = "";
    private String controllerIPs = "";
    private String deviceType = "";
    private String occupier = "";
    private String beginTime = "";
    private String endTime = "";
    private String deviceGroup = "";
    private String deviceHostIPs = "";
    private String note = "";
    private String hostAccount = "";
    private String controllerAccount = "";
    private int isOccupied = 0;
    private int status = 1;

    public Device(){}

    public Device(String deviceType, String deviceName, String ips, int isOccupied, String occupier) {
        this.deviceType = deviceType;
        this.deviceName = deviceName;
        this.controllerIPs = ips;
        this.isOccupied = isOccupied;
        this.occupier = occupier;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getControllerAccount() {
        return controllerAccount;
    }

    public void setControllerAccount(String controllerAccount) {
        this.controllerAccount = controllerAccount;
    }

    public String getControllerIPs() {
        return controllerIPs;
    }

    public void setControllerIPs(String controllerIPs) {
        this.controllerIPs = controllerIPs;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getHostAccount() {
        return hostAccount;
    }

    public void setHostAccount(String hostAccount) {
        this.hostAccount = hostAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(int isOccupied) {
        this.isOccupied = isOccupied;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOccupier() {
        return occupier;
    }

    public void setOccupier(String occupier) {
        this.occupier = occupier;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
