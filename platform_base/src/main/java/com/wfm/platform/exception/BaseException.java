package com.wfm.platform.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Weifengming
 * @description 异常基础类
 * @date 2020/2/17
 */
@Data
public class BaseException extends RuntimeException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private Integer code = HttpErrorCodes.COMMON.code();
    private String shortMessage = "应用程序异常";
    private String moreInfoUrl = "www.weifengming.com";

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(HttpStatus status, Integer code) {
        this.status = status;
        this.code = code;
    }

    public BaseException(HttpStatus status, Integer code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public BaseException(HttpStatus status, Integer code, String message, String shortMessage) {
        super(message);
        this.status = status;
        this.code = code;
        this.shortMessage = shortMessage;
    }

    public BaseException(HttpStatus status, Integer code, String message, String shortMessage, String moreInfoUrl) {
        super(message);
        this.status = status;
        this.code = code;
        this.shortMessage = shortMessage;
        this.moreInfoUrl = moreInfoUrl;
    }
}
