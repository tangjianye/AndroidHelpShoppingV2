package com.product.helpshopping.module.net.response;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/12/23 0023.
 */
public class MaskArraySet<T> {
    private int errno;
    private String err;
    private ArrayList<T> rsm;

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

    public ArrayList<T> getRsm() {
        return rsm;
    }

    public void setRsm(ArrayList<T> rsm) {
        this.rsm = rsm;
    }

    @Override
    public String toString() {
        return "MaskArraySet{" +
                "err='" + err + '\'' +
                ", errno=" + errno +
                ", rsm=" + ((null != rsm) ? rsm.size() : null) +
                '}';
    }
}
