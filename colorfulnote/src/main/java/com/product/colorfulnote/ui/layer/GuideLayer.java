package com.product.colorfulnote.ui.layer;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.product.colorfulnote.R;
import com.product.colorfulnote.common.interfaces.ICycle;
import com.product.colorfulnote.ui.activity.MainActivity;
import com.product.colorfulnote.ui.activity.SplashActivity;
import com.product.common.ui.base.BaseActivity;
import com.product.common.ui.view.ScrollViewPager;

import java.util.ArrayList;
import java.util.List;


public class GuideLayer extends RelativeLayout implements ICycle {
    // private Button mBtnEntry;
    private LinearLayout mIndicator;
    private ScrollViewPager mViewPager;
    private List<View> mBannerViewList;

    // private int mGuidePageResId[] = {R.drawable.guide_page_1, R.drawable.guide_page_2, R.drawable.guide_page_3};
    private int mGuidePageResId[] = {0, 1, 2};

    public GuideLayer(Context context) {
        super(context);
    }

    public GuideLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    public void updateData() {
        initData();
        initIndicator(getContext(), mIndicator);
        notifyAdapter();
    }

    private void notifyAdapter() {
        mViewPager.setAdapter(new GuidePagerAdapter(getContext(), mBannerViewList));
        mViewPager.setOnPageChangeListener(new GuidePageChangeListener());
        // mBtnEntry.setOnClickListener(mClickListener);
    }

    @Override
    public void refresh(Object obj) {
        updateData();
    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() {
        mIndicator = (LinearLayout) findViewById(R.id.ll_dot_group);
        mViewPager = (ScrollViewPager) findViewById(R.id.view_pager);
    }

    private void initData() {
        // 初始化引导页
        ImageView imageView;
        mBannerViewList = new ArrayList<>();
        for (int id : mGuidePageResId) {
            imageView = new ImageView(getContext());
            imageView.setImageResource(id);
            mBannerViewList.add(imageView);
        }
    }

    private void initIndicator(final Context context, ViewGroup layout) {
        // 初始化引导点indicator
        LinearLayout.LayoutParams params;
        for (int i = 0; i < mGuidePageResId.length; i++) {
            View dot = new View(getContext());
            dot.setBackgroundResource(R.drawable.selector_guide_indicator);
            params = new LinearLayout.LayoutParams(20, 20);
            params.leftMargin = 8;
            params.rightMargin = 8;
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            layout.addView(dot);
        }

        if (layout.getChildCount() != 0)
            layout.getChildAt(0).setEnabled(true);
    }

//    private void initView() {
//        // mBtnEntry = (Button) findViewById(R.id.btn_entry);
//        mIndicator = (LinearLayout) findViewById(R.id.ll_dot_group);
//        mViewPager = (ScrollViewPager) findViewById(R.id.view_pager);
//    }

//    private OnClickListener mClickListener = new OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if (R.id.btn_entry == v.getId()) {
//                WallpaperUtil.gotoActivity(getContext(),
//                        WallpaperActivity.class);
//                ((WelcomeAnimationActivity) getContext()).finish();
//                ((WelcomeAnimationActivity) getContext()).sendGuide(999);
//            }
//        }
//    };

    private class GuidePageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int pageIndex) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int pageIndex) {
            // setButtonEnabled(pageIndex);
            setIndicatorEnabled(pageIndex);
        }
    }

//    private void setButtonEnabled(int pos) {
//        if (mGuidePageResId.length - 1 == pos) {
//            mBtnEntry.setVisibility(View.VISIBLE);
//        } else {
//            mBtnEntry.setVisibility(View.GONE);
//        }
//    }

    private void setIndicatorEnabled(int pos) {
        for (int i = 0; i < mGuidePageResId.length; i++) {
            mIndicator.getChildAt(i).setEnabled(false);
        }
        mIndicator.getChildAt(pos).setEnabled(true);
    }

    private class GuidePagerAdapter extends PagerAdapter {
        private Context mContext;
        private List<View> mList;

        public GuidePagerAdapter(Context context, List<View> list) {
            this.mContext = context;
            this.mList = list;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        public View instantiateItem(View arg0, final int arg1) {
            ((ViewPager) arg0).addView(mList.get(arg1));
            if (arg1 == mList.size() - 1) {
                mList.get(arg1).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO Auto-generated method stub
                        ((BaseActivity) mContext).openActivity(MainActivity.class);
                        ((SplashActivity) mContext).finish();
                    }
                });
            }
            return mList.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }
    }
}
