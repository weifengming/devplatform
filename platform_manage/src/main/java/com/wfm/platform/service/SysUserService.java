package com.wfm.platform.service;

import com.wfm.platform.entities.SysUser;

public interface SysUserService extends Service<String, SysUser> {

    SysUser login(SysUser suser);

    void addUser(SysUser suser);

    SysUser getUserByLoginName(String loginname);

    void updateById(SysUser suser);

    void deleteById(String id);
}