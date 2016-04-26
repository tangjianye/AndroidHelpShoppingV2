package com.product.helpshopping.module.net.response;

/**
 * Created by Administrator on 2015/12/23 0023.
 */
public class MaskObjectSet<T> {
    private int errno;
    private String err;
    private T rsm;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public T getRsm() {
        return rsm;
    }

    public void setRsm(T rsm) {
        this.rsm = rsm;
    }

    @Override
    public String toString() {
        return "MaskObjectSet{" +
                "err='" + err + '\'' +
                ", errno=" + errno +
                ", rsm=" + rsm +
                '}';
    }
}
