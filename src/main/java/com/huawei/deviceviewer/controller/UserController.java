package com.huawei.deviceviewer.controller;

import com.huawei.deviceviewer.exception.IncorrectCredentialsException;
import com.huawei.deviceviewer.exception.UnknownAccountException;
import com.huawei.deviceviewer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Object> message = new HashMap<>();

    @RequestMapping("/login")
    Map<String, Object> login(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password,
                              HttpSession session) {
        return loginVerify(username, password, session);
    }

    @RequestMapping("/logout")
    Map<String, Object> logout(HttpSession session) {
        session.removeAttribute("sessionId");
        return renderMessage("true", "logout success!");
    }

    @RequestMapping("/getSession")
    Map<String, Object> getSession(HttpSession session) {
        return renderMessage("true", session.getAttribute("sessionId"));
    }

    private Map<String, Object> loginVerify(String username, String password, HttpSession session) {
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

    private Map<String, Object> renderMessage(String status, Object msg) {
        message.put("status", status);
        message.put("msg", msg);
        return message;
    }
}
