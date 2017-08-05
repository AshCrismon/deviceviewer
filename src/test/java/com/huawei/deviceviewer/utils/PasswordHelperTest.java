package com.huawei.deviceviewer.utils;

import com.huawei.deviceviewer.config.AbstractTestConfig;
import com.huawei.deviceviewer.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/28
 * Email: sunyadi@huawei.com
 */
public class PasswordHelperTest extends AbstractTestConfig{

    @Autowired
    private UserDao userDao;

    @Test
    public void testEncrypt() throws NoSuchAlgorithmException {

    }
}
