package com.wfm.platform.dao;

import com.wfm.platform.entities.SysUser;

public interface SysUserMapper extends BaseDao<String, SysUser> {

    SysUser findByName(String loginname);
}