package com.huawei.deviceviewer.service.impl;

import com.huawei.deviceviewer.dao.DeviceDao;
import com.huawei.deviceviewer.entity.device.Device;
import com.huawei.deviceviewer.entity.device.DeviceState;
import com.huawei.deviceviewer.entity.common.MessageTip;
import com.huawei.deviceviewer.entity.common.Page;
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
        checkIfExists(device.getId());
        deviceDao.update(device);
    }

    @Override
    public Device loadDeviceById(int deviceId) {
        return checkIfExists(deviceId);
    }

    @Override
    public List<Device> loadAllDevices() {
        return deviceDao.loadAll();
    }

    @Override
    public Page<Device> loadDevicesByPage(int pageNo, int pageSize) {
        Page<Device> devicePage = new Page<Device>();
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
    public void applyDevice(int deviceId, String username, String beginTime, String endTime) {
        Device device = checkIfExists(deviceId);
        checkOccupancyState(device.getOccupancyState());
        checkTimeRange(beginTime, endTime);

        device.setOccupancyState(DeviceState.OCCUPYING.value());
        device.setOccupier(username);
        device.setBeginTime(beginTime);
        device.setEndTime(endTime);
        deviceDao.update(device);
    }

    @Override
    public void releaseDevice(int deviceId, String username) {
        Device device = checkIfExists(deviceId);
        device.setOccupancyState(DeviceState.FREE.value());
        device.setOccupier("");
        device.setBeginTime("");
        device.setEndTime("");
        deviceDao.update(device);
    }

    private Device checkIfExists(int deviceId) {
        Device device = deviceDao.load(deviceId);
        if(null == device){
            throw new RuntimeException(MessageTip.DEVICE_NOT_FOUND.value());
        }
        return device;
    }

    private void checkOccupancyState(int occupancyState){
        if (DeviceState.OCCUPYING.value() == occupancyState){
            throw new RuntimeException(MessageTip.DEVICE_OCCUPYING.value());
        }
    }

    private void checkTimeRange(String beginTime, String endTime){
        if (beginTime.compareTo(endTime) >= 0) {
            throw new RuntimeException(MessageTip.INVALID_TIME_RANGE.value());
        }
    }

}
