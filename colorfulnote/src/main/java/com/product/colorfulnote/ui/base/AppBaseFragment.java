package com.product.colorfulnote.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.product.common.ui.base.BaseFragment;


public abstract class AppBaseFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(this.getClass().getSimpleName());
//        MobclickAgent.onResume(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
//        MobclickAgent.onPause(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public AppBaseActivity getAppBaseActivity() {
        return (AppBaseActivity) getActivity();
    }
}
