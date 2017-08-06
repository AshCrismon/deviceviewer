package com.huawei.deviceviewer.dao;

import com.huawei.deviceviewer.entity.Device;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/22
 * Email: sunyadi@huawei.com
 */
public interface DeviceDao {

	public int insert(Device device);

	public int delete(int id);

	public int update(Device device);

	public Device load(int id);

	public List<Device> loadAll();

	public List<Device> loadPage(int start, int limit);

	public int count();

}
