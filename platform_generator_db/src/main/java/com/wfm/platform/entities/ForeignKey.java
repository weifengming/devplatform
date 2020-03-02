package com.wfm.platform.entities;

import java.io.Serializable;

/**
 * @author Weifengming
 * @description 外键
 * @date 2020/2/27
 */
public class ForeignKey implements Serializable {
    /**
     * 外键名称
     */
    private String fkName;
    /**
     * 主键表名
     */
    private String pkTableName;
    /**
     * 主键字段名
     */
    private String pkColumnName;
    /**
     * 外键表明
     */
    private String fkTableName;
    /**
     * 外键字段名
     */
    private String fkColumnName;

    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) {
        this.fkName = fkName;
    }

    public String getPkTableName() {
        return pkTableName;
    }

    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    public String getPkColumnName() {
        return pkColumnName;
    }

    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }

    public String getFkTableName() {
        return fkTableName;
    }

    public void setFkTableName(String fkTableName) {
        this.fkTableName = fkTableName;
    }

    public String getFkColumnName() {
        return fkColumnName;
    }

    public void setFkColumnName(String fkColumnName) {
        this.fkColumnName = fkColumnName;
    }
}
