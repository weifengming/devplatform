package com.wfm.platform.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Weifengming
 * @description 无法找到
 * @date 2020/2/17
 */
public class NotFoundException extends BaseException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, Integer.valueOf(HttpStatus.NOT_FOUND.value()));
    }

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, Integer.valueOf(HttpStatus.NOT_FOUND.value()), message);
    }

    public NotFoundException(String message, String shortMessage) {
        super(HttpStatus.NOT_FOUND, Integer.valueOf(HttpStatus.NOT_FOUND.value()), message, shortMessage);
    }

    public NotFoundException(String message, String shortMessage, String moreInfoUrl) {
        super(HttpStatus.NOT_FOUND, Integer.valueOf(HttpStatus.NOT_FOUND.value()), message, shortMessage, moreInfoUrl);
    }
}
