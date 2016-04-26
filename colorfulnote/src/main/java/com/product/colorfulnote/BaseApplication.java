package com.product.colorfulnote;

import android.app.Application;

import com.product.colorfulnote.db.DBManager;
import com.product.colorfulnote.exception.CrashException;
import com.product.colorfulnote.module.fresco.FrescoManager;
import com.product.colorfulnote.thridpart.statistics.StatisticsProxy;
import com.product.colorfulnote.thridpart.update.UpdateProxy;
import com.product.common.manager.AppManager;
import com.product.common.utils.LogUtils;
import com.umeng.analytics.MobclickAgent;

public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        // initStetho();
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
        LogUtils.init(BuildConfig.DEBUG, BuildConfig.APPLICATION_ID);
        CrashException.getInstance().init(this);
        DBManager.getInstance().init(this);
        FrescoManager.getInstance().init(this);
        // Notify.getInstance().init(this);
        StatisticsProxy.getInstance().init(this);
        UpdateProxy.getInstance().init(this);
    }

    public void exitApp(boolean isKillProcess) {
        MobclickAgent.onKillProcess(this);
        FrescoManager.getInstance().shutDown();
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
