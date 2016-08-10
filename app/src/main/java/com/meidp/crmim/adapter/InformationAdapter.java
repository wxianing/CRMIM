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

    public InformationAdapter(List<InformationClassify> mDatas, Context context) {
        super(mDatas, context);
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
            resetViewHolder(vh);
        }

        ImageLoader.getInstance().displayImage(mDatas.get(position).getIcon(), vh.imageView, MyApplication.options);
        vh.titleName.setText(mDatas.get(position).getTypeName());

        int unReaderCount = mDatas.get(position).getNoReadTotal();

        switch (position) {
            case 0:
                BadgeView badgeView1 = new BadgeView(context);
                badgeView1.setTargetView(vh.announcementImg);
                badgeView1.setBadgeCount(unReaderCount);
                vh.explain.setText("巨烽的企业文化");
                break;
            case 1:
                BadgeView badgeView2 = new BadgeView(context);
                badgeView2.setTargetView(vh.announcementImg);
                badgeView2.setBadgeCount(unReaderCount);
                vh.explain.setText("巨烽的规章制度");
                break;
            case 2:
                BadgeView badgeView3 = new BadgeView(context);
                badgeView3.setTargetView(vh.announcementImg);
                badgeView3.setBadgeCount(unReaderCount);
                vh.explain.setText("巨烽新闻直播间");
                break;
            case 3:
                BadgeView badgeView4 = new BadgeView(context);
                badgeView4.setTargetView(vh.announcementImg);
                badgeView4.setBadgeCount(unReaderCount);
                vh.explain.setText("回顾过往活动及展会详情");
                break;
            case 4:
                BadgeView badgeView5 = new BadgeView(context);
                badgeView5.setTargetView(vh.announcementImg);
                badgeView5.setBadgeCount(unReaderCount);
                vh.explain.setText("企业组织架构一览");
                break;
        }
        unReaderCount = 0;
        return convertView;
    }

    protected void resetViewHolder(ViewHolder vh) {
        vh.imageView.setImageBitmap(null);
        vh.titleName.setText(null);
        vh.announcementImg.setImageBitmap(null);
    }

    private static class ViewHolder {
        @ViewInject(R.id.img)
        private ImageView imageView;
        @ViewInject(R.id.announcement)
        private TextView titleName;
        @ViewInject(R.id.announcement_img)
        private ImageView announcementImg;
        @ViewInject(R.id.explain)
        private TextView explain;

        public ViewHolder(View v) {
            x.view().inject(this, v);
        }
    }
}
