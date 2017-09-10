package com.huawei.deviceviewer.dao;

import com.huawei.deviceviewer.config.AbstractTestConfig;
import com.huawei.deviceviewer.entity.user.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/30
 * Email: sunyadi@huawei.com
 */
public class UserDaoTest extends AbstractTestConfig {

    @Autowired
    private UserDao userDao;

    @Test
    public void  testInsert(){
        User user = new User("张三", "s00123456", "123456");
        int effectedLines = userDao.insert(user);
        Assert.assertEquals(1, effectedLines);
    }

    @Test
    public void testDelete(){
        User user = new User("李四", "s987654321", "123456");
        int effectedLines = userDao.insert(user);
        Assert.assertEquals(1, effectedLines);
        effectedLines = userDao.delete(user.getId());
        Assert.assertEquals(1, effectedLines);
    }

    @Test
    public void testUpdate(){
        User user = new User("李四", "s776655", "123456");
        int effectedLines = userDao.insert(user);
        Assert.assertEquals(1, effectedLines);
        user.setUsername("s556677");
        user.setPassword("55667788");
        userDao.update(user);
        user = userDao.loadByUsername(user.getUsername());
        Assert.assertNotNull(user);
    }

    @Test
    public void testLoadById(){
        User user = new User("李四", "s987654321", "123456");
        int effectedLines = userDao.insert(user);
        Assert.assertEquals(1, effectedLines);
        user = userDao.loadById(user.getId());
        Assert.assertNotNull(user);
    }

    @Test
    public void testLoadByUsername(){
        User user = new User("李四", "s987654321", "123456");
        int effectedLines = userDao.insert(user);
        Assert.assertEquals(1, effectedLines);
        user = userDao.loadByUsername(user.getUsername());
        Assert.assertNotNull(user);
    }

    public void testLoadAll(){
        List<User> userList = userDao.loadAll();
        Assert.assertFalse(userList.isEmpty());
    }
}
