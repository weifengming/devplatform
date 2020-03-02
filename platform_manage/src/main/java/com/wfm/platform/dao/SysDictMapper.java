package com.wfm.platform.dao;

import com.wfm.platform.entities.SysDict;

public interface SysDictMapper extends BaseDao<String, SysDict> {
    int deleteByPrimaryKey(String id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);
}