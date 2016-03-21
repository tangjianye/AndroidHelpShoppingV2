package com.product.helpshopping;

import android.app.Application;

import com.product.common.manager.AppManager;
import com.product.common.utils.LogUtils;
import com.product.helpshopping.thridpart.statistics.StatisticsProxy;
import com.umeng.analytics.MobclickAgent;

public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        initStetho();
        init();
    }

    private void initStetho() {
//        Stetho.initialize(Stetho.newInitializerBuilder(this)
//                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                .build());

//        Stetho.initializeWithDefaults(this);
    }

    private void init() {
        LogUtils.init(BuildConfig.LOG_DEBUG, BuildConfig.APPLICATION_ID);
        // CrashException.getInstance().init(this);

//        DBManager.getInstance().init(this);
//        ImageLoaderManager.getInstance().init(this);
//        FrescoManager.getInstance().init(this);
//        VolleyManager.getInstance().init(this);
//        Notify.getInstance().init(this);
//
        StatisticsProxy.getInstance().init(this);
//        UpdateProxy.getInstance().init(this);
    }

    public void exitApp(boolean isKillProcess) {
        MobclickAgent.onKillProcess(this);
//        ImageLoaderManager.getInstance().shutDown();
//        FrescoManager.getInstance().shutDown();
//        VolleyManager.getInstance().shutDown();
        AppManager.getInstance().appExit(this, isKillProcess);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
