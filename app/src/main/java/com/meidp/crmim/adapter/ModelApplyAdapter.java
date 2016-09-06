package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.ModelApply;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/22
 */
public class ModelApplyAdapter extends BasicAdapter<ModelApply> {

    public ModelApplyAdapter(List<ModelApply> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_modelapply_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.titleName.setText(mDatas.get(position).getTitle());
        if (NullUtils.isNull(mDatas.get(position).getProductName())) {
            vh.produceName.setText("产品名：" + mDatas.get(position).getProductName());
        } else {
            vh.produceName.setText("产品名：");
        }
        if (NullUtils.isNull(mDatas.get(position).getCustName())) {
            vh.customerName.setText("客户：" + mDatas.get(position).getCustName());
        } else {
            vh.customerName.setText("客户：");
        }
        vh.totalMoney.setText("￥" + Integer.toString(mDatas.get(position).getTotalFee()));
        if (NullUtils.isNull(mDatas.get(position).getCreatorName())) {
            vh.principal.setText("负责人：" + mDatas.get(position).getCreatorName());
        } else {
            vh.principal.setText("负责人：");
        }
        if (NullUtils.isNull(mDatas.get(position).getCreateDate())) {
            vh.createTime.setText(mDatas.get(position).getCreateDate());
        }else {
            vh.createTime.setText("");
        }
        vh.applyCount.setText("申请数量：" + Integer.toString(mDatas.get(position).getProductCount()));
        if (NullUtils.isNull(mDatas.get(position).getFlowStatusName())) {
            vh.checkStatus.setText(mDatas.get(position).getFlowStatusName());
        }
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.produce_name)
        private TextView produceName;
        @ViewInject(R.id.title_name)
        private TextView titleName;
        @ViewInject(R.id.customer_name)
        private TextView customerName;
        @ViewInject(R.id.total_money)
        private TextView totalMoney;
        @ViewInject(R.id.principal)
        private TextView principal;
        @ViewInject(R.id.create_time)
        private TextView createTime;
        @ViewInject(R.id.apply_count)
        private TextView applyCount;
        @ViewInject(R.id.checked_status)
        private TextView checkStatus;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
