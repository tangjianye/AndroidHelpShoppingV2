package com.product.helpshopping.module.net;

import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2016-4-26.
 */
public class ApiManager {
    private static final String TAG = ApiManager.class.getSimpleName();
    private static ApiManager ourInstance = new ApiManager();

    public interface IRequest<T> {
        public void onSuccess(T response);

        public void onFailure(VolleyError error);
    }

    public static ApiManager getInstance() {
        return ourInstance;
    }

    private ApiManager() {
    }
}
