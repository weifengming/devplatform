package com.wfm.platform.dao.impl;

import com.wfm.platform.dao.DatabaseDao;
import com.wfm.platform.dao.MetaDataConverter;
import com.wfm.platform.entities.*;
import com.wfm.platform.exception.DAOException;
import com.wfm.platform.util.StringHelper;
import com.wfm.platform.vo.ConnectParam;

import java.sql.*;
import java.util.*;

/**
 * @author Weifengming
 * @description 抽象元数据查询类
 * @date 2020/2/28
 */
public abstract class AbstractDatabaseDaoImpl implements DatabaseDao {
    protected MetaDataConverter converter;
    protected ConnectParam connectParam;

    private Connection connection;

    public AbstractDatabaseDaoImpl(ConnectParam connectParam) {
        this.connectParam = connectParam;
    }

    @Override
    public List<Map<String, String>> query(String sql, String[] params) throws DAOException {
        if (StringHelper.isEmpty(sql)) {
            Exception e = new IllegalAccessException("查询语句为空");
            throw new DAOException(DAOException.QUERY_EXCEPTION, e.getMessage(), e);
        }
        List<Map<String, String>> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            if (null != params && params.length > 0) {
                for (int paramIndex = 0; paramIndex < params.length; paramIndex++) {
                    statement.setString(paramIndex + 1, params[paramIndex]);
                }
            }
            resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, String> result_map = new HashMap<>(16);
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    String columnName = resultSetMetaData.getColumnLabel(columnIndex);
                    String columnValue = resultSet.getString(columnName);

                    result_map.put(columnName, columnValue);
                }
                result.add(result_map);
            }
        } catch (SQLException e) {
            throw new DAOException(DAOException.QUERY_EXCEPTION, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Table> getTables() throws DAOException {
        List<Table> tables = new ArrayList<>();
        /*try {

        } catch (DAOException e) {
            throw new DAOException(DAOException.QUERY_TABLE_EXCEPTION, e.getMessage(), e);
        }*/
        return tables;
    }

    @Override
    public List<Column> getColumns(String tableName) throws DAOException {
        return null;
    }

    @Override
    public List<PrimaryKey> getPrimaryKeys(String tableName) throws DAOException {
        return null;
    }

    @Override
    public List<ForeignKey> getForeignKeys(String tableName) throws DAOException {
        return null;
    }

    @Override
    public List<Index> getIndexes(String tableName) throws DAOException {
        return null;
    }

    @Override
    public List<Trigger> getTriggers(String tableName) throws DAOException {
        return null;
    }

    @Override
    public Connection openConnection() throws DAOException {
        return null;
    }

    @Override
    public void closeConnection() throws DAOException {

    }

    public void setConverter(MetaDataConverter converter) {
        this.converter = converter;
    }

    /**
     * 获取驱动类字符串描述值，抽象方法，由子类实现
     *
     * @return
     */
    abstract protected String getDriver() throws DAOException;

    /**
     * 获取要连接的数据库url，抽象方法，由子类实现
     *
     * @param host   数据库地址
     * @param port   数据库端口号
     * @param dbName 数据库实例名称
     * @return
     */
    abstract protected String getUrl(String host, int port, String dbName)
            throws DAOException;

    /**
     * 获取sql查询语句,由子类实现
     *
     * @param sqlKey
     * @return
     */
    abstract protected String getQuerySql(String sqlKey) throws DAOException;
}
