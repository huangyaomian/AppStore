package com.hym.appstore.common.exception;

public class BaseException extends Exception {
    private int code;
    private String displayMessage;

    public BaseException(int code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }
}
