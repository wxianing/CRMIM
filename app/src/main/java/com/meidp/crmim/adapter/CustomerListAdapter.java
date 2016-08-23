package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.ClientContacts;
import com.meidp.crmim.model.CustomerLists;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/19
 */
public class CustomerListAdapter extends BasicAdapter<ClientContacts> {
    public CustomerListAdapter(List<ClientContacts> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_customer_list, parent, false);
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
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.name_tv)
        private TextView name;
        @ViewInject(R.id.number)
        private TextView number;
        @ViewInject(R.id.cust_name)
        private TextView custName;
        @ViewInject(R.id.administrative_office)
        private TextView administrative;//科室
        @ViewInject(R.id.duty_name)
        private TextView dutyName;
        @ViewInject(R.id.create_time)
        private TextView createTime;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
