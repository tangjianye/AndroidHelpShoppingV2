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
//        // 广告位
//        public static final String BUNDLE_URL = "bundle_url_key";
//        // 通知栏音乐
//        public static final String MUSIC_ITEM_KEY = "music_item_key";
//        // 用户UID
//        public static final String ACCOUNT_UID = "account_uid";
//        // 用户名字
//        public static final String ACCOUNT_NAME = "account_name";
//        // 用户密码
//        public static final String ACCOUNT_PASSWORD = "account_password";
//        // 用户头像
//        public static final String ACCOUNT_AVATAR_FILE = "account_avatar_file";
    }

//    /**
//     * 音乐项目
//     */
//    public static class Music {
//        public static final String ITEM_01 = "music_item_01";
//        public static final String ITEM_02 = "music_item_02";
//        public static final String ITEM_03 = "music_item_03";
//        public static final String ITEM_04 = "music_item_04";
//    }
//
//    /**
//     * 播放控制msg
//     */
//    public static class PlayerMsg {
//        /* 初始化状态 */
//        public static final int IDLE = 0;
//        /* 开始播放 */
//        public static final int PLAY = 1;
//        /* 暂停|开始播放 */
//        public static final int PAUSE = 2;
//        /* 下一首 */
//        public static final int NEXT = 4;
//        /* 上一首 */
//        public static final int PREV = 5;
//        /* 停止播放 */
//        public static final int STOP = 6;
//    }
}
