package com.product.colorfulnote.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;
import com.product.colorfulnote.common.Constants;
import com.product.colorfulnote.module.fresco.FrescoManager;
import com.product.common.utils.AppUtils;
import com.product.common.utils.FileUtils;
import com.product.common.utils.LogUtils;
import com.product.common.utils.SDCardUtils;

import java.io.Serializable;


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

    /**
     * 设置图片显示
     *
     * @param view
     * @param uri
     */
    public static void loadImage(SimpleDraweeView view, String uri) {
        FrescoManager.getInstance().loadImage(view, uri);
    }

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
}
