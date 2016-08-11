package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Feedbacks;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/11
 */
public class FeedbackAdapter extends BasicAdapter<Feedbacks> {
    public FeedbackAdapter(List<Feedbacks> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_feedback_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.titleName.setText(mDatas.get(position).getTitle());
        vh.content.setText(mDatas.get(position).getContent());
        vh.time.setText(mDatas.get(position).getCreateDate());

        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.title_name)
        private TextView titleName;
        @ViewInject(R.id.content_tv)
        private TextView content;
        @ViewInject(R.id.time_tv)
        private TextView time;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
