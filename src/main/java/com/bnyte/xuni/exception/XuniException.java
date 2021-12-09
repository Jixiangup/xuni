package com.bnyte.xuni.exception;

/**
 * @auther bnyte
 * @date 2021-12-09 19:20
 * @email bnytezz@gmail.com
 */
public class XuniException extends RuntimeException {
    public XuniException() {
    }

    public XuniException(String message) {
        super(message);
    }

    public XuniException(String message, Throwable cause) {
        super(message, cause);
    }

    public XuniException(Throwable cause) {
        super(cause);
    }

    public XuniException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
