package com.buyukkaya.urlshortener.domain.url.model.exception;

public class AccessKeyExistException extends RuntimeException{

    public AccessKeyExistException(String message) {
        super(message);
    }

    public AccessKeyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessKeyExistException(Throwable cause) {
        super(cause);
    }

    protected AccessKeyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
