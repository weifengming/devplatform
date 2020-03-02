package com.wfm.platform.service;

import com.wfm.platform.entities.SysRole;

public interface SysRoleService {
    int deleteById(String id);

    int save(SysRole record);

    SysRole findById(String id);

    int update(SysRole record);
}