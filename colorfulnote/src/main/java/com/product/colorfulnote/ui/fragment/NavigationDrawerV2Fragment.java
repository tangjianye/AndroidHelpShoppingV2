package com.product.colorfulnote.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.product.colorfulnote.R;
import com.product.colorfulnote.common.interfaces.OnRecyclerViewItemClickListener;
import com.product.colorfulnote.ui.activity.AboutActivity;
import com.product.colorfulnote.ui.adapter.NavigationAdapter;
import com.product.colorfulnote.ui.base.NoteBaseFragment;
import com.product.colorfulnote.ui.helper.ThemeHelper;
import com.product.common.interfaces.IInit;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
@Deprecated
public class NavigationDrawerV2Fragment extends NoteBaseFragment implements IInit {
    private static final String TAG = NavigationDrawerV2Fragment.class.getSimpleName();
    private ArrayList<String> mDataset;
    private NavigationAdapter mAdapter;

    @Bind(R.id.iv_portrait)
    SimpleDraweeView mIvPortrait;
    @Bind(R.id.txt_name)
    TextView mTxtName;
    @Bind(R.id.lv_navigation)
    RecyclerView mRecyclerView;
    @Bind(R.id.ly_portrait)
    LinearLayout mLyPortrait;
    @Bind(R.id.txt_desc)
    TextView mTxtDesc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        ButterKnife.bind(this, view);
        // initData();
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public NavigationDrawerV2Fragment() {
    }

    @Override
    public void initData() {
        mDataset = new ArrayList<>();
        // mDataset.add(getString(R.string.label_navi_login));
        mDataset.add(getString(R.string.label_nav_upgrade));
        mDataset.add(getString(R.string.label_nav_about));
        mAdapter = new NavigationAdapter(getActivity(), mDataset);

        mAdapter.setItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object obj) {
                String item = (String) obj;
                if (getString(R.string.label_nav_upgrade).equals(item)) {
                    // getNoteBaseActivity().showToast(R.string.label_navi_upgrade);
                } else if (getString(R.string.label_nav_about).equals(item)) {
                    // getNoteBaseActivity().showToast(R.string.label_navi_about);
                    getNoteBaseActivity().openActivity(AboutActivity.class);
                }
            }

            @Override
            public void onItemLongClick(View view, Object obj) {

            }
        });
    }

    @Override
    public void initView() {
        mTxtDesc.setTextColor(ContextCompat.getColor(getActivity(), ThemeHelper.getInstance().getTitleBgColor()));

        // String uri = "http://www.wzfzl.cn/uploads/allimg/120206/1_120206130502_2.jpg";
        // CommonUtils.loadImage(mIvPortrait, uri);
        // mTxtName.setText("汤加");

        // mLyPortrait.setBackgroundColor(ThemeHelper.getInstance().getItemBgColor());

        // 设置固定大小
//        mRecyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.addItemDecoration(new RecycleViewDivider(
//                getActivity(), LinearLayoutManager.HORIZONTAL,
//                getResources().getDimensionPixelOffset(R.dimen.android_divider_height_normal),
//                ContextCompat.getColor(getActivity(), ThemeHelper.getInstance().getItemBgColor())));
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    //    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {
//        //创建返回键，并实现打开关/闭监听
//        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
//                getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//            }
//        };
//
//        drawerToggle.syncState();
//        drawerLayout.setDrawerListener(drawerToggle);
//    }
}
