package com.wfm.platform.service;

import com.wfm.platform.entities.*;
import com.wfm.platform.vo.ConnectParam;

import java.util.List;
import java.util.Map;

/**
 * @author Weifengming
 * @description 数据库元信息查询服务
 * @date 2020/2/29
 */
public interface DatabaseService {

    /**
     * 通用查询方法
     *
     * @param connParam 连接参数
     * @param sql       要查询的sql语句
     * @param params    查询条件数组
     * @return
     */
    List<Map<String, String>> query(ConnectParam connParam, String sql, String[] params);

    /**
     * 查询表集合
     *
     * @param connParam 连接参数
     * @return
     */
    List<Table> getTables(ConnectParam connParam);

    /**
     * 查询表的字段集
     *
     * @param connParam 连接参数
     * @param tableName
     * @return
     */
    List<Column> getColumns(ConnectParam connParam, String tableName);

    /**
     * 查询主键集
     *
     * @param connParam 连接参数
     * @param tableName
     * @return
     */
    List<PrimaryKey> getPrimaryKeys(ConnectParam connParam, String tableName);

    /**
     * 查询外键集
     *
     * @param connParam 连接参数
     * @param tableName
     * @return
     */
    List<ForeignKey> getForeignKeys(ConnectParam connParam, String tableName);

    /**
     * 查询索引集
     *
     * @param connParam 连接参数
     * @return
     */
    List<Index> getIndexes(ConnectParam connParam, String tableName);

    /**
     * 查询触发器集
     *
     * @param connParam 连接参数
     * @param tableName
     * @return
     */
    List<Trigger> getTriggers(ConnectParam connParam, String tableName);

    /**
     * 测试数据库是否可以连接
     *
     * @param connParam
     * @return
     */
    boolean canConnect(ConnectParam connParam);
}
