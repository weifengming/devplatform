package com.wfm.platform.service;

import com.wfm.platform.entities.SysUserToken;

public interface SysUserTokenService {
    int deleteById(String id);

    int save(SysUserToken record);

    SysUserToken findById(String id);

    int update(SysUserToken record);
}