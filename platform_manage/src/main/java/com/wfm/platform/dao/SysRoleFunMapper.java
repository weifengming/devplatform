package com.wfm.platform.dao;

import com.wfm.platform.entities.SysRoleFun;

public interface SysRoleFunMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRoleFun record);

    int insertSelective(SysRoleFun record);

    SysRoleFun selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRoleFun record);

    int updateByPrimaryKey(SysRoleFun record);
}