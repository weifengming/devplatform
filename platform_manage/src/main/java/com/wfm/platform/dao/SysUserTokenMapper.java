package com.wfm.platform.dao;

import com.wfm.platform.entities.SysUserToken;

public interface SysUserTokenMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserToken record);

    int insertSelective(SysUserToken record);

    SysUserToken selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserToken record);

    int updateByPrimaryKey(SysUserToken record);
}