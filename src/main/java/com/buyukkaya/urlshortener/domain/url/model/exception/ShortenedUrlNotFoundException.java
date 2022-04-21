package com.buyukkaya.urlshortener.domain.url.model.exception;

public class ShortenedUrlNotFoundException extends RuntimeException{

    public ShortenedUrlNotFoundException(String message) {
        super(message);
    }

    public ShortenedUrlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShortenedUrlNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ShortenedUrlNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
