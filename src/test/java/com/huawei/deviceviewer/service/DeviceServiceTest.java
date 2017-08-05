package com.huawei.deviceviewer.service;

import com.huawei.deviceviewer.config.AbstractTestConfig;
import com.huawei.deviceviewer.entity.Device;
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
    public void testInsertDevice(){
        Device device1 = new Device("18000V1R1", "anonymous", "8.46.47.101/102", 1, "s00423985");
        Device device2 = new Device("18000V1R1", "anonymous", "8.46.47.103/104", 1, "s00423985");
        Device device3 = new Device("18000V1R1", "anonymous", "8.46.47.194/195", 0, "");
        Device device4 = new Device("18000V1R1", "anonymous", "8.46.47.132/133", 0, "");
        deviceService.insertDevice(device1);
        deviceService.insertDevice(device2);
        deviceService.insertDevice(device3);
        deviceService.insertDevice(device4);
    }

    @Test
    public void testLoadAll(){
        List<Device> deviceList = deviceService.loadAll();
        print("total records: " + deviceList.size());
        for(Device device : deviceList){
            print(
                    "Device IPs: " + device.getIps() +
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
}
