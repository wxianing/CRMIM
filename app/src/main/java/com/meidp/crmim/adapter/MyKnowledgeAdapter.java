package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Announcements;
import com.meidp.crmim.model.MyKnowledges;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/3
 */
public class MyKnowledgeAdapter extends BasicAdapter<MyKnowledges> {
    public MyKnowledgeAdapter(List<MyKnowledges> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        MyKnowledges data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_announcement_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.title.setText(data.getTitle());
        vh.content.setText(data.getCulturetent());
        vh.time.setText(data.getCreateDate());
        vh.systemAnnouncement.setVisibility(View.GONE);
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.title_name)
        private TextView title;
        @ViewInject(R.id.time_tv)
        private TextView time;
        @ViewInject(R.id.ocntent_tv)
        private TextView content;
        @ViewInject(R.id.system_announcement)
        private TextView systemAnnouncement;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
