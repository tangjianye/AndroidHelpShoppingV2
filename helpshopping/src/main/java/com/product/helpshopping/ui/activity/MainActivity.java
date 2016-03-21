package com.product.helpshopping.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.product.common.utils.LogUtils;
import com.product.helpshopping.R;
import com.product.helpshopping.ui.base.AppBaseActivity;
import com.product.helpshopping.ui.fragment.CategoryFragment;
import com.product.helpshopping.ui.fragment.GuideFragment;
import com.product.helpshopping.ui.fragment.HomeFragment;
import com.product.helpshopping.ui.fragment.PersonalCenterFragment;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppBaseActivity implements TabHost.OnTabChangeListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        ((TextView) view.findViewById(R.id.tvTab)).setText(contentId);
        ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(iconId);
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        // TODO Auto-generated method stub
        LogUtils.i(TAG, "onTabChanged tabId = " + tabId);
        MobclickAgent.onEvent(this, "click", tabId);
    }
}
