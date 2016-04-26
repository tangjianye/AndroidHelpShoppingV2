package com.product.colorfulnote.ui.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.product.colorfulnote.R;
import com.product.colorfulnote.ui.helper.ThemeHelper;
import com.product.common.ui.base.BaseActivity;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public abstract class NoteBaseActivity extends BaseActivity {
    private static final String TAG = NoteBaseActivity.class.getSimpleName();
    protected Toolbar mToolBar = null;
    private MaterialDialog mLoadingDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoadingDialog();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar();
    }


    public void showLoadingDialog() {
        if (null == mLoadingDialog) {
            // mLoadingDialog = new MaterialDialog.Builder(this).build();
            mLoadingDialog = new MaterialDialog.Builder(this)
                    // .title("刷新")
                    .content(R.string.common_loading_tips)
                    .progress(true, 0)
                    .show();
        }
    }

    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        mLoadingDialog = null;
    }

    private void initToolbar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
            mToolBar.setBackgroundResource(ThemeHelper.getInstance().getTitleBgColor());
            mToolBar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back);
            // mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }
}
