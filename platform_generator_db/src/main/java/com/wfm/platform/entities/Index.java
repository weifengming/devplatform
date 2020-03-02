package com.wfm.platform.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Weifengming
 * @description 索引
 * @date 2020/2/27
 */
public class Index implements Serializable {
    /**
     * 名称
     */
    private String name;
    /**
     * 所属类型
     */
    private String indexType;
    /**
     * 所属表名称
     */
    private String tableName;
    /**
     * 是否唯一索引
     */
    private boolean unique;
    /**
     * 字段列表
     */
    private List<String> cloumns = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public List<String> getCloumns() {
        return cloumns;
    }

    public void setCloumns(List<String> cloumns) {
        this.cloumns = cloumns;
    }
}
