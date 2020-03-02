package com.wfm.platform.entities;

import java.io.Serializable;

/**
 * @author Weifengming
 * @description 存储过程
 * @date 2020/2/27
 */
public class Procedure implements Serializable {
    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 内容定义
     */
    private String definition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
