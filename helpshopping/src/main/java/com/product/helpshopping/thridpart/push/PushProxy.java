package com.product.helpshopping.thridpart.push;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.product.helpshopping.common.Constants;
import com.product.common.utils.AppUtils;


/**
 * Created by Administrator on 2016/1/27 0027.
 */
public class PushProxy {
    private static PushProxy ourInstance = new PushProxy();
    private static Context sCtx;

    public static PushProxy getInstance() {
        return ourInstance;
    }

    private PushProxy() {
    }

    public void init(Context context) {
        if (!(context instanceof Application)) {
            throw new AssertionError();
        }
        // sCtx = context;
    }

    public void startWork(Context context) {
        Resources resource = context.getResources();
        String pkgName = context.getPackageName();
        // Push: 以apikey的方式登录，一般放在主Activity的onCreate中。
        // 这里把apikey存放于manifest文件中，只是一种存放方式，
        // 您可以用自定义常量等其它方式实现，来替换参数中的Utils.getMetaValue(PushDemoActivity.this,
        // "api_key")
        // PushManager.startWork(context.getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "fMrUaMsir52oe70AnjPFkGGV");
        PushManager.startWork(context.getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,
                AppUtils.getMetaValue(context, Constants.PUSH_KEY));

        // Push: 如果想基于地理位置推送，可以打开支持地理位置的推送的开关
        // PushManager.enableLbs(context.getApplicationContext());

        // Push: 设置自定义的通知样式，具体API介绍见用户手册，如果想使用系统默认的可以不加这段代码
        // 请在通知推送界面中，高级设置->通知栏样式->自定义样式，选中并且填写值：1，
        // 与下方代码中 PushManager.setNotificationBuilder(this, 1, cBuilder)中的第二个参数对应
//        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
//                resource.getIdentifier("notification_custom_builder", "layout", pkgName),
//                resource.getIdentifier("notification_icon", "id", pkgName),
//                resource.getIdentifier("notification_title", "id", pkgName),
//                resource.getIdentifier("notification_text", "id", pkgName));
//        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
//        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
//        cBuilder.setStatusbarIcon(context.getApplicationInfo().icon);
//        cBuilder.setLayoutDrawable(resource.getIdentifier("simple_notification_icon", "drawable", pkgName));
//        cBuilder.setNotificationSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
//
//        // 推送高级设置，通知栏样式设置为下面的ID
//        PushManager.setNotificationBuilder(context, 1, cBuilder);
    }
}
