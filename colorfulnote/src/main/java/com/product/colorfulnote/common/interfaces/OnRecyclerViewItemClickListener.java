package com.product.colorfulnote.common.interfaces;

import android.view.View;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public interface OnRecyclerViewItemClickListener {
    public void onItemClick(View view, /*int postion,*/ Object obj);

    public void onItemLongClick(View view, /*int postion,*/ Object obj);

    // public void onItemSubViewClick(View view, int postion, Object obj);
}
