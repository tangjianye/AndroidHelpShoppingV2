package com.product.colorfulnote.thridpart.statistics;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/1/27 0027.
 */
public class StatisticsProxy {
    private static final String TAG = StatisticsProxy.class.getSimpleName();
    private static StatisticsProxy ourInstance = new StatisticsProxy();
    private static Context sCtx;

    public static StatisticsProxy getInstance() {
        return ourInstance;
    }

    private StatisticsProxy() {
    }

    public void init(Context context) {
        if (!(context instanceof Application)) {
            throw new AssertionError();
        }
        // sCtx = context;

        /** 友盟继承测试集成. */
        // String device = getDeviceInfo(context);
        // LogUtils.i(TAG, device);

        /** 使用集成测试模式请先在程序入口处调用如下代码，打开调试模式. */
        MobclickAgent.setDebugMode(true);

        /** 禁止默认的页面统计方式，这样将不会再自动统计Activity. */
        MobclickAgent.openActivityDurationTrack(false);

        /** 如不需要错误统计功能，可通过此方法关闭. */
        // MobclickAgent.setCatchUncaughtExceptions(false);

        /** 设置是否对日志信息进行加密, 默认false(不加密). */
        AnalyticsConfig.enableEncrypt(false);
    }


    /**
     * 友盟继承测试集成
     * {"device_id":"864527020392731","mac":"ac:38:70:24:99:69"}
     *
     * @param context
     * @return
     */
    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = tm.getDeviceId();
            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }
            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }
            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
