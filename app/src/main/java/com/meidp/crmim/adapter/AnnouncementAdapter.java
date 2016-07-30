package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Announcements;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/18
 */
public class AnnouncementAdapter extends BasicAdapter<Announcements.DataListBean> {
    public AnnouncementAdapter(List<Announcements.DataListBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        Announcements.DataListBean data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_announcement_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.title.setText(data.getNewsTitle());
        vh.content.setText("内容：" + data.getNewsContent());
        vh.time.setText(data.getComfirmDate());

        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.title_name)
        private TextView title;
        @ViewInject(R.id.time_tv)
        private TextView time;
        @ViewInject(R.id.ocntent_tv)
        private TextView content;

        public ViewHolder(View view) {
            x.view().inject(this, view);

        }
    }
}
