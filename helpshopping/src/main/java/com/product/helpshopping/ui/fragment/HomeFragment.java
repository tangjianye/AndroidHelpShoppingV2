package com.product.helpshopping.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.product.common.utils.JsonUtils;
import com.product.common.utils.LogUtils;
import com.product.common.utils.ResourceUtils;
import com.product.helpshopping.BuildConfig;
import com.product.helpshopping.R;
import com.product.helpshopping.module.net.VolleyManager;
import com.product.helpshopping.module.net.request.MaskRequest;
import com.product.helpshopping.module.net.response.HomeItem;
import com.product.helpshopping.module.net.response.MaskArraySet;
import com.product.helpshopping.ui.adapter.TabHomeAdapter;
import com.product.helpshopping.ui.base.HelpBaseFragment;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class HomeFragment extends HelpBaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private static final long DELAY = 1000;
    private static final int INIT_COUNT = 5;
    private static final int PAGE_COUNT = 5;
    private static final int PULL_DOWN = 1;
    private static final int LOAD_MORE = 2;

    private LinearLayoutManager mLayoutManager;
    private int mCount = INIT_COUNT;

    private TabHomeAdapter mAdapter;
    private ArrayList<HomeItem> mListData;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListData = new ArrayList<>();
        mAdapter = new TabHomeAdapter(getActivity(), mListData);

        if (BuildConfig.TEST_MODE) {
            String json = ResourceUtils.geFileFromAssets(getActivity(), "homePageJson");
            MaskArraySet<HomeItem> response = JsonUtils.parseJson(json, new TypeToken<MaskArraySet<HomeItem>>() {
            }.getType());

            mListData.addAll(response.getRsm());
            mAdapter.notifyDataSetChanged();
        } else {
            request("");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // return super.onCreateView(inflater, container, savedInstanceState);
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.label_home);
//        textView.setTextSize(20);
//        textView.setTextColor(Color.parseColor("#ff0000"));


        View view = inflater.inflate(R.layout.fragment_tab_home, container, false);
        ButterKnife.bind(this, view);

//        mExpandableListview.setAdapter(mAdapter);
//        boolean isEmpty = mListData.isEmpty() ? true : false;
//        mTxtEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);

        mRecyclerView.setHasFixedSize(true);
        // mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                LogUtils.i(TAG, "onScrollStateChanged lastVisibleItem = " + lastVisibleItem);

                LogUtils.i(TAG, "onScrollStateChanged lastVisibleItem = " + lastVisibleItem
                        + " ;getItemCount =" + mAdapter.getItemCount());
                if (RecyclerView.SCROLL_STATE_IDLE == newState
                        && (lastVisibleItem + 1) == mAdapter.getItemCount()) {
                    LogUtils.i(TAG, "onScrollStateChanged loadMore");
                    //getAppBaseActivity().showLoadingDialog();
                    //loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return view;
    }

    private void pullDown() {
//        mCount = INIT_COUNT;
//        LogUtils.i(TAG, "pullDown mCount = " + mCount);
//        noteGroupBy(mCount);
        // mAdapter.notifyDataSetChanged();
        //refreshCompleteQuick();
    }

    private void pullUp() {
//        mCount += PAGE_COUNT;
//        LogUtils.i(TAG, "pullUp mCount = " + mCount);
//        noteGroupBy(mCount);
        //refreshCompleteQuick();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void request(final String url) {
        getHelpBaseActivity().showLoadingDialog();

        MaskRequest<MaskArraySet<HomeItem>> request = new MaskRequest<MaskArraySet<HomeItem>>(
                Request.Method.GET,
                url,
                new TypeToken<MaskArraySet<HomeItem>>() {
                }.getType(),
                new Response.Listener<MaskArraySet<HomeItem>>() {
                    @Override
                    public void onResponse(MaskArraySet<HomeItem> response) {
                        getHelpBaseActivity().dismissLoadingDialog();
                        // refreshComplete();
                        if (null != response && response.getRsm() != null && response.getRsm().size() > 0) {
                            // mPage++;
                            // response(mode, response.getRsm());
                        } else {
                            getHelpBaseActivity().showToast(R.string.common_no_more_date);
                        }
                        // refreshContentTips(mAlbumDataSet.size() > 0 ? false : true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        getHelpBaseActivity().dismissLoadingDialog();
                        // refreshComplete();
                        getHelpBaseActivity().showToast(R.string.common_get_data_fail);
                    }
                }
        );

        VolleyManager.getInstance().addToRequestQueue(request, url);
    }
}
