package com.wfm.platform.dao;

import com.wfm.platform.entities.SysFunAction;

public interface SysFunActionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysFunAction record);

    int insertSelective(SysFunAction record);

    SysFunAction selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysFunAction record);

    int updateByPrimaryKey(SysFunAction record);
}