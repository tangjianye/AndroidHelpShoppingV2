package com.product.colorfulnote.exception;


/**
 * 封装Exception的基类
 *
 * @author tangjy
 */
public class BaseException extends Exception {
    public BaseException(String detailMessage) {
        super(detailMessage);
    }

    public BaseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }
}
