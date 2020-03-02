package com.wfm.platform.service;

import com.wfm.platform.entities.SysRoleFun;

public interface SysRoleFunService {
    int deleteById(String id);

    int save(SysRoleFun record);

    SysRoleFun findById(String id);

    int update(SysRoleFun record);
}