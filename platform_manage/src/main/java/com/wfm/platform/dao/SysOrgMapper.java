package com.wfm.platform.dao;

import com.wfm.platform.entities.SysOrg;

public interface SysOrgMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    SysOrg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);
}