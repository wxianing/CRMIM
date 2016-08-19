package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.StockUpDetails;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/19
 */
public class StockFollowAdapter extends BasicAdapter<StockUpDetails.FlowStepsBean> {
    public StockFollowAdapter(List<StockUpDetails.FlowStepsBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_stockup_follow, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.stepName.setText(mDatas.get(position).getStepName());
        vh.statusName.setText(mDatas.get(position).getCheckStatusName());
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.step_name)
        private TextView stepName;
        @ViewInject(R.id.status_name)
        private TextView statusName;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
