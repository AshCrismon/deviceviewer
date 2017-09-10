package com.huawei.deviceviewer.dao;

import com.huawei.deviceviewer.entity.log.Log;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/19
 * Email: sunyadi@huawei.com
 */
public interface LogDao {

    public int insert(Log log);

    public List<Log> loadAll();

    public List<Log> loadPage(int start, int limit);

    public List<Log> loadByDeviceId(int deviceId, int limit);

}
