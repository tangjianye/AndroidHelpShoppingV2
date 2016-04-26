package com.product.helpshopping.ui.fragment;

import com.product.helpshopping.ui.base.HelpBaseFragment;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
@Deprecated
public class NoteListFragment extends HelpBaseFragment {
//    private static final String TAG = NoteListFragment.class.getSimpleName();
//    private static final int INIT_COUNT = 5;
//    private static final int PAGE_COUNT = 5;
//    private static final long DELAY = 100;
//
//    private NoteAdapter mAdapter;
//    private List<Note> mNoteList;
//    private int mCount = INIT_COUNT;
//
//    @Bind(R.id.txt_empty)
//    TextView mTxtEmpty;
//
//    @Bind(R.id.expandable_listview)
//    PullToRefreshListView mExpListview;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mNoteList = new ArrayList<>();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_list_note, container, false);
//        ButterKnife.bind(this, view);
//        initView();
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        // Indicate that this fragment would like to influence the set of actions in the action bar.
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onResume() {
//        // TODO Auto-generated method stub
//        super.onResume();
//        MobclickAgent.onPageStart(TAG);
//    }
//
//    @Override
//    public void onPause() {
//        // TODO Auto-generated method stub
//        super.onPause();
//        MobclickAgent.onPageEnd(TAG);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }
//
//    public NoteListFragment() {
//    }
//
//    private void initView() {
//        // List<Note> noteList = getGroupNotes(INIT_COUNT);
//        noteGroupBy(INIT_COUNT);
//        boolean isEmpty = (mNoteList == null || mNoteList.isEmpty() ? true : false);
//        mTxtEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
//
//        mAdapter = new NoteAdapter(getActivity(), mNoteList);
//        mExpListview.getRefreshableView().setDivider(null);
//        mExpListview.getRefreshableView().setAdapter(mAdapter);
//
//
//        mExpListview.setMode(PullToRefreshBase.Mode.BOTH);
//        mExpListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//                mExpListview.getLoadingLayoutProxy().setLastUpdatedLabel(
//                        TimeUtils.getCurrentTimeInString(TimeUtils.DATE_FORMAT_MM));
//                pullDown();
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                mExpListview.getLoadingLayoutProxy().setLastUpdatedLabel(
//                        TimeUtils.getCurrentTimeInString(TimeUtils.DATE_FORMAT_MM));
//                pullUp();
//            }
//        });
//
//        mExpListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int pos = position - 1;
//                if (pos < 0) {
//                    return;
//                }
//                if (!mNoteList.isEmpty() && pos < mNoteList.size()) {
//                    Note entiy = mNoteList.get(pos);
//                    getNoteBaseActivity().openActivityForResult(NoteDetailActivity.class,
//                            Constants.COMMON_REQUEST_CODE, CommonUtils.getMaskBundle(entiy));
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//    }
//
//    private void noteGroupBy(final int count) {
//        mNoteList.clear();
//        List<Note> noteList = DBNoteHelper.getInstance().loadAllByDate();
//        if (noteList.isEmpty())
//            return;
//
//        for (int i = 0; i < count && i < noteList.size(); i++) {
//            mNoteList.add(noteList.get(i));
//        }
//    }
//
//    /**
//     * 重新设置xlistview的状态
//     */
//    private void refreshComplete() {
//        mExpListview.onRefreshComplete();
//    }
//
//    /**
//     * 快速刷新回调
//     */
//    private void refreshCompleteQuick() {
//        mExpListview.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mExpListview.onRefreshComplete();
//            }
//        }, DELAY);
//    }
//
//    private void pullDown() {
//        mCount = INIT_COUNT;
//        LogUtils.i(TAG, "pullDown mCount = " + mCount);
//        noteGroupBy(mCount);
//        mAdapter.notifyDataSetChanged();
//        refreshCompleteQuick();
//    }
//
//    private void pullUp() {
//        mCount += PAGE_COUNT;
//        LogUtils.i(TAG, "pullUp mCount = " + mCount);
//        noteGroupBy(mCount);
//        refreshCompleteQuick();
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        LogUtils.i(TAG, "onActivityResult requestCode = " + requestCode + " ;resultCode = " + resultCode);
//        if (Activity.RESULT_OK == resultCode) {
//            if (Constants.COMMON_REQUEST_CODE == requestCode) {
//                noteGroupBy(mCount);
//                boolean isEmpty = (mNoteList == null || mNoteList.isEmpty() ? true : false);
//                mTxtEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
//                mAdapter.notifyDataSetChanged();
//            }
//        }
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        // If the drawer is open, show the global app actions in the action bar. See also
//        // showGlobalContextActionBar, which controls the top-left area of the action bar.
//
//        inflater.inflate(R.menu.menu_add, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.action_add) {
//            MobclickAgent.onEvent(getActivity(), "click");
//            MobclickAgent.onEvent(getActivity(), "click", "ActionAdd");
//            getNoteBaseActivity().openActivityForResult(
//                    NoteDetailActivity.class, Constants.COMMON_REQUEST_CODE, null);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    public class NoteAdapter extends ArrayAdapter<Note> {
//        private int mResourceId;
//        private LayoutInflater mInflater;
//
//        public NoteAdapter(Context context, List<Note> objects) {
//            this(context, R.layout.listitem_note, objects);
//        }
//
//        public NoteAdapter(Context context, int resource, List<Note> objects) {
//            super(context, resource, objects);
//            mResourceId = resource;
//            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder;
//            if (null == convertView) {
//                convertView = mInflater.inflate(mResourceId, parent, false);
//                holder = new ViewHolder(convertView);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//
//            holder.lyCard.setBackgroundResource(ThemeHelper.getInstance().getItemBgColor());
//            holder.ivTitle.setBackgroundResource(ThemeHelper.getInstance().getGroupIconBg());
//            holder.txtTitleWeek.setText(ThemeHelper.getInstance().getWeekly(getItem(position).getDate()));
//            holder.txtTitleDate.setText(TimeUtils.getTime(getItem(position).getDate().getTime()));
//            holder.txtTitleContent.setText(getItem(position).getContent());
//            return convertView;
//        }
//
//        public class ViewHolder {
//            @Bind(R.id.iv_title)
//            ImageView ivTitle;
//            @Bind(R.id.txt_title_week)
//            TextView txtTitleWeek;
//            @Bind(R.id.txt_title_date)
//            TextView txtTitleDate;
//            @Bind(R.id.txt_title_content)
//            TextView txtTitleContent;
//            @Bind(R.id.ly_card)
//            LinearLayout lyCard;
//
//            ViewHolder(View view) {
//                ButterKnife.bind(this, view);
//            }
//        }
//    }
}
