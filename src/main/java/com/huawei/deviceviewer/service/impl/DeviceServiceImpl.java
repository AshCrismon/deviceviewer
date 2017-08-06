package com.huawei.deviceviewer.service.impl;

import com.huawei.deviceviewer.dao.DeviceDao;
import com.huawei.deviceviewer.entity.Device;
import com.huawei.deviceviewer.entity.Page;
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
    public Page<Device> loadByPage(int pageNo, int pageSize) {
        Page<Device> devicePage = new Page<>();
        int start = (pageNo - 1) * pageSize;
        int count = deviceDao.count();
        List<Device> deviceList = deviceDao.loadPage(start, pageSize);
        devicePage.setPageNo(pageNo);
        devicePage.setPageSize(pageSize);
        devicePage.setTotalCount(count);
        devicePage.setTotalPages(count / pageSize + 1);
        devicePage.setObjList(deviceList);
        return devicePage;
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

    @Override
    public void cancelDevice(int id, String username) {
        Device device = deviceDao.load(id);
        device.setIsOccupied(0);
        device.setOccupier("");
        device.setBeginTime("");
        device.setEndTime("");
        deviceDao.update(device);
    }

}
