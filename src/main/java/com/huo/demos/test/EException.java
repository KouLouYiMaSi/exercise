package com.huo.demos.test;

public class EException extends Exception {

    public EException() {
        super();
    }

    public EException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EException(String message, Throwable cause) {
        super(message, cause);
    }

    public EException(String message) {
        super(message);
    }

}
