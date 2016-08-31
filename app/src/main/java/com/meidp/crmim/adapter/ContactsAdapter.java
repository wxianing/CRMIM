package com.meidp.crmim.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.ClientContacts;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/19
 */
public class ContactsAdapter extends BasicAdapter<ClientContacts> {
    private Activity activity;

    public ContactsAdapter(List<ClientContacts> mDatas, Context context, Activity activity) {
        super(mDatas, context);
        this.activity = activity;
    }

    @Override
    public View createView(final int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_contacts_list, parent, false);
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
        vh.onlyVisiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("CustId", mDatas.get(position).getCustID());//单位Id
                intent.putExtra("CustName", mDatas.get(position).getCustName());//单位名称
                intent.putExtra("CustContact", mDatas.get(position).getLinkManName());//客户联系人
                intent.putExtra("CustPhone", mDatas.get(position).getWorkTel());//联系电话
                intent.putExtra("CustContactId", mDatas.get(position).getID());//联系人Id
                intent.putExtra("OID", mDatas.get(position).getID());//联系人Id
                intent.putExtra("Department", mDatas.get(position).getDepartment());//部门
                intent.putExtra("Position", mDatas.get(position).getPosition());//职位

                activity.setResult(1001, intent);
                activity.finish();
            }
        });
        /**
         * 选择项目
         */
        vh.associationItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("CustId", mDatas.get(position).getCustID());//单位Id
                intent.putExtra("CustName", mDatas.get(position).getCustName());//单位名称
                intent.putExtra("CustContact", mDatas.get(position).getLinkManName());//客户联系人
                intent.putExtra("CustPhone", mDatas.get(position).getWorkTel());//联系电话
                intent.putExtra("CustContactId", mDatas.get(position).getID());//联系人Id
                intent.putExtra("OID", mDatas.get(position).getID());//联系人Id
                intent.putExtra("Department", mDatas.get(position).getDepartment());//部门
                intent.putExtra("Position", mDatas.get(position).getPosition());//职位
                intent.putExtra("SELECTTAG", Constant.RESULT_OK);
                activity.setResult(1001, intent);
                activity.finish();
            }
        });
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
        @ViewInject(R.id.only_visiting)
        private Button onlyVisiting;
        @ViewInject(R.id.association_items)
        private Button associationItems;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
