package com.huawei.deviceviewer.service.impl;

import com.huawei.deviceviewer.dao.UserDao;
import com.huawei.deviceviewer.entity.User;
import com.huawei.deviceviewer.exception.IncorrectCredentialsException;
import com.huawei.deviceviewer.exception.UnknownAccountException;
import com.huawei.deviceviewer.service.UserService;
import com.huawei.deviceviewer.utils.PasswordHelper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/8/5
 * Email: sunyadi@huawei.com
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public void insertUser(User user) {

        byte[] salt = PasswordHelper.createSalt();
        byte[] encryptedPassword = PasswordHelper.encrypt(user.getPassword(), salt);
        user.setSalt(Base64.encodeBase64String(salt));
        user.setPassword(Base64.encodeBase64String(encryptedPassword));
        userDao.insert(user);
    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User loadById(int id) {
        return userDao.loadById(id);
    }

    @Override
    public User loadByUsername(String username) {
        return userDao.loadByUsername(username);
    }

    @Override
    public List<User> loadAll() {
        return null;
    }

    @Override
    public void verifyUser(String username, String password) {
        User user = userDao.loadByUsername(username);
        if(null == user){
            throw new UnknownAccountException();
        }
        byte[] salt = Base64.decodeBase64(user.getSalt());
        String srcPassword = Base64.encodeBase64String(PasswordHelper.encrypt(password, salt));
        String targetPassword = user.getPassword();
        if(!srcPassword.equals(targetPassword)){
            throw new IncorrectCredentialsException();
        }
    }
}
