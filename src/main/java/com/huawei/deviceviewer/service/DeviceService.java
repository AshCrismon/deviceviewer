package com.huawei.deviceviewer.service;

import com.huawei.deviceviewer.entity.Device;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/5
 * Email: sunyadi@huawei.com
 */
public interface DeviceService {

    public void insertDevice(Device device);

    public void deleteDevice(int deviceId);

    public void updateDevice(Device device);

    public Device loadById(int id);

    public List<Device> loadAll();

    public void applyDevice(int id, String username, String beginTime, String endTime);
}
