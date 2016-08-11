package com.meidp.crmim.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.crmim.MyApplication;
import com.meidp.crmim.R;
import com.meidp.crmim.model.Contact;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.view.DActionSheetDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private List<Contact> list;
    private LayoutInflater inflater;
    private Context context;

    public ExpandableAdapter(List<Contact> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getUsers().size();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupVieHolder gvh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_group_layout, parent, false);
            gvh = new GroupVieHolder(convertView);
            convertView.setTag(gvh);

        } else {
            gvh = (GroupVieHolder) convertView.getTag();
        }
        gvh.title.setText(list.get(groupPosition).getDeptName());//部门名称
        gvh.count.setText(Integer.toString(list.get(groupPosition).getUsers().size()));//部门人数
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Contact group = list.get(groupPosition);
        Contact.UsersBean ch = group.getUsers().get(childPosition);
        ChildViewHolder cvh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_child_layout, parent, false);
            cvh = new ChildViewHolder(convertView);
            convertView.setTag(cvh);
        } else {
            cvh = (ChildViewHolder) convertView.getTag();
        }
        cvh.nameTv.setText(ch.getEmployeeName());
        ImageLoader.getInstance().displayImage(ch.getPhotoURL(), cvh.icon, MyApplication.optionsRounds);
        String zhiwei = ch.getQuarterName();
        if (NullUtils.isNull(zhiwei)) {
            cvh.department.setText(ch.getDeptName() + "(" + zhiwei + ")");
        } else {
            cvh.department.setText(ch.getDeptName());
        }
        final String phone = ch.getMobile();
        if (NullUtils.isNull(phone)) {
            cvh.phoneNum.setText("      " + ch.getMobile());
        } else {
            cvh.phoneImg.setVisibility(View.GONE);
        }
//        final String phoneNum = ch.getMobile();
        cvh.phoneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectIcon(phone);
                setCellPhone(phone);//拨打电话
            }
        });
        return convertView;
    }

    private static class ChildViewHolder {
        private TextView nameTv;
        private ImageView icon;
        private TextView department;
        private TextView phoneNum;
        private ImageView phoneImg;

        public ChildViewHolder(View convertView) {
            nameTv = (TextView) convertView.findViewById(R.id.item_child_name);
            icon = (ImageView) convertView.findViewById(R.id.item_child_icon);
            department = (TextView) convertView.findViewById(R.id.department_tv);
            phoneNum = (TextView) convertView.findViewById(R.id.phone_num);
            phoneImg = (ImageView) convertView.findViewById(R.id.phone_img);
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

    //选择拨打电话方式
    private void selectIcon(final String phone) {
        new DActionSheetDialog(context).builder()
                .setTitle("请选择拨打方式")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("手机",
                        DActionSheetDialog.SheetItemColor.Blue,
                        new DActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                setCellPhone(phone);
                            }
                        })
                .show();

    }

    //跳转拨号页面
    public void setCellPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        context.startActivity(intent);
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
