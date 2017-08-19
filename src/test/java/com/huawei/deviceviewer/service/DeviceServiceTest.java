package com.huawei.deviceviewer.service;

import com.huawei.deviceviewer.config.AbstractTestConfig;
import com.huawei.deviceviewer.entity.Device;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/5
 * Email: sunyadi@huawei.com
 */
public class DeviceServiceTest extends AbstractTestConfig{

    @Autowired
    private DeviceService deviceService;

    @Test
    public void testInsertDevice() throws InvalidArgumentException {
        Device device1 = new Device("18000V2R2", "anonymous", "8.46.47.145/146", 0, "");
        Device device2 = new Device("18000V2R2", "anonymous", "8.46.47.157/158", 0, "");
        Device device3 = new Device("18000V2R2", "anonymous", "8.46.47.182/183", 0, "");
        Device device4 = new Device("18000V2R2", "anonymous", "8.46.47.168/169", 0, "");
        Device device5 = new Device("18000V2R2", "anonymous", "8.46.47.171/172", 0, "");
        Device device6 = new Device("18000V2R2", "anonymous", "8.46.47.182/183", 0, "");
        Device device7 = new Device("18000V2R2", "anonymous", "8.46.47.191/192", 0, "");
        Device device8 = new Device("18000V2R2", "anonymous", "8.46.47.221/222", 0, "");
        Device device9 = new Device("18000V2R2", "anonymous", "8.46.47.242/243", 0, "");
        Device device10 = new Device("18000V2R2", "anonymous", "8.46.47.56/57", 0, "");
        deviceService.insertDevice(device1);
        deviceService.insertDevice(device2);
        deviceService.insertDevice(device3);
        deviceService.insertDevice(device4);
        deviceService.insertDevice(device5);
        deviceService.insertDevice(device6);
        deviceService.insertDevice(device7);
        deviceService.insertDevice(device8);
        deviceService.insertDevice(device9);
        deviceService.insertDevice(device10);
    }

    @Test
    public void testLoadAll(){
        List<Device> deviceList = deviceService.loadAllDevices();
        print("total records: " + deviceList.size());
        for(Device device : deviceList){
            print(
                    "Device IPs: " + device.getControllerIPs() +
                    ", IsOccupied: " + device.getIsOccupied() +
                    ", Occupier: " + device.getOccupier());
        }
        print("over");
    }

    @Test
    public void testApplyDevice(){
        int deviceId = 1;
        String username = "s00423985";
        String beginTime = "2017-08-05 12:12:12";
        String endTime = "2017-08-05 21:12:12";
        deviceService.applyDevice(deviceId, username, beginTime, endTime);
    }

    @Test
    public void testLoadDevice(){
        int deviceId = 1;
        Device device = deviceService.loadDeviceById(deviceId);
        print(device.getDeviceType());
    }
}
