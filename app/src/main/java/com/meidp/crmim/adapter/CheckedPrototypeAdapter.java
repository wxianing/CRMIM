package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.CostDetails;
import com.meidp.crmim.model.PrototypeDetails;
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
public class CheckedPrototypeAdapter extends BasicAdapter<PrototypeDetails.FlowStepsBean> {
    public CheckedPrototypeAdapter(List<PrototypeDetails.FlowStepsBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        PrototypeDetails.FlowStepsBean data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_check_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.regulatoryAgency.setText("审批部门：" + data.getStepName());
        if (NullUtils.isNull(data.getNote())) {
            vh.regulatoryIdea.setText("审批意见：" + data.getNote());
        } else {
            vh.regulatoryIdea.setText("审批意见：");
        }
        String timeStr = data.getModifiedDate();
        if (NullUtils.isNull(timeStr)) {
            timeStr = timeStr.substring(0, timeStr.length() - 3);
            vh.regulatoryTime.setText(timeStr);
        } else {
            vh.regulatoryTime.setText("");
        }
        vh.img.setVisibility(View.VISIBLE);
        String status = data.getCheckStatusName();
        if (NullUtils.isNull(status) &&status.equals("通过")) {
            vh.img.setImageResource(R.mipmap.pass);
        } else {
            vh.img.setImageResource(R.mipmap.pass_not);
        }
        if (!NullUtils.isNull(status)) {
            status = "";
        }
        vh.currStatus.setText("状态：" + status);
        vh.step.setText("StepNo" + data.getStepNo());
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.regulatory_agency)
        private TextView regulatoryAgency;//审批部门
        @ViewInject(R.id.regulatory_idea)
        private TextView regulatoryIdea;//审批意见
        @ViewInject(R.id.regulatory_time)
        private TextView regulatoryTime;//审批时间
        @ViewInject(R.id.curr_status)
        private TextView currStatus;//当前状态
        @ViewInject(R.id.step)
        private TextView step;
        @ViewInject(R.id.status_img)
        private ImageView img;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
