package com.product.common.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 是否可以滚动ViewPager
 */
public class ScrollViewPager extends ViewPager {

    private boolean mScrollable = true;

    public ScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置viewpage是否可以滑动
     *
     * @param scrollable true 可以滑动
     *                   false 禁止滑动
     */
    public void setScrollAble(boolean scrollable) {
        this.mScrollable = scrollable;
    }

    public boolean getScrollAble() {
        return mScrollable;
    }

    @Override
    public void scrollTo(int x, int y) {
        if (mScrollable) {
            super.scrollTo(x, y);
        }
    }
}