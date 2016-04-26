package com.product.helpshopping.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.product.helpshopping.ui.base.HelpBaseFragment;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class CategoryFragment extends HelpBaseFragment {
    private static final String TAG = CategoryFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        TextView view = new TextView(getActivity());
        view.setText(com.product.helpshopping.R.string.label_category);
        view.setTextColor(Color.parseColor("#ff0000"));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CommonUtils.showShare(getActivity());
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }
}
