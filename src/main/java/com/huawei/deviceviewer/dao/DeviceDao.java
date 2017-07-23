package com.huawei.deviceviewer.dao;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/22
 * Email: sunyadi@huawei.com
 */
import com.huawei.deviceviewer.entity.Device;

import java.util.List;

public interface DeviceDao {

	public int add(Device device);

	public int delete(int id);

	public int update(Device device);

	public Device load(int id);

	public List<Device> loadAll();

}
