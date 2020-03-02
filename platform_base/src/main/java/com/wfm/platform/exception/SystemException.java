package com.wfm.platform.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Weifengming
 * @description 系统错误
 * @date 2020/2/17
 */
public class SystemException extends BaseException {
    public SystemException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    public SystemException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), message);
    }

    public SystemException(String message, String shortMessage) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), message, shortMessage);
    }

    public SystemException(String message, String shortMessage, String moreInfoUrl) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), message, shortMessage, moreInfoUrl);
    }
}
