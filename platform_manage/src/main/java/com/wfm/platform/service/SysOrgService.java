package com.wfm.platform.service;

import com.wfm.platform.entities.SysOrg;

public interface SysOrgService {
    int deleteById(String id);

    int save(SysOrg record);

    SysOrg findById(String id);

    int update(SysOrg record);
}