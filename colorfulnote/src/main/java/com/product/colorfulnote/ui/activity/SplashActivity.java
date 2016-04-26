package com.product.colorfulnote.ui.activity;

import android.os.Bundle;
import android.view.ViewStub;

import com.product.colorfulnote.R;
import com.product.colorfulnote.common.Constants;
import com.product.colorfulnote.ui.base.NoteBaseActivity;
import com.product.colorfulnote.ui.layer.GuideLayer;
import com.product.colorfulnote.ui.layer.SplashLayer;
import com.product.common.manager.AppManager;
import com.product.common.utils.SPUtils;
import com.umeng.analytics.MobclickAgent;

public class SplashActivity extends NoteBaseActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private SplashLayer mSplashLayer = null;
    private GuideLayer mGuideLayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ShareSDK.initSDK(this);

        // 防止用户点击状态栏重新激活app
        boolean isAppLive = AppManager.getInstance().resumeApp(this);
        super.onCreate(savedInstanceState);
        if (isAppLive) {
            finish();
            return;
        }

        setContentView(R.layout.activity_splash);
        switchView(getGuideSwitch());
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ShareSDK.stopSDK(this);

        if (null != mSplashLayer) {
            mSplashLayer.destroy();
        }
        if (null != mGuideLayer) {
            mGuideLayer.destroy();
        }
    }

    /**
     * @return true 显示闪屏界面
     */
    private Boolean getGuideSwitch() {
        // return (Boolean) SPUtils.get(this, Constants.SPKey.GUIDE_KEY, false);
        return true;
    }

    private void switchView(Boolean havedShowGuide) {
        if (havedShowGuide) {
            ViewStub stub = (ViewStub) findViewById(R.id.vs_welcome);
            stub.inflate();
            mSplashLayer = (SplashLayer) findViewById(R.id.in_welcome);
            mSplashLayer.refresh(null);
        } else {
            ViewStub stub = (ViewStub) findViewById(R.id.vs_guide);
            stub.inflate();
            mGuideLayer = (GuideLayer) findViewById(R.id.in_guide);
            mGuideLayer.refresh(null);

            SPUtils.put(this, Constants.SPKey.GUIDE_KEY, true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
