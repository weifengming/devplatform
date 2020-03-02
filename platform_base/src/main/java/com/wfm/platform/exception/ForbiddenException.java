package com.wfm.platform.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Weifengming
 * @description 服务器禁止
 * @date 2020/2/17
 */
public class ForbiddenException extends BaseException {
    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, Integer.valueOf(HttpStatus.FORBIDDEN.value()));
    }

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, Integer.valueOf(HttpStatus.FORBIDDEN.value()), message);
    }

    public ForbiddenException(String message, String shortMessage) {
        super(HttpStatus.FORBIDDEN, Integer.valueOf(HttpStatus.FORBIDDEN.value()), message, shortMessage);
    }

    public ForbiddenException(String message, String shortMessage, String moreInfoUrl) {
        super(HttpStatus.FORBIDDEN, Integer.valueOf(HttpStatus.FORBIDDEN.value()), message, shortMessage, moreInfoUrl);
    }
}
