package com.product.helpshopping.module.net;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.product.common.utils.LogUtils;
import com.product.helpshopping.BaseApplication;
import com.product.helpshopping.common.Constants;
import com.squareup.okhttp.OkHttpClient;

/**
 * Volley网络通信单例管理类<br>
 * Created by tangjy on 2015/3/2.
 */
public class VolleyManager {
    private static final String TAG = VolleyManager.class.getSimpleName();

    private static Context sCtx;
    private static VolleyManager sINSTANTCE;
    private static RequestQueue sRequestQueue;
    private static ImageLoader sImageLoader;
    private static VolleyMemoryCache sMemoryCache;

    private VolleyManager() {
    }

    public static synchronized VolleyManager getInstance() {
        if (sINSTANTCE == null) {
            sINSTANTCE = new VolleyManager();
        }
        return sINSTANTCE;
    }

    public void init(Context context) {
        if (!(context instanceof BaseApplication)) {
            throw new AssertionError();
        }

        sCtx = context;
        if (sRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            sRequestQueue = Volley.newRequestQueue(sCtx.getApplicationContext(), new OkHttpStack(new OkHttpClient()));
        }
//        if (sMemoryCache == null) {
//            sMemoryCache = new VolleyMemoryCache(ConfigConstants.MAX_MEMORY_CACHE_SIZE);
//        }
//        if (sImageLoader == null) {
//            sImageLoader = new ImageLoader(sRequestQueue, sMemoryCache);
//        }
    }

    @NonNull
    private DefaultRetryPolicy getRetryPolicy() {
        return new DefaultRetryPolicy(Constants.REQUEST_TIMEOUT_MS,
                Constants.REQUEST_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public <T> RequestQueue addToRequestQueue(Request<T> req, String tag) {
        req.setRetryPolicy(getRetryPolicy());
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        LogUtils.i(TAG, "Adding request to queue = " + req.getUrl());
        sRequestQueue.add(req);
        return sRequestQueue;
    }

    public void cancelAll(String tag) {
        if (sRequestQueue != null) {
            sRequestQueue.cancelAll(tag);
        }
    }

    @Deprecated
    public RequestQueue getRequestQueue() {
        return sRequestQueue;
    }

    @Deprecated
    public ImageLoader getImageLoader() {
        return sImageLoader;
    }

    /**
     * 释放图片资源
     */
    public void shutDown() {
        if (null != sMemoryCache) {
            sMemoryCache.clear();
        }
    }
}
