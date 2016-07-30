package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Projects;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/18
 */
public class MyProjectAdapter extends BasicAdapter<Projects> {
    public MyProjectAdapter(List<Projects> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        Projects data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_project_manager_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.projectName.setText(data.getProjectName());
        vh.projectNum.setText("项目编号：" + data.getProjectNo());
        vh.ceateTime.setText("项目时间:" + data.getCreateDate());

        return convertView;
    }

    private static class ViewHolder {

        @ViewInject(R.id.item_project_title)
        private TextView projectName;
        @ViewInject(R.id.project_num)
        private TextView projectNum;
        @ViewInject(R.id.project_time)
        private TextView ceateTime;
        @ViewInject(R.id.project_status)
        private TextView status;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
