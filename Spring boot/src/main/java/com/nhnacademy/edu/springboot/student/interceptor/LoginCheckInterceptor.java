package com.nhnacademy.edu.springboot.student.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean isLogin = request.getSession().getAttribute("sessionId") != null;
        if (isLogin) {
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }
}
