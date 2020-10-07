package com.mk.service.impl;

import com.mk.dao.UserDao;
import com.mk.po.User;
import com.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    //注册
    @Override
    public int register(User user) {
        return userDao.register(user);
    }
    //登陆
    @Override
    public User login(String mobile_phone, String password) {
        return userDao.login(mobile_phone,password);
    }
    //修改信息
    @Override
    public int alterUser(User user) {
        return userDao.alterUser(user);
    }
    //查询用户信息
    @Override
    public User findUser(String userid) {
        return userDao.findUser(userid);
    }
}
