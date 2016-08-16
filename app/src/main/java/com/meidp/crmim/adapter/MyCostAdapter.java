package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.CostLists;

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

        vh.title.setText(data.getTitle());
        vh.ariseDate.setText("申请时间：" + data.getAriseDate());
//        vh.needTime.setText("需要时间：" + data.getNeedDate());
        vh.reason.setText("申请原因：" + data.getReason());
        vh.projectName.setText("关联项目：" + data.getProjectName());
        vh.applyPerson.setText("申请人：" + data.getCreatorName());
        vh.totalMoney.setText("￥" + data.getTotalAmount());
        vh.financialDepartment.setText(data.getFlowStatusName());

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
