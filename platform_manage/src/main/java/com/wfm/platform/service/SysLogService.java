package com.wfm.platform.service;

import com.wfm.platform.entities.SysLog;

public interface SysLogService {
    int deleteById(String id);

    int save(SysLog record);

    SysLog findById(String id);

    int update(SysLog record);
}