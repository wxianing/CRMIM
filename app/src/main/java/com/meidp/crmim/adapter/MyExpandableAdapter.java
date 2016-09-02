package com.meidp.crmim.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.meidp.crmim.R;
import com.meidp.crmim.model.Child;
import com.meidp.crmim.model.Contact;
import com.meidp.crmim.model.DocBean;
import com.meidp.crmim.model.Group;
import com.meidp.crmim.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class MyExpandableAdapter extends BaseExpandableListAdapter {

    private List<Group> list;
    private LayoutInflater inflater;
    private static HashMap<String, Boolean> isSelected;


    public MyExpandableAdapter(List<Group> list, Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        isSelected = new HashMap<>();
        initDate(list);
    }


    // 初始化isSelected的数据
    private void initDate(List<Group> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getDocBeen().size(); j++) {
                getIsSelected().put(list.get(i).getDocBeen().get(j).getFileName(), false);
            }
        }
    }

    public static HashMap<String, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<String, Boolean> isSelected) {
        MyExpandableAdapter.isSelected = isSelected;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildList().size();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupVieHolder gvh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gro_layout, parent, false);
            gvh = new GroupVieHolder(convertView);
            convertView.setTag(gvh);

        } else {
            gvh = (GroupVieHolder) convertView.getTag();
        }
        gvh.title.setText(list.get(groupPosition).getTitle());
        gvh.count.setText("" + list.get(groupPosition).getChildList().size());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Group group = list.get(groupPosition);
        Child ch = group.getChildList().get(childPosition);

        ChildViewHolder cvh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_chi_layout, parent, false);
            cvh = new ChildViewHolder(convertView);
            convertView.setTag(cvh);

        } else {
            cvh = (ChildViewHolder) convertView.getTag();
        }
        cvh.nameTv.setText(ch.getName());
//        boolean nowStatus = getIsSelected().get(list.get(groupPosition).getDocBeen().get(childPosition).getFileName());// 当前状态
//        Log.e("now", ">>>>>>>>>>>" + nowStatus);
        cvh.mCheckBox.setChecked(false);
        return convertView;
    }

    public static class ChildViewHolder {
        private TextView nameTv;

        public CheckBox mCheckBox;

        public ChildViewHolder(View convertView) {
            nameTv = (TextView) convertView.findViewById(R.id.item_child_name);
            mCheckBox = (CheckBox) convertView.findViewById(R.id.check_box);
        }
    }

    public class GroupVieHolder {
        private TextView title;
        private TextView count;

        public GroupVieHolder(View convertView) {
            title = (TextView) convertView.findViewById(R.id.item_group_title);
            count = (TextView) convertView.findViewById(R.id.count);
        }
    }


    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
