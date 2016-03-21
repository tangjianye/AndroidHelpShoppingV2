package com.product.common.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.Stack;

/**
 * 页面管理类，主要维护页面堆栈，APP退出机制
 *
 * @author Peng Yi
 */
public class AppManager {
    private static Stack<Activity> mActivityStack;
    private static AppManager mAppManager;

    private AppManager() {

    }

    public static AppManager getInstance() {
        if (mAppManager == null) {
            mAppManager = new AppManager();
        }
        return mAppManager;
    }

    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
    }

    public Activity getTopActivity() {
        if (mActivityStack == null || mActivityStack.size() == 0)
            return null;
        Activity activity = mActivityStack.lastElement();
        return activity;
    }

    public void killTopActivity() {
        Activity activity = mActivityStack.lastElement();
        killActivity(activity);
    }

    public void killActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void killActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                killActivity(activity);
            }
        }
    }

    public void killAllActivity() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    public void appExit(Context context, boolean isKillProcess) {
        try {
            killAllActivity();
            if (isKillProcess) {
                System.exit(0);
            }
        } catch (Exception e) {
        }
    }

    public boolean resumeApp(Context context) {
        boolean isAppLive = false;
        Activity activity = getTopActivity();
        if (activity != null) {
            isAppLive = true;
            Intent intent = new Intent(context, activity.getClass());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        }
        return isAppLive;
    }
}
