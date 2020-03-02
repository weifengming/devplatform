package com.wfm.platform.dao;

import com.wfm.platform.constants.GeneratorConstants;
import com.wfm.platform.constants.GeneratorDatabaseType;
import com.wfm.platform.dao.impl.CommonDatabaseDaoImpl;
import com.wfm.platform.dao.impl.MySql5DatabaseDaoImpl;
import com.wfm.platform.vo.ConnectParam;

/**
 * @author Weifengming
 * @description 查询器工厂
 * @date 2020/2/27
 */
public class DatabaseFactory {
    public static DatabaseDao getDAO(ConnectParam connectParam) {
        String upperCaseDbName = connectParam.getDbType().toUpperCase();

        if (upperCaseDbName.contains(GeneratorConstants.GENERATOR_ORACLE.code)) {
            return new CommonDatabaseDaoImpl(connectParam, GeneratorDatabaseType.Oracle);
        } else if (upperCaseDbName.contains(GeneratorConstants.GENERATOR_SQLSERVER.code) || upperCaseDbName.contains(GeneratorConstants.GENERATOR_SQL_SERVER.code)) {
            return new CommonDatabaseDaoImpl(connectParam, GeneratorDatabaseType.MSSQLServer);
        } else if (upperCaseDbName.contains(GeneratorConstants.GENERATOR_MYSQL.code)) {
            return new MySql5DatabaseDaoImpl(connectParam, GeneratorDatabaseType.MySql5);
        }
        return new MySql5DatabaseDaoImpl(connectParam, GeneratorDatabaseType.MySql5);
    }
}
