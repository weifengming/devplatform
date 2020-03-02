package com.wfm.platform.query;

import io.swagger.annotations.ApiModel;

/**
 * @author Weifengming
 * @description 字段间的组合关系
 * @date 2020/2/18
 */
@ApiModel(description = "字段间的组合关系")
public enum FieldRelation {
    AND("AND"),

    OR("OR");

    private String val;

    private FieldRelation(String _val) {
        this.val = _val;
    }

    public String value() {
        return this.val;
    }
}
