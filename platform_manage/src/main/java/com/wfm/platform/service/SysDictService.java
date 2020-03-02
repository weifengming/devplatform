package com.wfm.platform.service;

import com.wfm.platform.entities.SysDict;

public interface SysDictService {
    int deleteById(String id);

    int save(SysDict record);

    SysDict findById(String id);

    int update(SysDict record);
}