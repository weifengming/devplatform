package com.wfm.platform.entities;

import java.io.Serializable;

/**
 * @author Weifengming
 * @description 主键
 * @date 2020/2/27
 */
public class PrimaryKey implements Serializable {
    /**
     * 名称
     */
    private String name;
    /**
     * 所属表名称
     */
    private String tableName;
    /**
     * 字段
     */
    private String cloumn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCloumn() {
        return cloumn;
    }

    public void setCloumn(String cloumn) {
        this.cloumn = cloumn;
    }
}
