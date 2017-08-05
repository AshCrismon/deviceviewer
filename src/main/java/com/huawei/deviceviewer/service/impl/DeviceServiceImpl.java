package com.huawei.deviceviewer.service.impl;

import com.huawei.deviceviewer.dao.DeviceDao;
import com.huawei.deviceviewer.entity.Device;
import com.huawei.deviceviewer.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/5
 * Email: sunyadi@huawei.com
 */
@Service
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    private DeviceDao deviceDao;

    @Override
    public void insertDevice(Device device) {
        deviceDao.insert(device);
    }

    @Override
    public void deleteDevice(int deviceId) {
        deviceDao.delete(deviceId);
    }

    @Override
    public void updateDevice(Device device) {
        deviceDao.update(device);
    }

    @Override
    public Device loadById(int id) {
        return null;
    }

    @Override
    public List<Device> loadAll() {
        return deviceDao.loadAll();
    }

    @Override
    public void applyDevice(int id, String username, String beginTime, String endTime) {
        Device device = deviceDao.load(id);
        device.setIsOccupied(1);
        device.setOccupier(username);
        device.setBeginTime(beginTime);
        device.setEndTime(endTime);
        deviceDao.update(device);
    }

}
