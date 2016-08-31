package com.meidp.crmim.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.activity.ApprovalDetailsActivity;
import com.meidp.crmim.activity.PrototypeDetailsActivity;
import com.meidp.crmim.model.ProjectDetails;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

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
    public View createView(final int position, View convertView, ViewGroup parent) {
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
            vh.submitProject.setText(data.getProcessName() + "：");
        } else {
            vh.submitProject.setText("");
        }
        if (NullUtils.isNull(data.getProcessTime())) {
            vh.submitTime.setText(data.getProcessTime());
        } else {
            vh.submitTime.setText("");
        }
        if (NullUtils.isNull(data.getMsg()) && !data.getMsg().equals("(当前状态)")) {
            vh.msg.setText(data.getMsg() + "  " + data.getFileNames());
        } else {
            vh.msg.setText("");
        }

        vh.addDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.shows(context, "正在开发中");
            }
        });
        vh.msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatas.get(position).getFKId() != 0) {
                    Intent intent = new Intent(context, PrototypeDetailsActivity.class);
                    intent.putExtra("OID", mDatas.get(position).getFKId());
                    context.startActivity(intent);
                }
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        @ViewInject(R.id.submit_project)
        public TextView submitProject;
        @ViewInject(R.id.submit_date)
        private TextView submitTime;
        @ViewInject(R.id.msg_tv)
        private TextView msg;
        @ViewInject(R.id.add_documents)
        private Button addDocuments;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
