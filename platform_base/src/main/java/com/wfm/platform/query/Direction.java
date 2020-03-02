package com.wfm.platform.query;

import io.swagger.annotations.ApiModel;

/**
 * @author Weifengming
 * @description 排序对象
 * @date 2020/2/17
 */
@ApiModel(description = "排序对象")
public enum Direction {
    ASC,
    DESC;

    public static Direction fromString(String value) {
        try {
            return valueOf(value.toUpperCase());
        } catch (Exception e) {
        }
        return ASC;
    }
}
