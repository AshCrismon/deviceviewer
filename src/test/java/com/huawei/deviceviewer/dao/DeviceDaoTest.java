package com.huawei.deviceviewer.dao;

import com.huawei.deviceviewer.config.AbstractTestConfig;
import com.huawei.deviceviewer.entity.device.Device;
import com.huawei.deviceviewer.entity.device.DeviceState;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DeviceDaoTest extends AbstractTestConfig {

	@Autowired
	private DeviceDao deviceDao;

	@Test
	public void testInsert(){
		Device device = new Device();
		device.setDeviceName("anonymous");
		device.setDeviceType("18000V1R1");
		device.setDeviceGroup("xve");
		device.setDeviceHostIPs("8.42.37.164");
		device.setControllerIPs("8.46.47.101/102");
		device.setHostAccount("root/huawei@123");
		device.setControllerAccount("admin/admin@123");

		int effectedLines = deviceDao.insert(device);
		Assert.assertEquals(1, effectedLines);
	}

	@Test
	public void testDelete(){
		Device device = new Device();
		device.setDeviceName("anonymous");
		device.setDeviceType("18000V1R1");
		device.setDeviceGroup("xve");
		device.setDeviceHostIPs("8.42.37.164");
		device.setControllerIPs("8.46.47.101/102");
		device.setHostAccount("root/huawei@123");
		device.setControllerAccount("admin/admin@123");
		deviceDao.insert(device);
		int effectedLines = deviceDao.delete(device.getId());
		Assert.assertEquals(1, effectedLines);
	}

	@Test
	public void testUpdate(){
		Device device = new Device();
		device.setDeviceName("anonymous");
		device.setDeviceType("18000V1R1");
		device.setDeviceGroup("xve");
		device.setDeviceHostIPs("8.42.37.164");
		device.setControllerIPs("8.46.47.101/102");
		device.setHostAccount("root/huawei@123");
		device.setControllerAccount("admin/admin@123");
		deviceDao.insert(device);

		device.setBeginTime("2017-08-31 12:12");
		device.setEndTime("2017-09-01 12:12");
		device.setOccupier("s00423985");
		device.setOccupancyState(DeviceState.OCCUPYING.value());

		int effectedLines = deviceDao.update(device);
		Assert.assertEquals(1, effectedLines);
	}

	@Test
	public void testLoad(){
		Device device = new Device();
		device.setDeviceName("anonymous");
		device.setDeviceType("18000V1R1");
		device.setDeviceGroup("xve");
		device.setDeviceHostIPs("8.42.37.164");
		device.setControllerIPs("8.46.47.101/102");
		device.setHostAccount("root/huawei@123");
		device.setControllerAccount("admin/admin@123");
		deviceDao.insert(device);

		Device actualDevice = deviceDao.load(device.getId());
		Assert.assertEquals(device.getId(), actualDevice.getId());
	}

	@Test
	public void testLoadAll(){
		List<Device> devices = deviceDao.loadAll();
		Assert.assertNotNull(devices);
	}
	
}
