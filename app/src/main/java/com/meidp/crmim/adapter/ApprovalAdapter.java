package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.CheckforApply;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/8
 */
public class ApprovalAdapter extends BasicAdapter<CheckforApply> {
    public ApprovalAdapter(List<CheckforApply> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        CheckforApply data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_approval_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.titleName.setText(data.getTitle());
        vh.cusName.setText(data.getCustName());
        vh.dutyPerson.setText("负责人：" + data.getCreatorName());
        vh.currTime.setText(data.getCreateDate());
        vh.currStatus.setText(data.getCheckStatusName());
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.title_name)
        private TextView titleName;
        @ViewInject(R.id.cust_name)
        private TextView cusName;
        @ViewInject(R.id.duty_person)
        private TextView dutyPerson;
        @ViewInject(R.id.create_time)
        private TextView currTime;
        @ViewInject(R.id.curr_status)
        private TextView currStatus;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
