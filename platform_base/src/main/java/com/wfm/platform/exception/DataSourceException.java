package com.wfm.platform.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Weifengming
 * @description 内部服务器错误
 * @date 2020/2/17
 */
public class DataSourceException extends BaseException {

    public DataSourceException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    public DataSourceException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), message);
    }

    public DataSourceException(String message, String shortMessage) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), message, shortMessage);
    }

    public DataSourceException(String message, String shortMessage, String moreInfoUrl) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), message, shortMessage, moreInfoUrl);
    }
}
