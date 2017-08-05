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
    private String ips = "";
    private String type = "";
    private String occupier = "";
    private String beginTime = "";
    private String endTime = "";
    private int isOccupied = 0;
    private int status = 1;

    public Device(){}

    public Device(String type, String name, String ips, int isOccupied, String occupier) {
        this.type = type;
        this.name = name;
        this.ips = ips;
        this.isOccupied = isOccupied;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public int getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(int isOccupied) {
        this.isOccupied = isOccupied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
