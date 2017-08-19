package com.huawei.deviceviewer.service;

import com.huawei.deviceviewer.entity.User;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/30
 * Email: sunyadi@huawei.com
 */
public interface UserService {

    public void insertUser(User user);

    public void deleteUser(int userId);

    public void updateUser(User user);

    public User loadById(int id);

    public User loadByUsername(String username);

    public List<User> loadAll();

    public void loginVerify(String username, String password);

}
