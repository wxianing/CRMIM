package com.meidp.crmim.adapter;

import android.content.Context;
import android.graphics.Color;
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
public class OpenProjectAdapter extends BasicAdapter<Projects> {
    public OpenProjectAdapter(List<Projects> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        Projects data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_open_projectr_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.projectName.setText(data.getProjectName());
        vh.linkName.setText("项目联系人："+data.getCustLinkMan());
        vh.projectNum.setText("项目编号：" + data.getProjectNo());
        String timeStr = data.getCreateDate().substring(0, 10);
        vh.ceateTime.setText("登记时间：" + timeStr);

        double success = data.getSuccessRate() * 100;
        if (success < 30) {
            vh.successRate.setTextColor(Color.RED);
        } else if (success < 50) {
            vh.successRate.setTextColor(Color.rgb(255, 194, 0));
        } else {
            vh.successRate.setTextColor(Color.GREEN);
        }

        vh.successRate.setText(success + "%");

        return convertView;
    }

    private static class ViewHolder {

        @ViewInject(R.id.item_project_title)
        private TextView projectName;
        @ViewInject(R.id.link_name)
        private TextView linkName;
        @ViewInject(R.id.project_num)
        private TextView projectNum;
        @ViewInject(R.id.project_time)
        private TextView ceateTime;
        @ViewInject(R.id.success_rate)
        private TextView successRate;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
