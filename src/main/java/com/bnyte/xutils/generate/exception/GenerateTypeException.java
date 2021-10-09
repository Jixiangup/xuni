package com.bnyte.xutils.generate.exception;

/**
 * @auther bnyte
 * @date 2021-10-09 09:48
 * @email bnytezz@gmail.com
 */
public class GenerateTypeException extends RuntimeException {
    public GenerateTypeException() {
    }

    public GenerateTypeException(String message) {
        super(message);
    }

    public GenerateTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenerateTypeException(Throwable cause) {
        super(cause);
    }

    public GenerateTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
