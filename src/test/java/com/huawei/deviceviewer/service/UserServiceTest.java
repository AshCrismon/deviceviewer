package com.huawei.deviceviewer.service;

import com.huawei.deviceviewer.config.AbstractTestConfig;
import com.huawei.deviceviewer.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/5
 * Email: sunyadi@huawei.com
 */
public class UserServiceTest extends AbstractTestConfig {

    @Autowired
    private UserService userService;

    @Test
    public void testInsertUser(){
        User user = new User("Yadi Sun", "s00423985", "123456");
        userService.insertUser(user);
    }

    @Test
    public void testVerifyUser(){
        String username = "s00423985";
        String password = "123456";
        userService.loginVerify(username, password);
    }
}
