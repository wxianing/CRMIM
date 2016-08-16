package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Secretary;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/9
 */
public class SecretaryAdapter extends BasicAdapter<Secretary> {
    public SecretaryAdapter(List<Secretary> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_secretary_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.title.setText(mDatas.get(position).getTitle());
        vh.content.setText(mDatas.get(position).getMsg());
        String timeStr = mDatas.get(position).getCreateTime();
//        if (NullUtils.isNull(timeStr)) {
//            timeStr = timeStr.substring(0, timeStr.length() - 3);
//        }
        vh.time.setText(mDatas.get(position).getCreateTime());
        return convertView;
    }

    private static class ViewHolder {

        @ViewInject(R.id.title_name)
        private TextView title;

        @ViewInject(R.id.content_tv)
        private TextView content;
        @ViewInject(R.id.time_tv)
        private TextView time;

        public ViewHolder(View v) {
            x.view().inject(this, v);
        }
    }
}
