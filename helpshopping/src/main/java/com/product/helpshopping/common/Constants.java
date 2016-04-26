package com.product.helpshopping.common;

/**
 * Created by Administrator on 2015/11/3 0003.
 */
public class Constants {
    /**
     * The default socket timeout in milliseconds
     */
    public static final int REQUEST_TIMEOUT_MS = 30 * 1000;

    /**
     * The default number of retries
     */
    public static final int REQUEST_MAX_RETRIES = 1;

    /**
     * The default request code
     */
    public static final int COMMON_REQUEST_CODE = 1001;

    /**
     * The default list page count
     */
    public static final int PAGE_COUNT = 5;

    /**
     * The default acoount uid
     */
    public static final int DEFAULT_UID = 9;

    /**
     * 渠道标志
     */
    public static final String CHANNEL = "UMENG_CHANNEL";

    /**
     * 百度PUSH
     */
    public static final String PUSH_KEY = "PUSH_KEY";

    /**
     * T卡文件夹
     */
    public static class Folder {
        // 根目录
        public static final String ROOT_PATH = "HelpShopping/";
    }

    /**
     * SP关键字
     */
    public static class SPKey {
        // 导航页
        public static final String GUIDE_KEY = "guide_key";
        // intent关键字
        public static final String INTENT_KEY = "intent_key";
        // bundle关键字
        public static final String BUNDLE_KEY = "bundle_key";
    }
}
