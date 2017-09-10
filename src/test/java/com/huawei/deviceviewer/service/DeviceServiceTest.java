package com.huawei.deviceviewer.service;

import com.huawei.deviceviewer.config.AbstractTestConfig;
import com.huawei.deviceviewer.entity.device.Device;
import com.huawei.deviceviewer.entity.device.DeviceState;
import com.huawei.deviceviewer.entity.common.Page;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Assert;
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
        Device device = new Device();
        device.setDeviceName("anonymous");
        device.setDeviceType("18000V1R1");
        device.setDeviceGroup("xve");
        device.setDeviceHostIPs("8.42.37.164");
        device.setControllerIPs("8.46.47.101/102");
        device.setHostAccount("root/huawei@123");
        device.setControllerIPs("admin/admin@123");
        deviceService.insertDevice(device);
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteDevice() throws InvalidArgumentException {
        Device device = new Device();
        device.setDeviceName("anonymous");
        device.setDeviceType("18000V1R1");
        device.setDeviceGroup("xve");
        device.setDeviceHostIPs("8.42.37.164");
        device.setControllerIPs("8.46.47.101/102");
        device.setHostAccount("root/huawei@123");
        device.setControllerIPs("admin/admin@123");
        deviceService.insertDevice(device);
        deviceService.deleteDevice(device.getId());
        deviceService.loadDeviceById(device.getId());
    }

    @Test
    public void testUpdateDevice() throws InvalidArgumentException {
        Device device = new Device();
        device.setDeviceName("anonymous");
        device.setDeviceType("18000V1R1");
        device.setDeviceGroup("xve");
        device.setDeviceHostIPs("8.42.37.164");
        device.setControllerIPs("8.46.47.101/102");
        device.setHostAccount("root/huawei@123");
        device.setControllerIPs("admin/admin@123");
        deviceService.insertDevice(device);

        device.setOccupancyState(DeviceState.OCCUPYING.value());
        device.setOccupier("s00423985");
        device.setBeginTime("2017-08-05 12:12:12");
        device.setEndTime("2017-08-05 21:12:12");
        deviceService.updateDevice(device);
    }

    @Test
    public void testLoadDeviceById(){
        Device device = new Device();
        device.setDeviceName("anonymous");
        device.setDeviceType("18000V1R1");
        device.setDeviceGroup("xve");
        device.setDeviceHostIPs("8.42.37.164");
        device.setControllerIPs("8.46.47.101/102");
        device.setHostAccount("root/huawei@123");
        device.setControllerIPs("admin/admin@123");
        deviceService.insertDevice(device);

        device = deviceService.loadDeviceById(device.getId());
        System.out.println("================== begin ==================");
        System.out.println(device);
        System.out.println("==================  end  ==================");
    }

    @Test
    public void testLoadAll(){
        List<Device> deviceList = deviceService.loadAllDevices();
        System.out.println("================== begin ==================");
        for(Device device : deviceList){
            System.out.println(device);
        }
        System.out.println("==================  end  ==================");
    }

    @Test
    public void testLoadDevicesByPage(){
        int deviceId = 1;
        int pageNo = 1;
        int pageSize = 10;
        Page<Device> devicePage = deviceService.loadDevicesByPage(pageNo, pageSize);
        System.out.println("================== begin ==================");
        System.out.println(devicePage);
        System.out.println("==================  end  ==================");
    }

    @Test
    public void testApplyDevice(){
        Device device = new Device();
        device.setDeviceName("anonymous");
        device.setDeviceType("18000V1R1");
        device.setDeviceGroup("xve");
        device.setDeviceHostIPs("8.42.37.164");
        device.setControllerIPs("8.46.47.101/102");
        device.setHostAccount("root/huawei@123");
        device.setControllerIPs("admin/admin@123");
        deviceService.insertDevice(device);

        String username = "s00423985";
        String beginTime = "2017-08-05 12:12:12";
        String endTime = "2017-08-05 21:12:12";
        deviceService.applyDevice(device.getId(), username, beginTime, endTime);
        device = deviceService.loadDeviceById(device.getId());

        Assert.assertEquals(DeviceState.OCCUPYING.value(), device.getOccupancyState());
        Assert.assertEquals(username, device.getOccupier());
        Assert.assertEquals(beginTime, device.getBeginTime());
        Assert.assertEquals(endTime, device.getEndTime());
    }

    @Test
    public void testReleaseDevice(){
        Device device = new Device();
        device.setDeviceName("anonymous");
        device.setDeviceType("18000V1R1");
        device.setDeviceGroup("xve");
        device.setDeviceHostIPs("8.42.37.164");
        device.setControllerIPs("8.46.47.101/102");
        device.setHostAccount("root/huawei@123");
        device.setControllerIPs("admin/admin@123");
        deviceService.insertDevice(device);

        String username = "s00423985";
        String beginTime = "2017-08-05 12:12:12";
        String endTime = "2017-08-05 21:12:12";
        deviceService.applyDevice(device.getId(), username, beginTime, endTime);
        deviceService.releaseDevice(device.getId(), username);
        device = deviceService.loadDeviceById(device.getId());

        Assert.assertEquals(DeviceState.FREE.value(), device.getOccupancyState());
        Assert.assertEquals("", device.getOccupier());
        Assert.assertEquals("", device.getBeginTime());
        Assert.assertEquals("", device.getEndTime());
    }
}
