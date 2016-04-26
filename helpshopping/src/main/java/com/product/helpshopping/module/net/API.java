package com.product.helpshopping.module.net;

import android.os.Environment;
import android.support.annotation.NonNull;

import com.product.common.utils.FileUtils;
import com.product.common.utils.LogUtils;

/**
 * Created by Administrator on 2015/11/19 0019.
 */
public class API {
    private static final String TAG = API.class.getSimpleName();

    /**
     * 后台api接口
     * 列表: http://mask.cloudsdee.com/?/api/shop/get_goods/?category_id=8&page=1
     * 广告: http://mask.cloudsdee.com/?/api/shop/get_goods/?category_id=7&page=1
     **********************************************************************************************/
    private static final String TEST_MODE = "/masktime0123456789";

    private static final String DOMAIN = "http://mask.cloudsdee.com/?";
    private static final String TEST_DOMAIN = "http://113.107.245.39:92?";

    private static final String GET_PICTURE = "http://mask.cloudsdee.com/uploads/kshop/";
    private static final String GET_GOODS = "/api/shop/get_goods/?";

    public static final String REGISTER = "/api/account/register_process/";
    public static final String LOGIN = "/api/account/login_process/";

    public static final int PAGE_BANNER = 1;
    public static final int CATEGORY_BANNER = 7;
    public static final int CATEGORY_CONTENT = 8;

    /**
     * 获取后台接口
     **********************************************************************************************/
    public static boolean isTestMode() {
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        return FileUtils.isFolderExist(sdcard + TEST_MODE);
    }

    private static String domain(boolean test) {
        return test ? TEST_DOMAIN : DOMAIN;
    }

    public static String getUrl(String urlPath) {
        return domain(isTestMode()) + urlPath;
    }

    public static String getPicUrl(String urlPath) {
        String url = GET_PICTURE + urlPath;
        LogUtils.i(TAG, "getPicUrl url = " + url);
        return url;
    }

    @NonNull
    public static String getCategoryUrl(int category, int page) {
        String url = getUrl(GET_GOODS) + "category_id=" + category + "&page=" + page;
        LogUtils.i(TAG, "getCategoryUrl url = " + url);
        return url;
    }
}
