package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.CostLists;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/30
 */
public class MyCostAdapter extends BasicAdapter<CostLists> {
    public MyCostAdapter(List<CostLists> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        CostLists data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_cost_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (NullUtils.isNull(data.getTitle())) {
            vh.title.setText(data.getTitle());
        } else {
            vh.title.setText("");
        }

        if (NullUtils.isNull(data.getAriseDate())) {
            vh.ariseDate.setText("申请时间：" + data.getAriseDate());
        } else {
            vh.ariseDate.setText("");
        }
//        vh.needTime.setText("需要时间：" + data.getNeedDate());

        if (NullUtils.isNull(data.getReason())) {
            vh.reason.setText("申请原因：" + data.getReason());
        } else {
            vh.reason.setText("申请原因：");
        }
        if (NullUtils.isNull(data.getProjectName())) {
            vh.projectName.setText("关联项目：" + data.getProjectName());
        } else {
            vh.projectName.setText("关联项目：");
        }
        if (NullUtils.isNull(data.getCreatorName())) {
            vh.applyPerson.setText("申请人：" + data.getCreatorName());
        } else {
            vh.applyPerson.setText("申请人：");
        }
        vh.totalMoney.setText("￥" + data.getTotalAmount());
        if (NullUtils.isNull(data.getFlowStatusName())) {
            vh.financialDepartment.setText(data.getFlowStatusName());
        } else {
            vh.financialDepartment.setText("");
        }

        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.title_tv)
        private TextView title;
        @ViewInject(R.id.arise_date)
        private TextView ariseDate;
        @ViewInject(R.id.need_time)
        private TextView needTime;
        @ViewInject(R.id.reason)
        private TextView reason;
        @ViewInject(R.id.project_name)
        private TextView projectName;
        @ViewInject(R.id.apply_person)
        private TextView applyPerson;
        @ViewInject(R.id.total_money)
        private TextView totalMoney;
        @ViewInject(R.id.financial_department)
        private TextView financialDepartment;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
