package com.product.helpshopping.thridpart.update;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/1/27 0027.
 */
public class UpdateProxy {
    private static final String TAG = UpdateProxy.class.getSimpleName();
    private static UpdateProxy ourInstance = new UpdateProxy();
    private static Context sCtx;

    public static UpdateProxy getInstance() {
        return ourInstance;
    }

    private UpdateProxy() {
    }

    public void init(Context context) {
        if (!(context instanceof Application)) {
            throw new AssertionError();
        }
        // sCtx = context;
        // UmengUpdateAgent.setUpdateOnlyWifi(false);
    }

//    public void update(Context context) {
//
//        UmengUpdateAgent.setDownloadListener(new UmengDownloadListener() {
//            @Override
//            public void OnDownloadStart() {
//                LogUtils.i(TAG, "OnDownloadStart");
//            }
//
//            @Override
//            public void OnDownloadUpdate(int i) {
//                LogUtils.i(TAG, "OnDownloadUpdate i =" + i);
//            }
//
//            @Override
//            public void OnDownloadEnd(int i, String s) {
//                LogUtils.i(TAG, "OnDownloadUpdate i =" + i + " ; s = " + s);
//            }
//        });
//
//        UmengUpdateAgent.update(context);
//    }
}
