package com.product.common.interfaces;

/**
 * 回调
 *
 * @param <T>
 */
public interface ICallback<T> {
    public void callback(T data);
}
