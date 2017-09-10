package com.huawei.deviceviewer.service;

import com.huawei.deviceviewer.entity.log.Log;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/20
 * Email: sunyadi@huawei.com
 */
public interface LogService {

    public void insertLog(Log log);

    public List<Log> loadByDeviceId(int deviceId, int limit);

    public List<Log> loadAll();

}
