package com.huawei.deviceviewer.entity.user;

import com.huawei.deviceviewer.entity.EntityState;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/28
 * Email: sunyadi@huawei.com
 */
public class User {
    private int id;
    @NotBlank(message="{user.name}")
    private String name;
    @NotBlank(message="{user.username}")
    private String username;
    @NotBlank(message="{user.password}")
    private String password;

    private String salt = "";
    private int status = EntityState.NORMAL.value();

    public User(){}
    public User(String name, String username, String password) {
        this.name = name;
        this.password = password;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
