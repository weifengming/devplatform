package com.wfm.platform.service;

import com.wfm.platform.entities.SysUserRole;

public interface SysUserRoleService {
    int deleteById(String id);

    int save(SysUserRole record);

    SysUserRole findById(String id);

    int update(SysUserRole record);
}