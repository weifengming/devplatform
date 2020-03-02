package com.wfm.platform.service;

import com.wfm.platform.entities.SysMenu;

public interface SysMenuService {
    int deleteById(String id);

    int save(SysMenu record);

    SysMenu findById(String id);

    int update(SysMenu record);
}