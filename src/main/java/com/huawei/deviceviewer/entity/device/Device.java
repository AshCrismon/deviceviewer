package com.huawei.deviceviewer.entity.device;

import com.huawei.deviceviewer.entity.EntityState;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/22
 * Email: sunyadi@huawei.com
 */
public class Device {
    private int id;
    @NotBlank(message="{device.deviceName}")
    private String deviceName;
    @NotBlank(message="{device.controllerIPs}")
    private String controllerIPs;
    @NotBlank(message="{device.deviceType}")
    private String deviceType;
    @NotBlank(message="{device.deviceGroup}")
    private String deviceGroup;
    @NotBlank(message="{device.deviceHostIPs}")
    private String deviceHostIPs;
    @NotBlank(message="{device.hostAccount}")
    private String hostAccount;
    @NotBlank(message="{device.controllerAccount}")
    private String controllerAccount;

    private String occupier = "";
    private String beginTime = "";
    private String endTime = "";
    private int occupancyState = DeviceState.FREE.value();
    private String note = "";
    private int status = EntityState.NORMAL.value();

    public Device(){}

    public Device(String deviceType, String deviceName, String ips, int occupancyState, String occupier) {
        this.deviceType = deviceType;
        this.deviceName = deviceName;
        this.controllerIPs = ips;
        this.occupancyState = occupancyState;
        this.occupier = occupier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getControllerIPs() {
        return controllerIPs;
    }

    public void setControllerIPs(String controllerIPs) {
        this.controllerIPs = controllerIPs;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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

    public String getHostAccount() {
        return hostAccount;
    }

    public void setHostAccount(String hostAccount) {
        this.hostAccount = hostAccount;
    }

    public String getControllerAccount() {
        return controllerAccount;
    }

    public void setControllerAccount(String controllerAccount) {
        this.controllerAccount = controllerAccount;
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

    public int getOccupancyState() {
        return occupancyState;
    }

    public void setOccupancyState(int occupancyState) {
        this.occupancyState = occupancyState;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", controllerIPs='" + controllerIPs + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceGroup='" + deviceGroup + '\'' +
                ", deviceHostIPs='" + deviceHostIPs + '\'' +
                ", hostAccount='" + hostAccount + '\'' +
                ", controllerAccount='" + controllerAccount + '\'' +
                ", occupier='" + occupier + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", occupancyState=" + occupancyState +
                ", note='" + note + '\'' +
                ", status=" + status +
                '}';
    }
}
