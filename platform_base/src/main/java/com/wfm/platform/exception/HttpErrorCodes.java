package com.wfm.platform.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Weifengming
 * @description 错误编码
 * @date 2020/2/17
 */
public enum HttpErrorCodes {

    COMMON(Integer.valueOf(500), "通用错误"),
    REUIRED(Integer.valueOf(1000), "必填");

    private Integer code;
    private String description;

    private HttpErrorCodes(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer code() {
        return this.code;
    }

    public String description() {
        return this.description;
    }

    public static Map<Integer, String> getHttpStatusTypes() {
        Map map = new HashMap();
        for (HttpErrorCodes e : values()) {
            map.put(e.code(), e.description());
        }
        return map;
    }
}
