package com.product.helpshopping.module.net.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.product.common.utils.JsonUtils;
import com.product.common.utils.LogUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public class MaskRequest<T> extends Request<T> {
    private static final String TAG = MaskRequest.class.getSimpleName();
    private final Listener<T> mListener;
    private Type mType;

    public MaskRequest(int method, String url, Type type, Listener<T> listener,
                       ErrorListener errorListener) {
        super(method, url, errorListener);
        // setRetryPolicy(new DefaultRetryPolicy(
        //        Constants.REQUEST_TIMEOUT_MS, Constants.REQUEST_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        LogUtils.i(TAG, "url = " + url);

        mType = type;
        mListener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            LogUtils.i(TAG, "parseNetworkResponse jsonString = " + jsonString);

            T result = JsonUtils.parseJson(jsonString, mType);
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
