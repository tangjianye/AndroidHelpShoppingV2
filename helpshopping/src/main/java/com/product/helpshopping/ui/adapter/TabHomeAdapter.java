package com.product.helpshopping.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.product.helpshopping.R;
import com.product.helpshopping.common.interfaces.OnRecyclerViewItemClickListener;
import com.product.helpshopping.module.net.response.HomeItem;
import com.product.helpshopping.utils.CommonUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class TabHomeAdapter extends RecyclerView.Adapter<TabHomeAdapter.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<HomeItem> mDataset;
    private OnRecyclerViewItemClickListener mItemClickListener;

    public TabHomeAdapter(Context context, ArrayList<HomeItem> dataset) {
        this.mContext = context;
        this.mInflater = ((Activity) context).getLayoutInflater();
        this.mDataset = dataset;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 注释掉的填充方式会导致match_parent无效
        // View view = View.inflate(parent.getContext(), R.layout.listitem_note, null);
        View view = mInflater.inflate(R.layout.listitem_tab_home, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        holder.itemView.setTag(mDataset.get(position));

        CommonUtils.loadImage(holder.ivTitle, mDataset.get(position).getCover());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_title)
        SimpleDraweeView ivTitle;
        @Bind(R.id.ly_card)
        CardView lyCard;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        if (null != mItemClickListener) {
            mItemClickListener.onItemClick(view, view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (null != mItemClickListener) {
            mItemClickListener.onItemLongClick(view, view.getTag());
        }
        return true;
    }
}
