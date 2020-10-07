package com.mk.service.impl;

import com.mk.mapper.SysUserMapper;
import com.mk.po.SysUser;
import com.mk.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    //查询所用的用户
    @Override
    public List<SysUser> queryList() {
        return sysUserMapper.queryList();
    }

    //通过id查询一个用户的信息
    @Override
    public SysUser querySysUserById(Integer id) {
        return sysUserMapper.querySysUserById(id);
    }
    //添加用户
    @Override
    public int addSysUser(SysUser sysUser) {
        return sysUserMapper.addSysUser(sysUser);
    }
    //修改用户
    @Override
    public int updateSysUser(SysUser sysUser) {
        return sysUserMapper.updateSysUser(sysUser);
    }
    //删除用户
    @Override
    public int deleteSysUser(Integer id) {
        return sysUserMapper.deleteSysUser(id);
    }

    //用户登陆
    @Override
    public SysUser querySysUserByUserid(String user_code,String user_password) {
        return sysUserMapper.querySysUserByUserid(user_code,user_password);
    }
}
