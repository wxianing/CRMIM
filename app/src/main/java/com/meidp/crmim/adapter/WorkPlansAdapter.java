package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.WorkPlans;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/2
 */
public class WorkPlansAdapter extends BasicAdapter<WorkPlans> {
    public WorkPlansAdapter(List<WorkPlans> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_work_plan, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.titleTv.setText(mDatas.get(position).getAimTitle());
        vh.contentv.setText(mDatas.get(position).getAimContent());
        vh.timeTv.setText(mDatas.get(position).getCreateDate());
        vh.typeTv.setText(mDatas.get(position).getAimSortName());

        return convertView;
    }


    private static class ViewHolder {
        @ViewInject(R.id.plan_title)
        private TextView titleTv;
        @ViewInject(R.id.content_tv)
        private TextView contentv;
        @ViewInject(R.id.time_tv)
        private TextView timeTv;
        @ViewInject(R.id.type_tv)
        private TextView typeTv;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
