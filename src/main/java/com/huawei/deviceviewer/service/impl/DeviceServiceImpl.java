package com.huawei.deviceviewer.service.impl;

import com.huawei.deviceviewer.dao.DeviceDao;
import com.huawei.deviceviewer.entity.Device;
import com.huawei.deviceviewer.entity.Page;
import com.huawei.deviceviewer.exception.EmptyArgumentException;
import com.huawei.deviceviewer.exception.EntityNotFoundException;
import com.huawei.deviceviewer.exception.InvalidTimeRangeException;
import com.huawei.deviceviewer.service.DeviceService;
import org.apache.commons.lang3.StringUtils;
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
        validateArgument(device);
        deviceDao.insert(device);
    }

    @Override
    public void deleteDevice(int deviceId) {
        validateIfExists(deviceId);
        deviceDao.delete(deviceId);
    }

    @Override
    public void updateDevice(Device device) {
        validateArgument(device);
        deviceDao.update(device);
    }

    @Override
    public Device loadDeviceById(int deviceId) {
        return validateIfExists(deviceId);
    }

    @Override
    public List<Device> loadAllDevices() {
        return deviceDao.loadAll();
    }

    @Override
    public Page<Device> loadDevicesByPage(int pageNo, int pageSize) {
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
    public void applyDevice(int deviceId, String username, String beginTime, String endTime) {
        Device device = validateIfExists(deviceId);
        validateIfOccupied(device);
        validateTimeRange(beginTime, endTime);
        device.setIsOccupied(1);
        device.setOccupier(username);
        device.setBeginTime(beginTime);
        device.setEndTime(endTime);
        deviceDao.update(device);
    }

    @Override
    public void cancelDevice(int deviceId, String username) {
        Device device = validateIfExists(deviceId);
        device.setIsOccupied(0);
        device.setOccupier("");
        device.setBeginTime("");
        device.setEndTime("");
        deviceDao.update(device);
    }


    public void validateArgument(Device device) {
        if(null == device){
            throw new NullPointerException("设备信息不能为空 !");
        }
        if(StringUtils.isAnyBlank(device.getDeviceName(), device.getControllerIPs(), device.getDeviceType(), device.getDeviceHostIPs())) {
            throw new EmptyArgumentException("设备名、设备类型、控制器IP和主机IP等不能为空！");
        }
    }

    public Device validateIfExists(int deviceId) {
        Device device = deviceDao.load(deviceId);
        if(null == device){
            throw new EntityNotFoundException("设备不存在！");
        }
        return device;
    }

    public void validateIfOccupied(Device device){
        if (1 == device.getIsOccupied()){
            throw new RuntimeException("设备已经被占用 !");
        }
    }

    public void validateTimeRange(String beginTime, String endTime){
        if (beginTime.compareTo(endTime) >= 0) {
            throw new InvalidTimeRangeException("开始时间不能大于结束时间！");
        }
    }

}
