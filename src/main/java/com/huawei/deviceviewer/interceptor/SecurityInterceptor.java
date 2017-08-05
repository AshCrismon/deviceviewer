package com.huawei.deviceviewer.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.huawei.deviceviewer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/30
 * Email: sunyadi@huawei.com
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    private JSONObject message = new JSONObject();

    private final String CONTEXT_PATH = "/deviceviewer";
    private final String LOGIN_REQUEST = "/user/login";
    private final String LOGIN_URL = "/views/login.html";
    private final String HOME_URL = "/views/index.html";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean continueFilter = true;
        if (isAuthenticated(request.getSession())) {
            if (isLoginUrl(request) || isLoginRequest(request)) {
                redirectTo(response, CONTEXT_PATH + HOME_URL);
                continueFilter = false;
            }
        } else if (!(isLoginUrl(request) || isLoginRequest(request))) {
            redirectTo(response, CONTEXT_PATH + LOGIN_URL);
            continueFilter = false;
        }
        return continueFilter;
    }

    public boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("sessionId") != null;
    }

    private boolean isLoginRequest(HttpServletRequest request) {
        return LOGIN_REQUEST.equals(getActionUrl(request));
    }

    private boolean isLoginUrl(HttpServletRequest request) {
        return LOGIN_URL.equals(getActionUrl(request));
    }

    private String getActionUrl(HttpServletRequest request){
        String contextPath = request.getContextPath();  // [/deviceviewer]
        String url = request.getRequestURL().toString();
        String actionUrl = url.substring(url.indexOf(contextPath) + contextPath.length());
        return actionUrl;
    }

    private void redirectTo(HttpServletResponse response, String url) throws IOException {
        response.sendRedirect(url);
    }

}
