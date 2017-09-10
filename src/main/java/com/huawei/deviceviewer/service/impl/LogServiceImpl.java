package com.huawei.deviceviewer.service.impl;

import com.huawei.deviceviewer.dao.LogDao;
import com.huawei.deviceviewer.entity.log.Log;
import com.huawei.deviceviewer.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/20
 * Email: sunyadi@huawei.com
 */
@Service
public class LogServiceImpl implements LogService{

    @Autowired
    private LogDao logDao;

    @Override
    public void insertLog(Log log) {
        logDao.insert(log);
    }

    @Override
    public List<Log> loadByDeviceId(int deviceId, int limit) {

        return logDao.loadByDeviceId(deviceId, limit);
    }

    @Override
    public List<Log> loadAll() {
        return logDao.loadAll();
    }

}
