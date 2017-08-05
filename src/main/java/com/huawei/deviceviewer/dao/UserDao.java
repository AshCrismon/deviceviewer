package com.huawei.deviceviewer.dao;

import com.huawei.deviceviewer.entity.User;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/30
 * Email: sunyadi@huawei.com
 */
public interface UserDao {

    public int insert(User user);

    public int delete(int id);

    public int update(User user);

    public User loadById(int id);

    public User loadByUsername(String username);

    public List<User> loadAll();
}
