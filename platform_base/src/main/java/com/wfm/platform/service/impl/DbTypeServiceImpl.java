package com.wfm.platform.service.impl;

import com.wfm.platform.service.DbTypeService;

/**
 * @author Weifengming
 * @description 数据库类型接口实现类
 * @date 2020/2/21
 */
public class DbTypeServiceImpl implements DbTypeService {
    private String dbType;

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public DbTypeServiceImpl() {
    }

    public DbTypeServiceImpl(String type) {
        this.dbType = type;
    }

    public String getDbType() {
        return this.dbType;
    }
}
