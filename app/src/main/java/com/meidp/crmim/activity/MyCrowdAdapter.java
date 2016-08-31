package com.meidp.crmim.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.adapter.BasicAdapter;
import com.meidp.crmim.model.TeamMembers;

import java.util.List;

/**
 * Package： com.meidp.crmim.activity
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/31
 */
public class MyCrowdAdapter extends BasicAdapter<TeamMembers> {
    public MyCrowdAdapter(List<TeamMembers> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        TeamMembers data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_mycrowd_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.name.setText("姓名：" + data.getLinkmanName());
        vh.administrative.setText(data.getPosition());
        vh.number.setText(data.getMobilePhone());
        vh.custName.setText("公司：" + data.getCompanyName());

        return convertView;
    }

    private static class ViewHolder {
        private TextView name;
        private TextView number;
        private TextView custName;
        private TextView administrative;//科室
        private TextView dutyName;

        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.name_tv);
            number = (TextView) view.findViewById(R.id.number);
            custName = (TextView) view.findViewById(R.id.cust_name);
            administrative = (TextView) view.findViewById(R.id.administrative_office);
            dutyName = (TextView) view.findViewById(R.id.duty_name);
        }
    }
}
