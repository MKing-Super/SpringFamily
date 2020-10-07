package com.mk.controller;

import com.mk.po.User;
import com.mk.service.UserService;
import com.mk.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private UserService userService;
    //验证码
    private int checkcode;

    //去登陆
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String tologin(){
        return "login/login";
    }
    //用户密码登陆中
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public boolean login(String mobile_phone, String password, HttpSession session){
        String md5password = MD5Util.makePassword(password);
        User user = userService.login(mobile_phone, md5password);

        if (user != null){
            session.setAttribute("USER_SESSION",user);
            return true;
        }else {
            return  false;
        }
    }
    //获取验证码
    @RequestMapping(value = "/getcode",method = RequestMethod.POST)
    @ResponseBody
    public int getcode(String mobile_phone){
        checkcode = (int) ((Math.random() * 9 + 1) * 100000);
        //发送手机验证码，需要打开下面的注释，在util包下的SmsDemo类下填写阿里云的accessKeyId和accessKeySecret
        //SmsDemo.sendSms(mobile_phone,checkcode);
        System.out.println(checkcode);
        return checkcode;
    }
    //手机验证码登陆
    @RequestMapping(value = "/logincode")
    @ResponseBody
    public boolean loginByCode(String mobile_phone,int code,HttpSession session){
        User user = userService.login(mobile_phone,null);
        if (checkcode == code){
            if (user != null){
                session.setAttribute("USER_SESSION",user);
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }
    //退出登陆
    @RequestMapping(value = "/logout")
    @ResponseBody
    public boolean logout(HttpSession session){
        //清除session
        session.invalidate();
        return true;
    }
}
