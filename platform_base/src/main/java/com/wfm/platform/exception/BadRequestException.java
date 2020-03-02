package com.wfm.platform.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Weifengming
 * @description 请求错误
 * @date 2020/2/17
 */
public class BadRequestException extends BaseException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, Integer.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, Integer.valueOf(HttpStatus.BAD_REQUEST.value()), message);
    }

    public BadRequestException(String message, String shortMessage) {
        super(HttpStatus.BAD_REQUEST, Integer.valueOf(HttpStatus.BAD_REQUEST.value()), message, shortMessage);
    }

    public BadRequestException(String message, String shortMessage, String moreInfoUrl) {
        super(HttpStatus.BAD_REQUEST, Integer.valueOf(HttpStatus.BAD_REQUEST.value()), message, shortMessage, moreInfoUrl);
    }
}
