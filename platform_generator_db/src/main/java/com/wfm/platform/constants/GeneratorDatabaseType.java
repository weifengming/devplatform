package com.wfm.platform.constants;

import com.wfm.platform.converter.CommonMetaDataConverter;
import com.wfm.platform.converter.MySQL5MetaDataConverter;
import com.wfm.platform.converter.OracleMetaDataConverter;
import com.wfm.platform.dao.MetaDataConverter;

/**
 * @author Weifengming
 * @description 数据库类型
 * @date 2020/2/28
 */
public enum GeneratorDatabaseType {
    Oracle {
        @Override
        public String getFileName() {
            return FOLDER + "/Oracle.xml";
        }

        @Override
        public MetaDataConverter getConverter() {
            return OracleMetaDataConverter.getInstance();
        }

    },

    MySql5 {
        @Override
        public String getFileName() {
            return FOLDER + "/MySQL5.xml";
        }

        @Override
        public MetaDataConverter getConverter() {
            return MySQL5MetaDataConverter.getInstance();
        }
    },

    MSSQLServer {
        @Override
        public String getFileName() {
            return FOLDER + "/MSSQL.xml";
        }
    };

    private static final String FOLDER =
            GeneratorDatabaseType.class.getPackage().getName().replace('.', '/');

    abstract public String getFileName();

    public MetaDataConverter getConverter() {
        return CommonMetaDataConverter.getInstance();
    }

}
