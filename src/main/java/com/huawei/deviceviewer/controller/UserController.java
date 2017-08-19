package com.huawei.deviceviewer.controller;

import com.huawei.deviceviewer.entity.User;
import com.huawei.deviceviewer.service.UserService;
import com.huawei.deviceviewer.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/28
 * Email: sunyadi@huawei.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final String SESSION_ID = "token";

    @Autowired
    private MessageVO message;
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    MessageVO login(@RequestParam(value = "username") String username,
                    @RequestParam(value = "password") String password,
                    HttpSession session) {
        userService.loginVerify(username, password);
        session.setAttribute(SESSION_ID, username);
        return renderMessage("登录成功！", username);
    }

    @RequestMapping("/logout")
    MessageVO logout(HttpSession session) {
        session.removeAttribute(SESSION_ID);
        return renderMessage("退出成功！", "");
    }

    @RequestMapping("/register")
    MessageVO register(@RequestParam(value = "name") String name,
                       @RequestParam(value = "username") String username,
                       @RequestParam(value = "password") String password,
                       HttpSession session) {

        User user = new User(name, username, password);
        userService.insertUser(user);
        session.setAttribute(SESSION_ID, username);
        return renderMessage("注册成功！", username);
    }

    @RequestMapping("/getToken")
    MessageVO getToken(HttpSession session) {
        return renderMessage("操作成功！", session.getAttribute(SESSION_ID));
    }

    private MessageVO renderMessage(String msg, Object token) {
        message.setStatusCode(HttpStatus.OK.value());
        message.setMsg(msg);
        message.setToken(token);
        return message;
    }
}
