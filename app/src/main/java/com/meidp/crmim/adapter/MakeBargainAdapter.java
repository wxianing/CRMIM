package com.meidp.crmim.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Projects;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/18
 */
public class MakeBargainAdapter extends BasicAdapter<Projects> {
    public MakeBargainAdapter(List<Projects> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        Projects data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_make_bargain_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if (NullUtils.isNull(data.getProjectName())) {
            vh.projectName.setText(data.getProjectName());
        } else {
            vh.projectName.setText("");
        }
        if (NullUtils.isNull(data.getCustLinkMan())) {
            vh.linkName.setText("-" + data.getCustLinkMan());
        } else {
            vh.linkName.setText("");
        }
        String timeStr = data.getCreateDate();
        if (NullUtils.isNull(timeStr)) {
            timeStr = timeStr.substring(0, timeStr.length() - 3);
            vh.ceateTime.setText("成交时间：" + timeStr);
        } else {
            vh.ceateTime.setText("成交时间：");
        }
        if (NullUtils.isNull(data.getStatusName())) {
            vh.statusName.setText(data.getStatusName());
        } else {
            vh.statusName.setText("待跟进");
        }
        double success = data.getSuccessRate() * 100;
        if (success < 30) {
            vh.successRate.setTextColor(Color.RED);
        } else if (success < 50) {
            vh.successRate.setTextColor(Color.rgb(255, 194, 0));
        } else {
            vh.successRate.setTextColor(Color.BLUE);
        }
        vh.successRate.setText(success + "%");
        if (NullUtils.isNull(data.getCreatorName())) {
            vh.dutyPerson.setText("" + data.getCreatorName());
        } else {
            vh.dutyPerson.setText("");
        }

        if (NullUtils.isNull(data.getCompanyName())) {
            vh.company.setText(data.getCompanyName());
        } else {
            vh.company.setText("");
        }
        if (NullUtils.isNull(data.getDepartmentName())) {
            vh.department.setText("-" + data.getDepartmentName());
        } else {
            vh.department.setText("");
        }

        return convertView;
    }

    private static class ViewHolder {

        @ViewInject(R.id.item_project_title)
        private TextView projectName;
        @ViewInject(R.id.link_name)
        private TextView linkName;
        @ViewInject(R.id.project_time)
        private TextView ceateTime;
        @ViewInject(R.id.success_rate)
        private TextView successRate;
        @ViewInject(R.id.duty_person)
        private TextView dutyPerson;
        @ViewInject(R.id.status_name)
        private TextView statusName;
        @ViewInject(R.id.company_name)
        private TextView company;
        @ViewInject(R.id.department_tv)
        private TextView department;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
