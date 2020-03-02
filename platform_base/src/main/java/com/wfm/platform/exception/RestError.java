package com.wfm.platform.exception;

import lombok.Data;

/**
 * @author Weifengming
 * @description TODO
 * @date 2020/2/17
 */
@Data
public class RestError {
    private int status;
    private int code;
    private String message;
    private String shortMessage;
    private String moreInfoUrl = "";
    private Boolean state = Boolean.valueOf(false);

    public RestError(BaseException baseException) {
        this.status = baseException.getStatus().value();
        this.code = baseException.getCode().intValue();
        this.shortMessage = baseException.getShortMessage();
        this.message = baseException.getMessage();
    }

    public RestError(int status, int code, String message, String shortMessage) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.shortMessage = shortMessage;
    }

    public RestError(int status, int code, String message, String shortMessage, String moreInfoUrl) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.shortMessage = shortMessage;
        this.moreInfoUrl = moreInfoUrl;
    }
}
