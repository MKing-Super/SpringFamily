package com.mk.controller;

import com.aliyuncs.exceptions.ClientException;
import com.mk.po.User;
import com.mk.service.UserService;
import com.mk.util.MD5Util;
import com.mk.util.createUUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    //验证码
    private int checkcode;
    //去注册
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String toregister(){
        return "login/register";
    }
    //获取验证码
    @RequestMapping(value = "/code",method = RequestMethod.POST)
    @ResponseBody
    public int code(String mobile_phone) throws ClientException {
        checkcode = (int) ((Math.random() * 9 + 1) * 100000);
        //发送手机验证码，需要打开下面的注释，在util包下的SmsDemo类下填写阿里云的accessKeyId和accessKeySecret
        //SmsDemo.sendSms(mobile_phone,checkcode);
        System.out.println(checkcode);
        return  checkcode;
    }
    //注册中
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public boolean register(String mobile_phone,int code,HttpSession session){
        System.out.println(code);
        if (checkcode == code){     //验证码吻合
            User user = new User();
            //使用UUID作为主键
            user.setUserid(createUUID.getUUID());
            //设置新用户手机号
            user.setMobile_phone(mobile_phone);
            System.out.println(user);
            int register = userService.register(user);
            if (register>0){    //添加成功
                //设置session
                session.setAttribute("ALTER_USER",user);
                return true;
            }else {             //添加失败
                return false;
            }
        }else {                 //验证码不对
            return false;
        }
    }




    //注册完成后立即修改用户的相关信息
    //跳转到修改用户信息的界面
    @RequestMapping(value = "/alterUser",method = RequestMethod.GET)
    public String toalterUser(){
        return "login/alterUser";
    }
    //修改用户信息
    @RequestMapping(value = "/alterUser",method = RequestMethod.POST)
    @ResponseBody
    public boolean alterUser(String username,String password,HttpSession session){
        User user = (User) session.getAttribute("ALTER_USER");

        user.setUsername(username);
        //使用MD5对密码加密
        String md5password = MD5Util.makePassword(password);
        user.setPassword(md5password);
        System.out.println(user);
        //修改用户信息
        int register = userService.alterUser(user);
        if (register>0){    //添加成功
            //销毁session对象
            session.removeAttribute("ALTER_USER");
            return true;
        }else {             //添加失败
            return false;
        }


    }

}
