package com.product.colorfulnote.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.TextView;

import com.product.colorfulnote.R;
import com.product.colorfulnote.ui.base.AppBaseActivity;
import com.product.colorfulnote.ui.helper.ThemeHelper;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class AboutActivity extends AppBaseActivity {
    private static final String TAG = AboutActivity.class.getSimpleName();

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.txt_about)
    TextView mTxtAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // SDK已经禁用了基于Activity 的页面统计，所以需要再次重新统计页面
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        // SDK已经禁用了基于Activity 的页面统计，所以需要再次重新统计页面
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }

    private void initView() {
        // mTxtAbout.setTextColor(ThemeHelper.getInstance().getItemBgColor());

//        // 设置还没收缩时状态下字体颜色
//        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.black));
//        // 设置收缩后Toolbar上字体的颜色
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        // 设置收缩后内容上的颜色
        mCollapsingToolbarLayout.setContentScrimColor(
                getResources().getColor(ThemeHelper.getInstance().getTitleBgColor()));

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_header);
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(final Palette palette) {
//                int defaultColor = ThemeHelper.getInstance().getTitleBgColor();
//                int defaultTitleColor = getResources().getColor(R.color.white);
//                int bgColor = palette.getDarkVibrantColor(defaultColor);
//                int titleColor = palette.getLightVibrantColor(defaultTitleColor);
//                mCollapsingToolbarLayout.setContentScrimColor(bgColor);
//                // mCollapsingToolbarLayout.setCollapsedTitleTextColor(titleColor);
//                // mCollapsingToolbarLayout.setExpandedTitleColor(titleColor);
//            }
//        });
    }
}
