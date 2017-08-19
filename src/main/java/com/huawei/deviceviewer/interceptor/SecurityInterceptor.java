package com.huawei.deviceviewer.interceptor;

import com.alibaba.fastjson.JSON;
import com.huawei.deviceviewer.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created by Intellij IDEA.
 * Author: Yadi.Sun
 * Date: 2017/7/30
 * Email: sunyadi@huawei.com
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private final String SESSION_ID = "token";

    @Autowired
    private MessageVO messageVO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean continueFilter = false;
        if (isAuthenticated(request.getSession())) {
            continueFilter = true;
        }

        if(!continueFilter){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(renderUnthenticateMessage()));
        }

        return continueFilter;
    }

    public boolean isAuthenticated(HttpSession session) {
        return session.getAttribute(SESSION_ID) != null;
    }

    private MessageVO renderUnthenticateMessage() {
        messageVO.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        messageVO.setMsg("未认证！");
        return messageVO;
    }
}
