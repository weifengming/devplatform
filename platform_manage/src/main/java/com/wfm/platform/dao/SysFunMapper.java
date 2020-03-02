package com.wfm.platform.dao;

import com.wfm.platform.entities.SysFun;

public interface SysFunMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysFun record);

    int insertSelective(SysFun record);

    SysFun selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysFun record);

    int updateByPrimaryKey(SysFun record);
}