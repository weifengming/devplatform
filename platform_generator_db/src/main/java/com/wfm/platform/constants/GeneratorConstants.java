package com.wfm.platform.constants;

/**
 * @author Weifengming
 * @description 代码生成器常量, 用于统一存放生成器可以配置的常量
 * @date 2020/2/28
 */
public enum GeneratorConstants {

    GENERATOR_DRIVER("driver", null, "数据库驱动"),
    GENERATOR_URL("url", null, "数据库连接"),
    GENERATOR_QUERY_TABLE("query_table", null, ""),
    GENERATOR_QUERY_COLUMN("query_column", null, ""),
    GENERATOR_QUERY_INDEX("query_index", null, ""),
    GENERATOR_QUERY_PRIMARY_KEY("query_primary_key", null, ""),
    GENERATOR_QUERY_FOREIGN_KEY("query_foreign_key", null, ""),
    GENERATOR_QUERY_TRIGGER("query_trigger", null, ""),
    /**
     * 加载xml的节点元素
     **/
    GENERATOR_ELEMENT_SELECT("select", null, "查询节点"),
    GENERATOR_ELEMENT_ATTRIBUTE_NAME("name", null, "节点名称"),
    GENERATOR_ELEMENT_DBMS("dbms", null, "元素"),
    GENERATOR_ATTRIBUTE_KEY("key", null, "元素"),
    GENERATOR_ATTRIBUTE_VALUE("value", null, "元素"),
    GENERATOR_ATTRIBUTE_HOST("host", null, "元素"),
    GENERATOR_ATTRIBUTE_PORT("port", null, "元素"),
    GENERATOR_ATTRIBUTE_DB("database", null, "元素"),
    GENERATOR_ATTRIBUTE_USER("userName", null, "元素"),
    GENERATOR_ATTRIBUTE_PASSWORD("password", null, "元素"),
    GENERATOR_ATTRIBUTE_DESE("desc", null, "元素"),
    /**
     * 数据库类型
     **/
    GENERATOR_ORACLE("ORACLE", null, "数据库类型"),
    GENERATOR_MYSQL("MYSQL", null, "数据库类型"),
    GENERATOR_DB2("DB2", null, "数据库类型"),
    GENERATOR_SQL_SERVER("SQL SERVER", null, "数据库类型"),
    GENERATOR_SQLSERVER("SQLSERVER", null, "数据库类型"),
    GENERATOR_SYBASE("SYBASE", null, "数据库类型"),
    GENERATOR_POSTGRE_SQL("POSTGRE_SQL", null, "数据库类型"),
    GENERATOR_HSQLDB("HSQLDB", null, "数据库类型"),
    GENERATOR_FIREBIRD("FIREBIRD", null, "数据库类型"),
    GENERATOR_DERBY("DERBY", null, "数据库类型"),
    GENERATOR_ELEMENT_DMBS_CONFIGURATIONS("dbmsConfigurations", null, "元素"),
    ;

    public final String code;
    public final String defaultValue;
    public final String desc;

    GeneratorConstants(String code, String defaultValue, String desc) {
        this.code = code;
        this.defaultValue = defaultValue;
        this.desc = desc;
    }
}
