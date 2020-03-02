package com.wfm.platform.service;

import com.wfm.platform.entities.SysFun;

public interface SysFunService {
    int deleteById(String id);

    int save(SysFun record);

    SysFun findById(String id);

    int update(SysFun record);
}