package com.huawei.deviceviewer.service;

import com.huawei.deviceviewer.entity.device.Device;
import com.huawei.deviceviewer.entity.common.Page;

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

    public Device loadDeviceById(int deviceId);

    public List<Device> loadAllDevices();

    public Page<Device> loadDevicesByPage(int pageNo, int pageSize);

    public void applyDevice(int deviceId, String username, String beginTime, String endTime);

    public void releaseDevice(int deviceId, String username);
}
