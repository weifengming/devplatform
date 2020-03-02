package com.wfm.platform.service;

import com.wfm.platform.entities.SysFunAction;

public interface SysFunActionService {
    int deleteById(String id);

    int save(SysFunAction record);

    SysFunAction findById(String id);

    int update(SysFunAction record);
}