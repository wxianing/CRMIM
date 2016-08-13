package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Exhibitions;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/12
 */
public class ExhibitionsAdapter extends BasicAdapter<Exhibitions> {
    public ExhibitionsAdapter(List<Exhibitions> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_exhibition_list, parent, false);

            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.titleName.setText(mDatas.get(position).getTitle());
        vh.address.setText("地点：" + mDatas.get(position).getAddress());
        vh.time.setText("展会时间：" + mDatas.get(position).getExhibitionStartDate() + "~" + mDatas.get(position).getExhibitionEndDate());
        vh.dutyName.setText("负责人：" + mDatas.get(position).getLinkManName());
        vh.createTime.setText(mDatas.get(position).getCreateDate());

        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.title_name)
        private TextView titleName;
        @ViewInject(R.id.address_tv)
        private TextView address;
        @ViewInject(R.id.time_tv)
        private TextView time;
        @ViewInject(R.id.duty_name)
        private TextView dutyName;
        @ViewInject(R.id.create_time)
        private TextView createTime;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
