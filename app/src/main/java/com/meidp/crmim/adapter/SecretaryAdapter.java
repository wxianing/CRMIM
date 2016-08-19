package com.meidp.crmim.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
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
        Secretary data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_secretary_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (NullUtils.isNull(data.getTitle())) {
            vh.title.setText(mDatas.get(position).getTitle());
        }
        if (NullUtils.isNull(mDatas.get(position).getMsg())) {
            vh.content.setText(mDatas.get(position).getMsg());
        }
        String timeStr = mDatas.get(position).getCreateTimeStr();
        if (NullUtils.isNull(timeStr)) {
            vh.time.setText(timeStr);
        }

        String statusName = mDatas.get(position).getStatusName();
        if (!statusName.equals("未读")) {
            vh.title.setTextColor(Color.rgb(189, 189, 189));
            vh.content.setTextColor(Color.rgb(189, 189, 189));
        }

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
