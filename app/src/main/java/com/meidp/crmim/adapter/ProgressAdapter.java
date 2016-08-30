package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.ProjectDetails;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/29
 */
public class ProgressAdapter extends BasicAdapter<ProjectDetails.ProcessListBean> {
    public ProgressAdapter(List<ProjectDetails.ProcessListBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ProjectDetails.ProcessListBean data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_process_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (NullUtils.isNull(data.getProcessName())) {
            vh.submitProject.setText(data.getProcessName());
        }
        if (NullUtils.isNull(data.getProcessTime())) {
            vh.submitTime.setText(data.getProcessTime());
        }
        return convertView;
    }

    public static class ViewHolder {
        @ViewInject(R.id.submit_project)
        public TextView submitProject;
        @ViewInject(R.id.submit_date)
        private TextView submitTime;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
