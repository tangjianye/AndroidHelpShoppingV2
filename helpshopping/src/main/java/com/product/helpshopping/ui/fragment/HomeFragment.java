package com.product.helpshopping.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.product.common.utils.JsonUtils;
import com.product.common.utils.ResourceUtils;
import com.product.helpshopping.BuildConfig;
import com.product.helpshopping.R;
import com.product.helpshopping.module.net.VolleyManager;
import com.product.helpshopping.module.net.request.MaskRequest;
import com.product.helpshopping.module.net.response.HomeItem;
import com.product.helpshopping.module.net.response.MaskArraySet;
import com.product.helpshopping.ui.base.HelpBaseFragment;
import com.product.helpshopping.utils.CommonUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class HomeFragment extends HelpBaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private TabHomeAdapter mAdapter;
    private ArrayList<HomeItem> mListData;

    @Bind(R.id.expandable_listview)
    PullToRefreshGridView mExpandableListview;
    @Bind(R.id.txt_empty)
    TextView mTxtEmpty;

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
//        TextView view = new TextView(getActivity());
//        view.setText(com.product.helpshopping.R.string.label_home);
//        view.setTextSize(20);
//        view.setTextColor(Color.parseColor("#ff0000"));
        View view = inflater.inflate(R.layout.fragment_tab_home, container, false);
        ButterKnife.bind(this, view);

        mExpandableListview.setAdapter(mAdapter);
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


    public class TabHomeAdapter extends ArrayAdapter<HomeItem> {
        private int mResourceId;
        private LayoutInflater mInflater;

        public TabHomeAdapter(Context context, List<HomeItem> objects) {
            this(context, R.layout.listitem_tab_home, objects);
        }

        public TabHomeAdapter(Context context, int resource, List<HomeItem> objects) {
            super(context, resource, objects);
            mResourceId = resource;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (null == convertView) {
                convertView = mInflater.inflate(mResourceId, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            CommonUtils.loadImage(holder.ivTitle, getItem(position).getCover());
//            holder.txtTitleWeek.setText(ThemeHelper.getInstance().getWeekly(getItem(position).getDate()));
//            holder.txtTitleDate.setText(TimeUtils.getTime(getItem(position).getDate().getTime()));
//            holder.txtTitleContent.setText(getItem(position).getContent());
            return convertView;
        }

        public class ViewHolder {
            @Bind(R.id.iv_title)
            SimpleDraweeView ivTitle;
            @Bind(R.id.ly_card)
            CardView lyCard;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
