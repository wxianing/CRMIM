package com.meidp.crmim.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.ClientContacts;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/19
 */
public class AddCrowdAdapter extends BasicAdapter<ClientContacts> {

    private static HashMap<Integer, Boolean> isSelected;

    public AddCrowdAdapter(List<ClientContacts> mDatas, Context context) {
        super(mDatas, context);
        isSelected = new HashMap<>();
        initData();
    }

    // 初始化isSelected的数据
    private void initData() {
        for (int i = 0; i < mDatas.size(); i++) {
            getIsSelected().put(mDatas.get(i).getID(), false);
        }
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        AddCrowdAdapter.isSelected = isSelected;
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_addcrowd_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (NullUtils.isNull(mDatas.get(position).getLinkManName())) {
            vh.name.setText("姓名：" + mDatas.get(position).getLinkManName());//联系人
        } else {
            vh.name.setText("姓名：");
        }
        if (NullUtils.isNull(mDatas.get(position).getWorkTel())) {
            vh.number.setText(mDatas.get(position).getWorkTel());//电话号码
        }
        if (NullUtils.isNull(mDatas.get(position).getCustName())) {
            if (mDatas.get(position).getCustName().equals("待定")) {
                vh.custName.setText("公司：" + "未填写");//公司、医院名称
            } else {
                vh.custName.setText("公司：" + mDatas.get(position).getCustName());
            }
        }
        if (NullUtils.isNull(mDatas.get(position).getDepartment())) {
            vh.administrative.setText("科室：" + mDatas.get(position).getDepartment());
        } else {
            vh.administrative.setText("科室：");
        }
        if (NullUtils.isNull(mDatas.get(position).getPosition())) {
            vh.dutyName.setText(mDatas.get(position).getPosition());//职务
        } else {
            vh.dutyName.setText("");
        }
        String timeStr = mDatas.get(position).getCreatedDate();
        if (NullUtils.isNull(timeStr)) {
            timeStr = timeStr.substring(0, timeStr.length() - 3);
            vh.createTime.setText("创建时间：" + timeStr);
        }

        boolean nowStatus = getIsSelected().get(mDatas.get(position).getID());
        Log.e("newStatus", ">>>>>>>>>" + nowStatus);

        vh.checkBox.setChecked(nowStatus);
        return convertView;
    }

    public static class ViewHolder {

        public TextView name;
        public TextView number;
        public TextView custName;
        public TextView administrative;//科室
        public TextView dutyName;
        public TextView createTime;
        public CheckBox checkBox;

        public ViewHolder(View view) {
            checkBox = (CheckBox) view.findViewById(R.id.check_box);
            name = (TextView) view.findViewById(R.id.name_tv);
            number = (TextView) view.findViewById(R.id.number);
            custName = (TextView) view.findViewById(R.id.cust_name);
            administrative = (TextView) view.findViewById(R.id.administrative_office);
            dutyName = (TextView) view.findViewById(R.id.duty_name);
            createTime = (TextView) view.findViewById(R.id.create_time);
        }
    }
}
