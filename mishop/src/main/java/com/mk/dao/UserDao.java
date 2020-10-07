package com.mk.dao;

import com.mk.po.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    //注册用户
    public int register(User user);
    //登陆
    public User login(@Param("mobile_phone") String mobile_phone,@Param("password") String password);
    //修改用户信息
    public int alterUser(User user);
    //查询用户信息
    public User findUser(@Param("userid") String userid);
}
