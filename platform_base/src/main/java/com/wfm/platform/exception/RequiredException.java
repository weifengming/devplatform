package com.wfm.platform.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Weifengming
 * @description TODO
 * @date 2020/2/17
 */
public class RequiredException extends BaseException {

    public RequiredException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, HttpErrorCodes.REUIRED.code(), message);
    }

    public RequiredException(String message, String shortMessage) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, HttpErrorCodes.REUIRED.code(), message, shortMessage);
    }

    public RequiredException(String message, String shortMessage, String moreInfoUrl) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, HttpErrorCodes.REUIRED.code(), message, shortMessage, moreInfoUrl);
    }
}
