package com.huawei.deviceviewer.dao;

import com.huawei.deviceviewer.config.AbstractTestConfig;
import com.huawei.deviceviewer.entity.log.Log;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/19
 * Email: sunyadi@huawei.com
 */
public class LogDaoTest extends AbstractTestConfig {

    @Autowired
    private LogDao logDao;

    @Test
    public void  testInsert(){
        for(int i = 0; i < 15; i++){
            Log log = new Log(i + 1, "s00423985", 1, "2017-08-19 21:26", "2017-08-19 23:00");
            logDao.insert(log);
        }
    }

    @Test
    public void testLoadByDeviceId(){
        List<Log> logList = logDao.loadByDeviceId(1, 10);
    }
}
