package com.product.helpshopping.ui.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.product.common.utils.LogUtils;
import com.product.helpshopping.BaseApplication;
import com.product.helpshopping.R;
import com.product.helpshopping.thridpart.push.PushProxy;
import com.product.helpshopping.ui.base.HelpBaseActivity;
import com.product.helpshopping.ui.fragment.CategoryFragment;
import com.product.helpshopping.ui.fragment.GuideFragment;
import com.product.helpshopping.ui.fragment.HomeFragment;
import com.product.helpshopping.ui.fragment.PersonalCenterFragment;
import com.product.helpshopping.utils.CommonUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends HelpBaseActivity implements TabHost.OnTabChangeListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_main);
        ButterKnife.bind(this);

        /** 初始化文件夹等环境 */
        CommonUtils.initAppEnvironment();
        /** 应用升级 */
        // UpdateProxy.getInstance().update(this);
        /** 百度push */
        PushProxy.getInstance().startWork(this);

        initView();
        initTab();
    }

    @Override
    public void onResume() {
        super.onResume();
        // MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }

    private void initView() {
        mTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.contentLayout);
        mTabhost.getTabWidget().setDividerDrawable(null);
        mTabhost.setOnTabChangedListener(this);
    }

    private void initTab() {
        TabSpec tabSpec = null;

        tabSpec = mTabhost.newTabSpec(getString(R.string.label_home))
                .setIndicator(getTabView(R.string.label_home, R.drawable.selector_tab_home));
        mTabhost.addTab(tabSpec, HomeFragment.class, null);
        mTabhost.setTag(getString(R.string.label_home));

        tabSpec = mTabhost.newTabSpec(getString(R.string.label_category))
                .setIndicator(getTabView(R.string.label_category, R.drawable.selector_tab_category));
        mTabhost.addTab(tabSpec, CategoryFragment.class, null);
        mTabhost.setTag(getString(R.string.label_category));

        tabSpec = mTabhost.newTabSpec(getString(R.string.label_guide))
                .setIndicator(getTabView(R.string.label_guide, R.drawable.selector_tab_guide));
        mTabhost.addTab(tabSpec, GuideFragment.class, null);
        mTabhost.setTag(getString(R.string.label_guide));

        tabSpec = mTabhost.newTabSpec(getString(R.string.label_personal_center))
                .setIndicator(getTabView(R.string.label_personal_center, R.drawable.selector_tab_personl));
        mTabhost.addTab(tabSpec, PersonalCenterFragment.class, null);
        mTabhost.setTag(getString(R.string.label_personal_center));
    }

    private View getTabView(int contentId, int iconId) {
        View view = LayoutInflater.from(this).inflate(R.layout.include_footer_tabs, null);
        ((TextView) view.findViewById(R.id.txt_tab)).setText(contentId);
        ((ImageView) view.findViewById(R.id.iv_icon)).setImageResource(iconId);
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        // TODO Auto-generated method stub
        LogUtils.i(TAG, "onTabChanged tabId = " + tabId);
        MobclickAgent.onEvent(this, "click", tabId);
    }

    // 时间间隔
    private static final long EXIT_INTERVAL = 2000L;
    // 需要监听几次点击事件数组的长度就为几
    // 如果要监听双击事件则数组长度为2，如果要监听3次连续点击事件则数组长度为3...
    long[] mHints = new long[2];

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 将mHints数组内的所有元素左移一个位置
            System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
            // 获得当前系统已经启动的时间
            mHints[mHints.length - 1] = SystemClock.uptimeMillis();
            if ((SystemClock.uptimeMillis() - mHints[0]) > EXIT_INTERVAL) {
                showToast(R.string.common_exit_app);
            } else {
                finish();
                ((BaseApplication) getApplicationContext()).exitApp(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
