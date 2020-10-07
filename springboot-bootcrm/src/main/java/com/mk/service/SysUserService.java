package com.mk.service;

import com.mk.po.SysUser;

import java.util.List;

public interface SysUserService {
    //查询所用的用户
    List<SysUser> queryList();
    //通过id查询一个用户的信息
    SysUser querySysUserById(Integer id);
    //添加用户
    int addSysUser(SysUser sysUser);
    //修改用户
    int updateSysUser(SysUser sysUser);
    //删除用户
    int deleteSysUser(Integer id);

    //用户登陆
    SysUser querySysUserByUserid(String user_code,String user_password);
}
