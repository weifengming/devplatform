package com.wfm.platform.service.impl;

import com.wfm.platform.dao.DatabaseDao;
import com.wfm.platform.dao.DatabaseFactory;
import com.wfm.platform.entities.*;
import com.wfm.platform.exception.DAOException;
import com.wfm.platform.service.DatabaseService;
import com.wfm.platform.vo.ConnectParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Weifengming
 * @description 数据库元信息查询服务
 * @date 2020/2/29
 */
public class DatabaseServiceImpl implements DatabaseService {

    @Override
    public List<Map<String, String>> query(ConnectParam connectParam, String sql, String[] params) {
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        if (connectParam == null) {
            return maps;
        }
        try {
            DatabaseDao dao = DatabaseFactory.getDAO(connectParam);
            long start = System.currentTimeMillis();
            dao.openConnection();
            maps = dao.query(sql, params);
            dao.closeConnection();
            long end = System.currentTimeMillis();
            System.out.println("反向获取数据库表信息耗时：" + (end - start) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }

    @Override
    public List<Table> getTables(ConnectParam connectParam) {
        List<Table> tables = new ArrayList<Table>();
        if (connectParam == null) {
            return tables;
        }
        try {
            DatabaseDao dao = DatabaseFactory.getDAO(connectParam);
            long start = System.currentTimeMillis();
            dao.openConnection();
            tables = dao.getTables();
            dao.closeConnection();
            long end = System.currentTimeMillis();
            System.out.println("反向获取数据库表信息耗时：" + (end - start) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }

    @Override
    public List<Column> getColumns(ConnectParam connectParam, String tableName) {
        List<Column> columns = new ArrayList<>();
        if (connectParam == null) {
            return columns;
        }
        try {
            DatabaseDao dao = DatabaseFactory.getDAO(connectParam);
            dao.openConnection();
            columns = dao.getColumns(tableName);
            dao.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columns;
    }

    @Override
    public List<PrimaryKey> getPrimaryKeys(ConnectParam connectParam, String tableName) {
        List<PrimaryKey> primaryKeys = new ArrayList<>();
        if (connectParam == null) {
            return primaryKeys;
        }
        try {
            DatabaseDao dao = DatabaseFactory.getDAO(connectParam);
            dao.openConnection();
            primaryKeys = dao.getPrimaryKeys(tableName);
            dao.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return primaryKeys;
    }

    @Override
    public List<ForeignKey> getForeignKeys(ConnectParam connectParam, String tableName) {
        List<ForeignKey> foreignKeys = new ArrayList<>();
        if (connectParam == null) {
            return foreignKeys;
        }
        try {
            DatabaseDao dao = DatabaseFactory.getDAO(connectParam);
            dao.openConnection();
            foreignKeys = dao.getForeignKeys(tableName);
            dao.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foreignKeys;
    }

    @Override
    public List<Index> getIndexes(ConnectParam connectParam, String tableName) {
        List<Index> indexes = new ArrayList<>();
        if (connectParam == null) {
            return indexes;
        }
        try {
            DatabaseDao dao = DatabaseFactory.getDAO(connectParam);
            dao.openConnection();
            indexes = dao.getIndexes(tableName);
            dao.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indexes;
    }

    @Override
    public List<Trigger> getTriggers(ConnectParam connectParam, String tableName) {
        List<Trigger> trigger = new ArrayList<>();
        if (connectParam == null) {
            return trigger;
        }
        try {
            DatabaseDao dao = DatabaseFactory.getDAO(connectParam);
            dao.openConnection();
            trigger = dao.getTriggers(tableName);
            dao.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trigger;
    }

    @Override
    public boolean canConnect(ConnectParam connectParam) {
        DatabaseDao dao = DatabaseFactory.getDAO(connectParam);
        if (dao == null) {
            return false;
        }
        try {
            dao.openConnection();
            System.out.println("数据库连接成功!");
            return true;
        } catch (Exception e) {
            System.out.println("数据库连接失败,请检查端口号、用户名或密码 !");
        } finally {
            try {
                dao.closeConnection();
            } catch (DAOException e) {
            }
        }
        return false;
    }
}
