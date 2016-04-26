package com.product.helpshopping.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.View;

import com.product.helpshopping.BaseApplication;
import com.product.helpshopping.R;
import com.product.helpshopping.common.Constants;
import com.product.helpshopping.thridpart.push.PushProxy;
import com.product.helpshopping.ui.base.HelpBaseActivity;
import com.product.helpshopping.ui.fragment.NavigationDrawerV2Fragment;
import com.product.helpshopping.ui.fragment.NoteListFragment;
import com.product.helpshopping.utils.CommonUtils;
import com.product.common.utils.LogUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

@Deprecated
public class MainActivity extends HelpBaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private NavigationDrawerV2Fragment mNavigationDrawerFragment;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /** 初始化文件夹等环境 */
        CommonUtils.initAppEnvironment();
        /** 应用升级 */
        // UpdateProxy.getInstance().update(this);
        /** 百度push */
        PushProxy.getInstance().startWork(this);

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void initViews() {
        setTitle(R.string.app_name);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, Fragment.instantiate(this, NoteListFragment.class.getName(), null))
                .commit();

        mNavigationDrawerFragment = (NavigationDrawerV2Fragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        // mNavigationDrawerFragment.setUp(mDrawerLayout, mToolBar);

        //创建返回键，并实现打开关/闭监听
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                MobclickAgent.onEvent(MainActivity.this, "click");
                MobclickAgent.onEvent(MainActivity.this, "click", "ActionDrawerOpened");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerToggle.syncState();
        mDrawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.i(TAG, "onActivityResult requestCode = " + requestCode + " ;resultCode = " + resultCode);
        if (Activity.RESULT_OK == resultCode) {
            if (Constants.COMMON_REQUEST_CODE == requestCode) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
                if (null != fragment) {
                    ((NoteListFragment) fragment).onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    private static final long EXIT_INTERVAL = 2000L;
    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > EXIT_INTERVAL) {
                showToast(R.string.common_exit_app);
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                ((BaseApplication) getApplicationContext()).exitApp(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
