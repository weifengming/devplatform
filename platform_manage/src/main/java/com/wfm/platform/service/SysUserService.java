package com.wfm.platform.service;

import com.wfm.platform.entities.SysUser;

public interface SysUserService extends Service<String, SysUser> {

    SysUser login(SysUser suser);
}