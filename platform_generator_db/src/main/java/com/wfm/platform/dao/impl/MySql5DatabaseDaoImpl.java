package com.wfm.platform.dao.impl;

import com.wfm.platform.constants.GeneratorDatabaseType;
import com.wfm.platform.exception.DAOException;
import com.wfm.platform.vo.ConnectParam;

import java.util.List;
import java.util.Map;

/**
 * @author Weifengming
 * @description MYSQL数据库元信息查询类
 * @date 2020/2/29
 */
public class MySql5DatabaseDaoImpl extends CommonDatabaseDaoImpl {

    public MySql5DatabaseDaoImpl(ConnectParam connectParam, GeneratorDatabaseType databaseType) {
        super(connectParam, databaseType);
    }

    @Override
    protected String getQuerySql(String sqlKey) throws DAOException {
        return super.getQuerySql(sqlKey);
    }

    @Override
    public List<Map<String, String>> query(String sql, String[] params) throws DAOException {
        String[] realParams;
        if (params == null) {
            realParams = new String[]{connectParam.getDbName()};
        } else {
            realParams = new String[params.length + 1];
            realParams[0] = connectParam.getDbName();
            for (int i = 0; i < params.length; i++) {
                realParams[i + 1] = params[i];
            }
        }
        return super.query(sql, realParams);
    }
}
