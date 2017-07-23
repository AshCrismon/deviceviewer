package com.huawei.deviceviewer.dao;

import com.huawei.deviceviewer.config.AbstractTestConfig;
import com.huawei.deviceviewer.entity.Device;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DeviceDaoTest extends AbstractTestConfig {

	@Autowired
	private DeviceDao deviceDao;

	@Test
	public void testAdd(){
		Device device = new Device(1, "anonymous");
		int effectedLines = deviceDao.add(device);
		Assert.assertEquals(1, effectedLines);
	}

	@Test
	public void testDelete(){
		Device device = new Device(2, "anonymous");
		deviceDao.add(device);
		int effectedLines = deviceDao.delete(device.getId());
		Assert.assertEquals(1, effectedLines);
	}

	@Test
	public void testUpdate(){
		Device device = new Device(1, "anonymous2");
		int effectedLines = deviceDao.update(device);
		Assert.assertEquals(1, effectedLines);
	}

	@Test
	public void testLoad(){
		int id = 1;
		Device device = deviceDao.load(1);
		Assert.assertEquals(id, device.getId());
	}

	@Test
	public void testLoadAll(){
		List<Device> devices = deviceDao.loadAll();
		Assert.assertNotNull(devices);
	}
	
}
