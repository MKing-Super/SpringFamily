package com.mk.service;

import com.mk.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface UserService {
    //注册
    public int register(User user);
    //登陆
    public User login(String mobile_phone,String password);
    //修改信息
    public int alterUser(User user);
    //查询用户信息
    public User findUser(String userid);
}
