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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //查询用户信息（目前没啥用，用不到）
    @RequestMapping(value = "/finduser")
    @ResponseBody
    public User findUser(String userid){
        User user = userService.findUser(userid);
        return  user;
    }
    //通过手机号，修改用户信息
    @RequestMapping(value = "/alteruser",method = RequestMethod.POST)
    @ResponseBody
    public boolean alterUser(User user, HttpSession session){
        //System.out.println(user);
        //使用MD5加密
        String password = MD5Util.makePassword(user.getPassword());
        user.setPassword(password);
        int i = userService.alterUser(user);
        if (i>0){   //修改成功，修改session的值
            session.setAttribute("USER_SESSION",user);
            return true;
        }else {
            return false;
        }
    }
}
