package com.product.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

@Deprecated
public class ToastUtils {
    private Toast mToast = null;

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    @SuppressLint("InflateParams")
    public void show(Context context, String text) {
        if (mToast == null) {
            mToast = new Toast(context);
        }
        mToast.setText(text);
        mToast.show();
    }

    public void show(Context context, int resId) {
        show(context, context.getString(resId));
    }

    public void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
