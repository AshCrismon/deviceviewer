package com.huawei.deviceviewer.controller;

import com.alibaba.fastjson.JSONObject;
import com.huawei.deviceviewer.exception.IncorrectCredentialsException;
import com.huawei.deviceviewer.exception.UnknownAccountException;
import com.huawei.deviceviewer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;
    private JSONObject message = new JSONObject();

    @RequestMapping("/login")
    String login(@RequestParam(value = "username") String username,
                 @RequestParam(value = "password") String password,
                 HttpSession session) {
        return loginVerify(username, password, session);
    }

    private String loginVerify(String username, String password, HttpSession session) {
        String status = "true";
        String msg = "";
        try {
            userService.verifyUser(username, password);
            session.setAttribute("sessionId", username);
        } catch (UnknownAccountException | IncorrectCredentialsException ex) {
            msg = ex.getMessage();
            status = "false";
        }
        return renderMessage(status, msg);
    }


    private String renderMessage(String status, String msg) {
        message.put("status", status);
        message.put("msg", msg);
        return message.toString();
    }
}
