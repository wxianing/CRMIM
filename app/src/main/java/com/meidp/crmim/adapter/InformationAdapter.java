package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.meidp.crmim.MyApplication;
import com.meidp.crmim.R;
import com.meidp.crmim.model.InformationClassify;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/8
 */
public class InformationAdapter extends BasicAdapter<InformationClassify> {
    private BadgeView badgeView;

    public InformationAdapter(List<InformationClassify> mDatas, Context context) {
        super(mDatas, context);
        badgeView = new BadgeView(context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_information_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(mDatas.get(position).getIcon(), vh.imageView, MyApplication.options);
        vh.titleName.setText(mDatas.get(position).getTypeName());
        if (mDatas.get(position).getNoReadTotal() > 0) {
            badgeView.setTargetView(vh.announcementImg);
            badgeView.setBadgeCount(mDatas.get(position).getNoReadTotal());
        }
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.img)
        private ImageView imageView;
        @ViewInject(R.id.announcement)
        private TextView titleName;
        @ViewInject(R.id.announcement_img)
        private ImageView announcementImg;

        public ViewHolder(View v) {
            x.view().inject(this, v);
        }
    }
}
