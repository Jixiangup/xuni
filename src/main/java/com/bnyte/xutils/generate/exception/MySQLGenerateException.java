package com.bnyte.xutils.generate.exception;

/**
 * @auther bnyte
 * @date 2021-10-08 10:44
 * @email bnytezz@gmail.com
 */
public class MySQLGenerateException extends RuntimeException {

    public MySQLGenerateException() {
    }

    public MySQLGenerateException(String message) {
        super(message);
    }

    public MySQLGenerateException(String message, Throwable cause) {
        super(message, cause);
    }

    public MySQLGenerateException(Throwable cause) {
        super(cause);
    }

    public MySQLGenerateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
