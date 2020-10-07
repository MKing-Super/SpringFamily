package com.mk.interceptor;

import com.mk.po.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        //url为登陆，可以访问
        if(url.indexOf("/login")>=0){
            return true;
        }
        //url为注册，可以访问
        if(url.indexOf("/register")>=0){
            return true;
        }
//        //url为main主页面，可以访问
//        if (url.indexOf("/main")>=0){
//            return true;
//        }
        //判断session里有没有已登陆的用户
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        if (user != null){
            return true;
        }
        request.setAttribute("msg","您还没有登陆，请先登陆");
        request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
