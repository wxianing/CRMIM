package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Importants;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/12
 */
public class ImportantAadapter extends BasicAdapter<Importants> {

    public ImportantAadapter(List<Importants> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_important_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.titleName.setText(mDatas.get(position).getArrangeTItle());
        vh.content.setText(mDatas.get(position).getContent());
        vh.createTime.setText(mDatas.get(position).getCreateDate());
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.title_name)
        private TextView titleName;
        @ViewInject(R.id.content_tv)
        private TextView content;
        @ViewInject(R.id.create_time)
        private TextView createTime;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
