package com.huawei.deviceviewer.service.impl;

import com.huawei.deviceviewer.dao.UserDao;
import com.huawei.deviceviewer.entity.User;
import com.huawei.deviceviewer.exception.DuplicateAccountException;
import com.huawei.deviceviewer.exception.EmptyArgumentException;
import com.huawei.deviceviewer.exception.IncorrectCredentialsException;
import com.huawei.deviceviewer.exception.UnknownAccountException;
import com.huawei.deviceviewer.service.UserService;
import com.huawei.deviceviewer.utils.PasswordHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        validateUser(user);
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
    public void loginVerify(String username, String password) {
        if(StringUtils.isAnyBlank(username, password)){
            throw new EmptyArgumentException("用户名或密码不能为空");
        }
        validateUser(username, password);
    }

    public void validateUser(String username, String password) {
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

    public void validateUser(User user){
        if (StringUtils.isAnyBlank(user.getName(), user.getUsername(), user.getPassword())){
            throw new EmptyArgumentException("姓名、用户名和密码等不能为空！");
        }
        if(null != userDao.loadByUsername(user.getUsername())){
            throw new DuplicateAccountException();
        }
    }
}
