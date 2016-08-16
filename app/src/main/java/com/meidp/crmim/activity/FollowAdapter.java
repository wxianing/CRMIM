package com.meidp.crmim.activity;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.ProjectDetails;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Package： com.meidp.crmim.activity
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/9
 */
public class FollowAdapter extends BaseAdapter {
    private ArrayList<ProjectDetails.ConstructionDetailsBean> mDatas;
    private Context context;

    private LayoutInflater inflater;

    public FollowAdapter(ArrayList<ProjectDetails.ConstructionDetailsBean> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas != null && mDatas.size() > 0 ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_follwo_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.personCount.setText("预计人数：" + mDatas.get(position).getPersonNum());

        String startTime = mDatas.get(position).getBeginDate();
        String overTime = mDatas.get(position).getEndDate();
        startTime = startTime.substring(0, 11);
        overTime = overTime.substring(0, 11);
        vh.startDate.setText(startTime + "~" + overTime);
        vh.remark.setText("备注：" + mDatas.get(position).getProjectMemo());
        vh.rate.setText("成功率:" + mDatas.get(position).getRate() + "%");
        vh.schedule.setText("进度：" + "0.3");
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.perosn_count)
        private TextView personCount;
        @ViewInject(R.id.rate)
        private TextView rate;
        @ViewInject(R.id.start_date)
        private TextView startDate;
        @ViewInject(R.id.schedule)
        private TextView schedule;

        @ViewInject(R.id.remark)
        private TextView remark;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
