package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.StockUps;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/19
 */
public class StockUpAdapter extends BasicAdapter<StockUps> {
    public StockUpAdapter(List<StockUps> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_stock_up_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if (NullUtils.isNull(mDatas.get(position).getCreatorName())) {
            vh.applyer.setText("申请人：" + mDatas.get(position).getCreatorName());
        }
        if (NullUtils.isNull(mDatas.get(position).getTitle())) {
            vh.produceName.setText(mDatas.get(position).getTitle());
        }
        if (NullUtils.isNull(mDatas.get(position).getCustName())) {
            vh.custName.setText(mDatas.get(position).getCustName());
        }
        vh.count.setText(Integer.toString(mDatas.get(position).getCountTotal()));
        if (NullUtils.isNull(mDatas.get(position).getFlowStatusName())) {
            vh.statusName.setText(mDatas.get(position).getFlowStatusName());
        }
        String timeStr = mDatas.get(position).getCreateDate();
        if (NullUtils.isNull(timeStr)) {
            timeStr = timeStr.substring(0, timeStr.length() - 3);
        }
        vh.createTime.setText(timeStr);
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.produce_name)
        private TextView produceName;
        @ViewInject(R.id.cust_name)
        private TextView custName;
        @ViewInject(R.id.count)
        private TextView count;
        @ViewInject(R.id.status_name)
        private TextView statusName;
        @ViewInject(R.id.applyer)
        private TextView applyer;
        @ViewInject(R.id.create_time)
        private TextView createTime;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
