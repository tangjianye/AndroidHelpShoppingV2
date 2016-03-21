package com.product.helpshopping.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.product.common.utils.AppUtils;
import com.product.common.utils.FileUtils;
import com.product.common.utils.LogUtils;
import com.product.common.utils.SDCardUtils;
import com.product.helpshopping.R;
import com.product.helpshopping.common.Constants;

import java.io.Serializable;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2015/11/6 0006.
 */
public class CommonUtils {
    private static final String TAG = CommonUtils.class.getSimpleName();

    /**
     * common
     **********************************************************************************************/

    public static String getSdCard() {
        // String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        String sdcard = SDCardUtils.getSDCardPath();
        LogUtils.i(TAG, "sdcard = " + sdcard);
        return sdcard;
    }

    public static String getSdPath(String relPath) {
        String absolutePath = getSdCard() + relPath;
        LogUtils.i(TAG, "getSdPath = " + absolutePath);
        return absolutePath;
    }

    private static void makeFolders() {
        FileUtils.makeFolders(getSdPath(Constants.Folder.ROOT_PATH));
    }

    public static void initAppEnvironment() {
        makeFolders();
    }

    /**
     * private
     **********************************************************************************************/
    /**
     * 获取应用渠道ID
     *
     * @param context 上下文
     * @return
     */
    public static String getChannel(Context context) {
        String channel = AppUtils.getMetaValue(context, Constants.CHANNEL);
        LogUtils.i(TAG, "channel = " + channel);
        return channel;
    }
//
//    /**
//     * 设置图片显示
//     *
//     * @param view
//     * @param uri
//     */
//    @Deprecated
//    public static void loadImage(NetworkImageView view, String uri) {
//        // view.setDefaultImageResId(R.drawable.place_holder);
//        // view.setErrorImageResId(R.drawable.place_holder);
//        view.setImageUrl(API.getPicUrl(uri), VolleyManager.getInstance().getImageLoader());
//    }
//
//    /**
//     * 设置图片显示
//     *
//     * @param view
//     * @param uri
//     */
//    @Deprecated
//    public static void loadImage(ImageView view, String uri) {
//        ImageLoaderManager.getInstance().loadImage(view, API.getPicUrl(uri));
//    }
//
//    /**
//     * 设置图片显示
//     *
//     * @param view
//     * @param uri
//     */
//    public static void loadImage(SimpleDraweeView view, String uri) {
//        FrescoManager.getInstance().loadImage(view, API.getPicUrl(uri));
//    }

//    /**
//     * 设置广告图片显示
//     *
//     * @param view
//     * @param uri
//     */
//    public static void loadImage(Context context, SimpleDraweeView view, String uri) {
//        int width = ScreenUtils.getScreenWidth(context);
//        int height = context.getResources().getDimensionPixelOffset(R.dimen.size100);
//        FrescoManager.getInstance().loadImage(view, API.getPicUrl(uri), width, height);
//    }

    /**
     * 设置播放信息
     *
     * @param context
     * @param bundle
     * @param cls
     * @return
     */
    public static Intent getMaskIntent(Context context, Bundle bundle, Class<?> cls) {
        Intent intent = new Intent();
        // intent.putExtra(Constants.SPKey.INTENT_KEY, bundle);
        intent.putExtras(bundle);
        intent.setClass(context.getApplicationContext(), cls);
        return intent;
    }

    public static Intent getMaskIntent(Context context, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        return intent;
    }

    /**
     * 获取数据
     *
     * @param intent
     * @return
     */
    public static Serializable getMaskSerializable(Intent intent) {
        if (null != intent) {
            // Bundle bundle = intent.getBundleExtra(Constants.SPKey.INTENT_KEY);
            Bundle bundle = intent.getExtras();
            if (null != bundle) {
                return bundle.getSerializable(Constants.SPKey.BUNDLE_KEY);
            }
        }
        return null;
    }

    /**
     * 绑定数据
     *
     * @param value
     * @return
     */
    public static Bundle getMaskBundle(Serializable value) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.SPKey.BUNDLE_KEY, value);
        return bundle;
    }

//    /**
//     * 获取setting模块音乐默认选项
//     *
//     * @param context
//     * @return
//     */
//    public static String getMusicItemKey(Context context) {
//        String key = (String) SPUtils.get(context, Constants.SPKey.MUSIC_ITEM_KEY, Constants.Music.ITEM_01);
//        LogUtils.i(TAG, "getMusicItemKey key = " + key);
//        return key;
//    }
//
//    public static int getMusicId(Context context) {
//        return GlobalSetting.MUSIC_MAP.get(getMusicItemKey(context));
//    }

    /**
     * 分享
     *
     * @param context
     */
    public static void showShare(Context context) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(context.getString(R.string.label_share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(context);
    }
}
