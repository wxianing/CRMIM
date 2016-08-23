package com.meidp.crmim.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.ApprovalCosts;
import com.meidp.crmim.model.CheckforApply;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/8
 */
public class ApprovalCostAdapter extends BasicAdapter<ApprovalCosts> {
    public ApprovalCostAdapter(List<ApprovalCosts> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ApprovalCosts data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_approval_cost_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if (NullUtils.isNull(data.getTitle())) {
            vh.titleName.setText(data.getTitle());
        } else {
            vh.titleName.setText("");
        }

        vh.reimbursement.setText("保险金额：￥" + mDatas.get(position).getTotalAmount());
        if (NullUtils.isNull(data.getCreatorName())) {
            vh.dutyPerson.setText("申请人：" + data.getCreatorName());
        } else {
            vh.dutyPerson.setText("申请人：");
        }

        vh.currTime.setText(data.getCreateDate());
        if (NullUtils.isNull(data.getReason())) {
            vh.reason.setText("申请原因：" + data.getReason());
        } else {
            vh.reason.setText("申请原因：");
        }
        int status = Integer.valueOf(data.getStatus());
        switch (status) {
            case 0:
                vh.currStatus.setText("已审核");
                break;
            case 1:
                vh.currStatus.setText("待审核");
                break;
        }
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.title_name)
        private TextView titleName;
        @ViewInject(R.id.duty_person)
        private TextView dutyPerson;
        @ViewInject(R.id.create_time)
        private TextView currTime;
        @ViewInject(R.id.curr_status)
        private TextView currStatus;
        @ViewInject(R.id.reimbursement)
        private TextView reimbursement;
        @ViewInject(R.id.reason)
        private TextView reason;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
