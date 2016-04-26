package com.product.helpshopping.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.product.helpshopping.R;
import com.product.helpshopping.db.gen.Note;
import com.product.helpshopping.ui.entiy.NoteEntiy;
import com.product.helpshopping.ui.helper.ThemeHelper;
import com.product.common.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
@Deprecated
public class TimelineAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Note> mNoteList;
    private List<NoteEntiy> mGroupList;
    private List<List<Note>> mChildList;

    public TimelineAdapter(Context context, List<Note> noteList) {
        this.mContext = context;
        this.mInflater = ((Activity) context).getLayoutInflater();
        this.mNoteList = noteList;

        mGroupList = new ArrayList<>();
        mChildList = new ArrayList<>();

        groupBy(mNoteList);
        generateGroup(mChildList);
    }

    public void resetData(final List<Note> noteList) {
        this.mNoteList = noteList;

        groupBy(mNoteList);
        generateGroup(mChildList);

        notifyDataSetChanged();
    }

    private void groupBy(final List<Note> noteList) {
        mChildList.clear();
        ArrayList<Note> childData = null;
        String preDate = null;

        for (Note entiy : noteList) {
            String date = TimeUtils.getTime(entiy.getDate().getTime(), TimeUtils.DATE_FORMAT_DAY);
            // 根据日期分组
            if (null == preDate || !preDate.equals(date)) {
                childData = new ArrayList<>();
                mChildList.add(childData);
            }
            childData.add(entiy);
            preDate = date;
        }
    }

    private void generateGroup(final List<List<Note>> childList) {
        mGroupList.clear();
        for (List<Note> noteList : childList) {
            mGroupList.add(new NoteEntiy(noteList.size(), noteList.get(0)));
        }
    }

    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mChildList.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return mGroupList.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        NoteEntiy entiy = (NoteEntiy) getGroup(groupPosition);

        GroupViewHolder holder;
        if (null == view) {
            view = mInflater.inflate(R.layout.listitem_note_group, viewGroup, false);
            holder = new GroupViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (GroupViewHolder) view.getTag();
        }

        view.setBackgroundResource(ThemeHelper.getInstance().getItemBgColor());
        holder.ivTitle.setBackgroundResource(ThemeHelper.getInstance().getGroupIconBg());
        holder.txtTitleDate.setText(TimeUtils.getTime(entiy.getNote().getDate().getTime(), TimeUtils.DATE_FORMAT_DAY));
        holder.txtTitleNum.setText(String.format(mContext.getString(R.string.label_group_num), entiy.getCount()));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        Note entiy = (Note) getChild(groupPosition, childPosition);

        ChildViewHolder holder;
        if (null == view) {
            view = mInflater.inflate(R.layout.listitem_note_child, viewGroup, false);
            holder = new ChildViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ChildViewHolder) view.getTag();
        }

        holder.txtBodyTitle.setText(entiy.getTitle());
        holder.txtBodyData.setText(TimeUtils.getTime(entiy.getDate().getTime(), TimeUtils.DATE_FORMAT_HMS));
        holder.txtBodyContent.setText(entiy.getContent());
        holder.lyLine.setVisibility(isLastChild ? View.GONE : View.VISIBLE);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'listitem_note_group.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class GroupViewHolder {
        @Bind(R.id.iv_title)
        ImageView ivTitle;
        @Bind(R.id.txt_title_date)
        TextView txtTitleDate;
        @Bind(R.id.txt_title_num)
        TextView txtTitleNum;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'listitem_note_child.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ChildViewHolder {
        @Bind(R.id.txt_body_title)
        TextView txtBodyTitle;
        @Bind(R.id.txt_body_data)
        TextView txtBodyData;
        @Bind(R.id.txt_body_content)
        TextView txtBodyContent;
        @Bind(R.id.ly_line)
        View lyLine;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
